package com.apifailoverandresilience.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Service
public class MonitoringService {

    private final Counter regionRequestCounter;
    private final Counter failoverCounter;
    private final Counter failbackCounter;
    private final Counter cacheSyncCounter;

    private final Timer apiResponseTimer;

    public MonitoringService(MeterRegistry meterRegistry) {

        this.regionRequestCounter = Counter.builder("region.requests")
                .description("Total Region Requests")
                .register(meterRegistry);

        this.failoverCounter = Counter.builder("failover.count")
                .description("Total Automatic Failovers")
                .register(meterRegistry);

        this.failbackCounter = Counter.builder("failback.count")
                .description("Total Manual Failbacks")
                .register(meterRegistry);

        this.cacheSyncCounter = Counter.builder("cache.sync.count")
                .description("Total Cache Synchronizations")
                .register(meterRegistry);

        this.apiResponseTimer = Timer.builder("api.response.time")
                .description("API Response Time")
                .register(meterRegistry);
    }

    // Region Request
    public void incrementRegionRequest() {
        regionRequestCounter.increment();
    }

    // Automatic Failover
    public void incrementFailover() {
        failoverCounter.increment();
    }

    // Manual Failback
    public void incrementFailback() {
        failbackCounter.increment();
    }

    // Cache Synchronization
    public void incrementCacheSync() {
        cacheSyncCounter.increment();
    }

    // API Response Time
    public <T> T recordResponseTime(java.util.function.Supplier<T> supplier) {

        return apiResponseTimer.record(supplier);
    }

    // Dashboard Data
    public Map<String, Double> getMetrics() {

        Map<String, Double> metrics = new HashMap<>();

        metrics.put("Region Requests", regionRequestCounter.count());
        metrics.put("Automatic Failovers", failoverCounter.count());
        metrics.put("Manual Failbacks", failbackCounter.count());
        metrics.put("Cache Synchronizations", cacheSyncCounter.count());

        return metrics;
    }

}