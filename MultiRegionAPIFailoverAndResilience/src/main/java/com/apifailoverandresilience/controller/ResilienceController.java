package com.apifailoverandresilience.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apifailoverandresilience.service.ResilienceService;


@RestController
@RequestMapping("/resilience")
public class ResilienceController {

	@Autowired
    private  ResilienceService resilienceService;

    

    // Test All Resilience Features
    @GetMapping("/test")
    public CompletableFuture<ResponseEntity<String>> testResilience() {

        return resilienceService.callActiveRegion()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/retry")
    public CompletableFuture<ResponseEntity<String>> retry() {

        return resilienceService.retryExample()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/circuit-breaker")
    public CompletableFuture<ResponseEntity<String>> circuitBreaker() {

        return resilienceService.circuitBreakerExample()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/rate-limiter")
    public CompletableFuture<ResponseEntity<String>> rateLimiter() {

        return resilienceService.rateLimiterExample()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/bulkhead")
    public CompletableFuture<ResponseEntity<String>> bulkhead() {

        return resilienceService.bulkheadExample()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/time-limiter")
    public CompletableFuture<ResponseEntity<String>> timeLimiter() {

        return resilienceService.simulateSlowService()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/active-region")
    public ResponseEntity<String> activeRegion() {

        return ResponseEntity.ok(
                resilienceService.getActiveRegionInfo());
    }

}
