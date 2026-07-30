package com.apifailoverandresilience.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifailoverandresilience.dto.RegionRequest;
import com.apifailoverandresilience.dto.RegionResponse;
import com.apifailoverandresilience.entity.Region;
import com.apifailoverandresilience.enums.RegionMode;
import com.apifailoverandresilience.enums.RegionStatus;
import com.apifailoverandresilience.repository.RegionRepository;
import com.apifailoverandresilience.util.SecurityUtils;

@Service
public class RegionService {

	@Autowired
	private  RegionRepository regionRepository;
	
	@Autowired
	private AuditLogService auditLogService;


	// Create Region
	public RegionResponse createRegion(RegionRequest request) {

		if (regionRepository.existsByRegionCode(request.getRegionCode())) {
			throw new RuntimeException("Region Code already exists");
		}

		Region region = new Region();

		region.setRegionName(request.getRegionName());
		region.setRegionCode(request.getRegionCode());
		region.setBaseUrl(request.getBaseUrl());
		region.setPriority(request.getPriority());
		region.setMode(RegionMode.valueOf(request.getMode()));
		region.setStatus(RegionStatus.valueOf(request.getStatus()));
		region.setHealth(request.getHealth());
		region.setCreatedAt(LocalDateTime.now());
		region.setUpdatedAt(LocalDateTime.now());

		Region savedRegion = regionRepository.save(region);
		
		auditLogService.saveAuditLog(
				SecurityUtils.getCurrentUsername(),
		        "CREATE_REGION",
		        "REGION",
		        "Created region : " + savedRegion.getRegionName(),
		        "127.0.0.1",
		        "SUCCESS");

		return mapToResponse(savedRegion);
	}

	public List<RegionResponse> getAllRegions() {

		return regionRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	public RegionResponse getRegionById(Long id) {

		Region region = regionRepository.findById(id).orElseThrow(() -> new RuntimeException("Region not found"));

		return mapToResponse(region);
	}

	public RegionResponse updateRegion(Long id, RegionRequest request) {

		Region region = regionRepository.findById(id).orElseThrow(() -> new RuntimeException("Region not found"));

		region.setRegionName(request.getRegionName());
		region.setRegionCode(request.getRegionCode());
		region.setBaseUrl(request.getBaseUrl());
		region.setPriority(request.getPriority());
		region.setMode(RegionMode.valueOf(request.getMode()));
		region.setStatus(RegionStatus.valueOf(request.getStatus()));
		region.setHealth(request.getHealth());
		region.setUpdatedAt(LocalDateTime.now());

		Region updatedRegion = regionRepository.save(region);
		
		auditLogService.saveAuditLog(
				SecurityUtils.getCurrentUsername(),
		        "UPDATE_REGION",
		        "REGION",
		        "Updated region : " + region.getRegionName(),
		        "127.0.0.1",
		        "SUCCESS");

		return mapToResponse(updatedRegion);
	}

	public String deleteRegion(Long id) {

		Region region = regionRepository.findById(id).orElseThrow(() -> new RuntimeException("Region not found"));

		regionRepository.delete(region);
		
		auditLogService.saveAuditLog(
				SecurityUtils.getCurrentUsername(),
		        "DELETE_REGION",
		        "REGION",
		        "Deleted region : " + region.getRegionName(),
		        "127.0.0.1",
		        "SUCCESS");

		return "Region deleted successfully";
	}

	// Update Region Status
	public RegionResponse updateRegionStatus(Long id, String status) {

		Region region = regionRepository.findById(id).orElseThrow(() -> new RuntimeException("Region not found"));

		region.setStatus(RegionStatus.valueOf(status.toUpperCase()));
		region.setUpdatedAt(LocalDateTime.now());

		Region updatedRegion = regionRepository.save(region);
		

		return mapToResponse(updatedRegion);
	}

	private RegionResponse mapToResponse(Region region) {

		RegionResponse response = new RegionResponse();

		response.setId(region.getId());
		response.setRegionName(region.getRegionName());
		response.setRegionCode(region.getRegionCode());
		response.setBaseUrl(region.getBaseUrl());
		response.setPriority(region.getPriority());
		response.setMode(region.getMode().name());
		response.setStatus(region.getStatus().name());
		response.setHealth(region.getHealth());
		response.setCreatedAt(region.getCreatedAt());
		response.setUpdatedAt(region.getUpdatedAt());

		return response;
	}
}