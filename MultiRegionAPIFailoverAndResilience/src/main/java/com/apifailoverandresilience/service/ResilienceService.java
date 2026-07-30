package com.apifailoverandresilience.service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.apifailoverandresilience.entity.Region;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@Service
public class ResilienceService {

    private final FailoverService failoverService;

    public ResilienceService(FailoverService failoverService) {
        this.failoverService = failoverService;
    }

    /**
     * Main Business API
     */
    @Retry(name = "regionService", fallbackMethod = "fallback")
    @CircuitBreaker(name = "regionService", fallbackMethod = "fallback")
    @RateLimiter(name = "regionService")
    @Bulkhead(name = "regionService", type = Type.THREADPOOL)
    @TimeLimiter(name = "regionService")
    public CompletableFuture<String> callActiveRegion() {

        return CompletableFuture.supplyAsync(() -> {

            Region activeRegion = failoverService.getActiveRegion();

            System.out.println("--------------------------------------");
            System.out.println("Calling Region : " + activeRegion.getRegionName());
            System.out.println("Base URL       : " + activeRegion.getBaseUrl());
            System.out.println("Time           : " + LocalDateTime.now());
            System.out.println("--------------------------------------");

            // Simulate Network Delay
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Simulate Failure
            if ("INACTIVE".equalsIgnoreCase(activeRegion.getStatus().name())
                    || "DOWN".equalsIgnoreCase(activeRegion.getHealth())) {

                throw new RuntimeException(
                        activeRegion.getRegionName() + " is unavailable.");
            }

            return "Successfully Connected to "
                    + activeRegion.getRegionName()
                    + " Region";
        });

    }

    
     // Retry + CircuitBreaker + TimeLimiter fallback
     
    public CompletableFuture<String> fallback(Throwable ex) {

        System.out.println("Fallback Triggered : " + ex.getMessage());

        return CompletableFuture.completedFuture(
                "Fallback Response : Service temporarily unavailable.");
    }

    
     //Get Active Region Information
     
    public String getActiveRegionInfo() {

        Region region = failoverService.getActiveRegion();

        return "Active Region : "
                + region.getRegionName()
                + " | Status : "
                + region.getStatus()
                + " | Health : "
                + region.getHealth();
    }

    
     //Simulate Slow API
     
    @TimeLimiter(name = "regionService", fallbackMethod = "fallback")
    public CompletableFuture<String> simulateSlowService() {

        return CompletableFuture.supplyAsync(() -> {

            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return "Slow Service Response";
        });
    }

    
       // Retry Example
     
    @Retry(name = "regionService", fallbackMethod = "fallback")
    public CompletableFuture<String> retryExample() {

        return CompletableFuture.supplyAsync(() -> {

            throw new RuntimeException("Retry Example Failed");

        });

    }

    
     //Circuit Breaker Example
     
    @CircuitBreaker(name = "regionService", fallbackMethod = "fallback")
    public CompletableFuture<String> circuitBreakerExample() {

        return CompletableFuture.supplyAsync(() -> {

            throw new RuntimeException("Circuit Breaker Example Failed");

        });

    }

    
     // Rate Limiter Example
     
    @RateLimiter(name = "regionService", fallbackMethod = "fallback")
    public CompletableFuture<String> rateLimiterExample() {

        return CompletableFuture.completedFuture(
                "Rate Limiter Request Processed Successfully");
    }

    
     // Bulkhead Example
     
    @Bulkhead(name = "regionService", type = Type.THREADPOOL)
    public CompletableFuture<String> bulkheadExample() {

        return CompletableFuture.supplyAsync(() -> {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return "Bulkhead Request Executed Successfully";

        });

    }

}