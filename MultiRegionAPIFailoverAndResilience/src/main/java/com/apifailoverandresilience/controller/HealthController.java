package com.apifailoverandresilience.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apifailoverandresilience.dto.HealthResponse;
import com.apifailoverandresilience.entity.Region;
import com.apifailoverandresilience.service.HealthService;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    private final HealthService healthService;

    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    // Overall Application Health
    @GetMapping
    public ResponseEntity<HealthResponse> applicationHealth() {

        return ResponseEntity.ok(
                healthService.applicationHealth());
    }

    // Database Health
    @GetMapping("/database")
    public ResponseEntity<HealthResponse> databaseHealth() {

        return ResponseEntity.ok(
                healthService.databaseHealth());
    }

    // Redis Health
    @GetMapping("/redis")
    public ResponseEntity<HealthResponse> redisHealth() {

        return ResponseEntity.ok(
                healthService.redisHealth());
    }

    // Kafka Health
    @GetMapping("/kafka")
    public ResponseEntity<HealthResponse> kafkaHealth() {

        return ResponseEntity.ok(
                healthService.kafkaHealth());
    }

    // Region Health
    @GetMapping("/regions")
    public ResponseEntity<List<Region>> regionHealth() {

        return ResponseEntity.ok(
                healthService.regionHealth());
    }

    // Active Region
    @GetMapping("/active-region")
    public ResponseEntity<Region> activeRegion() {

        return ResponseEntity.ok(
                healthService.activeRegion());
    }

}
