package com.apifailoverandresilience.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apifailoverandresilience.dto.AuditLogResponse;
import com.apifailoverandresilience.service.AuditLogService;

@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogController {

	private final AuditLogService auditLogService;

	public AuditLogController(AuditLogService auditLogService) {
		this.auditLogService = auditLogService;
	}

	// Get All Logs
	@GetMapping
	public ResponseEntity<Page<AuditLogResponse>> getAllLogs(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		return ResponseEntity.ok(auditLogService.getAllLogs(page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<AuditLogResponse> getLogById(@PathVariable Long id) {

		return ResponseEntity.ok(auditLogService.getLogById(id));
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<Page<AuditLogResponse>> getLogsByUsername(@PathVariable String username,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		return ResponseEntity.ok(auditLogService.getLogsByUsername(username, page, size));
	}

	@GetMapping("/action/{action}")
	public ResponseEntity<Page<AuditLogResponse>> getLogsByAction(@PathVariable String action,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		return ResponseEntity.ok(auditLogService.getLogsByAction(action, page, size));
	}

	// Get Logs By Module
	@GetMapping("/module/{module}")
	public ResponseEntity<Page<AuditLogResponse>> getLogsByModule(@PathVariable String module,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		return ResponseEntity.ok(auditLogService.getLogsByModule(module, page, size));
	}

	// Get Logs By Status
	@GetMapping("/status/{status}")
	public ResponseEntity<Page<AuditLogResponse>> getLogsByStatus(@PathVariable String status,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		return ResponseEntity.ok(auditLogService.getLogsByStatus(status, page, size));
	}

	@GetMapping("/date")
	public ResponseEntity<Page<AuditLogResponse>> getLogsBetweenDates(

			@RequestParam String startDate, @RequestParam String endDate, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDateTime start = LocalDateTime.parse(startDate, formatter);
		LocalDateTime end = LocalDateTime.parse(endDate, formatter);

		return ResponseEntity.ok(auditLogService.getLogsBetweenDates(start, end, page, size));
	}
}
