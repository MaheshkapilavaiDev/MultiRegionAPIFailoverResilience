package com.apifailoverandresilience.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apifailoverandresilience.dto.FailoverRequest;
import com.apifailoverandresilience.dto.FailoverResponse;
import com.apifailoverandresilience.entity.Region;
import com.apifailoverandresilience.service.FailoverService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class FailoverController {

	@Autowired
    private  FailoverService failoverService;

    @PostMapping("/failover")
    public ResponseEntity<FailoverResponse> failover(
            @Valid @RequestBody FailoverRequest request) {

        return new ResponseEntity<>(
                failoverService.failover(request),
                HttpStatus.OK);
    }

    @PostMapping("/failback/{regionId}")
    public ResponseEntity<FailoverResponse> failback(
            @PathVariable Long regionId) {

        return ResponseEntity.ok(
                failoverService.failback(regionId));
    }

    @GetMapping("/system/status")
    public ResponseEntity<Region> getCurrentRegion() {

        return ResponseEntity.ok(
                failoverService.getActiveRegion());
    }

}
