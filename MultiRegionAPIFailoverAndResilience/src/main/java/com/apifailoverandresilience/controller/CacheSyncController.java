package com.apifailoverandresilience.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apifailoverandresilience.dto.CacheSyncRequest;
import com.apifailoverandresilience.dto.CacheSyncResponse;
import com.apifailoverandresilience.service.CacheSyncService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cache")
public class CacheSyncController {

	@Autowired
	private CacheSyncService cacheSyncService;

	// Synchronize Cache

	@PostMapping("/sync")
	public ResponseEntity<CacheSyncResponse> syncCache(@Valid @RequestBody CacheSyncRequest request) {

		return new ResponseEntity<>(cacheSyncService.syncCache(request), HttpStatus.CREATED);
	}

	@GetMapping("/{key}")
	public ResponseEntity<CacheSyncResponse> getCache(@PathVariable String key) {

		return ResponseEntity.ok(cacheSyncService.getCache(key));
	}

	@PutMapping("/{key}")
	public ResponseEntity<CacheSyncResponse> updateCache(@PathVariable String key,
			@Valid @RequestBody CacheSyncRequest request) {

		return ResponseEntity.ok(cacheSyncService.updateCache(key, request));
	}

	@DeleteMapping("/{key}")
	public ResponseEntity<String> deleteCache(@PathVariable String key) {

		return ResponseEntity.ok(cacheSyncService.deleteCache(key));
	}

	// Check Cache Exists

	@GetMapping("/exists/{key}")
	public ResponseEntity<Boolean> cacheExists(@PathVariable String key) {

		return ResponseEntity.ok(cacheSyncService.cacheExists(key));
	}

}
