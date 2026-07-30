package com.apifailoverandresilience.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apifailoverandresilience.service.MonitoringService;

@RestController
@RequestMapping("/api/monitoring")
public class MonitoringController {

    private final MonitoringService monitoringService;

    public MonitoringController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    // Dashboard Metrics
    @GetMapping("/metrics")
    public ResponseEntity<Map<String, Double>> getMetrics() {

        return ResponseEntity.ok(monitoringService.getMetrics());
    }

    // Health Check
    @GetMapping("/health")
    public ResponseEntity<String> health() {

        return ResponseEntity.ok("Application is UP");
    }

    // Application Status
    @GetMapping("/status")
    public ResponseEntity<String> status() {

        return ResponseEntity.ok("Multi-Region API Failover & Resilience is Running Successfully");
    }

}