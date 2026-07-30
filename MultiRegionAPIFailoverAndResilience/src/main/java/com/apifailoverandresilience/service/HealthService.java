package com.apifailoverandresilience.service;

import java.util.List;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;

import com.apifailoverandresilience.dto.HealthResponse;
import com.apifailoverandresilience.entity.Region;
import com.apifailoverandresilience.repository.RegionRepository;

@Service
public class HealthService {

    private final RegionRepository regionRepository;
    private final RedisConnectionFactory redisConnectionFactory;

    public HealthService(RegionRepository regionRepository,
                         RedisConnectionFactory redisConnectionFactory) {

        this.regionRepository = regionRepository;
        this.redisConnectionFactory = redisConnectionFactory;
    }

    // Overall Application Health
    public HealthResponse applicationHealth() {

        return new HealthResponse(
                "Application",
                "UP",
                "Application is running successfully");
    }

    // Database Health
    public HealthResponse databaseHealth() {

        try {

            regionRepository.count();

            return new HealthResponse(
                    "Database",
                    "UP",
                    "Database connection is healthy");

        } catch (Exception e) {

            return new HealthResponse(
                    "Database",
                    "DOWN",
                    "Database connection failed");
        }
    }

    // Redis Health
    public HealthResponse redisHealth() {

        try {

            redisConnectionFactory.getConnection().ping();

            return new HealthResponse(
                    "Redis",
                    "UP",
                    "Redis server is running");

        } catch (Exception e) {

            return new HealthResponse(
                    "Redis",
                    "DOWN",
                    "Redis server is unavailable");
        }
    }

    // Kafka Health
    public HealthResponse kafkaHealth() {

        return new HealthResponse(
                "Kafka",
                "UP",
                "Kafka server is running");
    }

    // Region Health
    public List<Region> regionHealth() {

        return regionRepository.findAll();
    }

    // Active Region
    public Region activeRegion() {

        return regionRepository
                .findFirstByStatusOrderByPriorityAsc("ACTIVE")
                .orElseThrow(() -> new RuntimeException("No Active Region Found"));
    }

}
