package com.apifailoverandresilience.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifailoverandresilience.dto.FailoverRequest;
import com.apifailoverandresilience.dto.FailoverResponse;
import com.apifailoverandresilience.entity.Region;
import com.apifailoverandresilience.enums.RegionStatus;
import com.apifailoverandresilience.repository.RegionRepository;

@Service
public class FailoverService {

	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired
	private AuditLogService auditLogService;

	// Automatic Failover
	public FailoverResponse failover(FailoverRequest request) {

		Region currentRegion = regionRepository.findById(request.getRegionId())
				.orElseThrow(() -> new RuntimeException("Region not found"));

		currentRegion.setStatus(RegionStatus.INACTIVE);
		regionRepository.save(currentRegion);

		List<Region> healthyRegions = regionRepository.findAll().stream()
				.filter(region -> region.getStatus() == RegionStatus.ACTIVE)
				.filter(region -> region.getHealth().equalsIgnoreCase("HEALTHY"))
				.sorted(Comparator.comparing(Region::getPriority)).toList();

		if (healthyRegions.isEmpty()) {
			throw new RuntimeException("No healthy region available");
		}

		Region targetRegion = healthyRegions.get(0);

		FailoverResponse response = new FailoverResponse();

		response.setSourceRegion(currentRegion.getRegionName());
		response.setTargetRegion(targetRegion.getRegionName());
		response.setBaseUrl(targetRegion.getBaseUrl());
		response.setStatus("FAILOVER_SUCCESS");
		response.setMessage("Traffic switched successfully");
		response.setFailoverTime(LocalDateTime.now());
		
		auditLogService.saveAuditLog(
		        "SYSTEM",
		        "FAILOVER",
		        "FAILOVER",
		        "Traffic switched to backup region",
		        "127.0.0.1",
		        "SUCCESS");

		return response;
	}

	// Manual Failback
	public FailoverResponse failback(Long regionId) {

		Region region = regionRepository.findById(regionId).orElseThrow(() -> new RuntimeException("Region not found"));

		region.setStatus(RegionStatus.ACTIVE);

		regionRepository.save(region);
		
		auditLogService.saveAuditLog(
		        "admin@gmail.com",
		        "FAILBACK",
		        "FAILOVER",
		        "Traffic switched back to primary region",
		        "127.0.0.1",
		        "SUCCESS");

		FailoverResponse response = new FailoverResponse();

		response.setSourceRegion("Backup Region");
		response.setTargetRegion(region.getRegionName());
		response.setBaseUrl(region.getBaseUrl());
		response.setStatus("FAILBACK_SUCCESS");
		response.setMessage("Traffic restored successfully");
		response.setFailoverTime(LocalDateTime.now());

		return response;
	}

	public Region getActiveRegion() {

		return regionRepository.findAll().stream().filter(region -> region.getStatus() == RegionStatus.ACTIVE)
				.filter(region -> region.getHealth().equalsIgnoreCase("HEALTHY"))
				.min(Comparator.comparing(Region::getPriority))
				.orElseThrow(() -> new RuntimeException("No active region available"));
	}

}
