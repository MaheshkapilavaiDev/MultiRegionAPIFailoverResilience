package com.apifailoverandresilience.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.apifailoverandresilience.dto.AuditLogResponse;
import com.apifailoverandresilience.entity.AuditLog;
import com.apifailoverandresilience.repository.AuditLogRepository;

@Service
public class AuditLogService {

	private final AuditLogRepository auditLogRepository;

	public AuditLogService(AuditLogRepository auditLogRepository) {
		this.auditLogRepository = auditLogRepository;
	}

	// Save Audit Log
	public void saveAuditLog(String username, String action, String moduleName, String description, String ipAddress,
			String status) {

		AuditLog auditLog = new AuditLog();

		auditLog.setUsername(username);
		auditLog.setAction(action);
		auditLog.setModuleName(moduleName);
		auditLog.setDescription(description);
		auditLog.setIpAddress(ipAddress);
		auditLog.setStatus(status);
		//auditLog.setCreatedAt(LocalDateTime.now());

		auditLogRepository.save(auditLog);
	}

	// Get All Logs (Pagination)
	public Page<AuditLogResponse> getAllLogs(int page, int size) {

		return auditLogRepository.findAll(PageRequest.of(page, size)).map(this::mapToResponse);
	}

	// Get Log By Id
	public AuditLogResponse getLogById(Long id) {

		AuditLog auditLog = auditLogRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Audit Log not found"));

		return mapToResponse(auditLog);
	}

	// Get Logs By Username
	public Page<AuditLogResponse> getLogsByUsername(String username, int page, int size) {

		return auditLogRepository.findByUsername(username, PageRequest.of(page, size)).map(this::mapToResponse);
	}

	// Get Logs By Action
	public Page<AuditLogResponse> getLogsByAction(String action, int page, int size) {

		return auditLogRepository.findByAction(action, PageRequest.of(page, size)).map(this::mapToResponse);
	}

	// Get Logs By Module
	public Page<AuditLogResponse> getLogsByModule(String module, int page, int size) {

		return auditLogRepository.findByModuleName(module, PageRequest.of(page, size)).map(this::mapToResponse);
	}

// Get Logs By Status
	public Page<AuditLogResponse> getLogsByStatus(String status, int page, int size) {

		return auditLogRepository.findByStatus(status, PageRequest.of(page, size)).map(this::mapToResponse);
	}

	// Get Logs Between Dates
	public Page<AuditLogResponse> getLogsBetweenDates(LocalDateTime startDate, LocalDateTime endDate, int page,
			int size) {

		return auditLogRepository.findByCreatedAtBetween(startDate, endDate, PageRequest.of(page, size))
				.map(this::mapToResponse);
	}

	// Entity -> Response
	private AuditLogResponse mapToResponse(AuditLog auditLog) {

		AuditLogResponse response = new AuditLogResponse();

		response.setId(auditLog.getId());
		response.setUsername(auditLog.getUsername());
		response.setAction(auditLog.getAction());
		response.setModuleName(auditLog.getModuleName());
		response.setDescription(auditLog.getDescription());
		response.setIpAddress(auditLog.getIpAddress());
		response.setStatus(auditLog.getStatus());
		response.setCreatedAt(auditLog.getCreatedAt());

		return response;
	}

}