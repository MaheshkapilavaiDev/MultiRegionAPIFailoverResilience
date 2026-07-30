package com.apifailoverandresilience.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apifailoverandresilience.dto.RegionRequest;
import com.apifailoverandresilience.dto.RegionResponse;
import com.apifailoverandresilience.service.RegionService;

@RestController
@RequestMapping("/regions")
public class RegionController {

	@Autowired
	private RegionService regionService;

	@PostMapping
	public ResponseEntity<RegionResponse> createRegion(@RequestBody RegionRequest request) {

		return new ResponseEntity<>(regionService.createRegion(request), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<RegionResponse>> getAllRegions() {

		return ResponseEntity.ok(regionService.getAllRegions());
	}

	@GetMapping("/{id}")
	public ResponseEntity<RegionResponse> getRegionById(@PathVariable Long id) {

		return ResponseEntity.ok(regionService.getRegionById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<RegionResponse> updateRegion(@PathVariable Long id, @RequestBody RegionRequest request) {

		return ResponseEntity.ok(regionService.updateRegion(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRegion(@PathVariable Long id) {

		return ResponseEntity.ok(regionService.deleteRegion(id));
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<RegionResponse> updateRegionStatus(@PathVariable Long id, @RequestParam String status) {

		return ResponseEntity.ok(regionService.updateRegionStatus(id, status));
	}
}
