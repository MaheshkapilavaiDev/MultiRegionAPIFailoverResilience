package com.apifailoverandresilience.service;

import java.time.LocalDateTime;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.apifailoverandresilience.dto.CacheSyncRequest;
import com.apifailoverandresilience.dto.CacheSyncResponse;


@Service
public class CacheSyncService {

    private final RedisTemplate<String, Object> redisTemplate;

    public CacheSyncService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    
     // Store data in Redis
     
    public CacheSyncResponse syncCache(CacheSyncRequest request) {

        redisTemplate.opsForValue().set(
                request.getKey(),
                request.getValue());

        CacheSyncResponse response = new CacheSyncResponse();
        response.setKey(request.getKey());
        response.setValue(request.getValue());
        response.setRegion(request.getRegion());
        response.setMessage("Cache synchronized successfully");
        response.setSyncedAt(LocalDateTime.now());

        return response;
    }

    
     // Read cache
     
    public CacheSyncResponse getCache(String key) {

        Object value = redisTemplate.opsForValue().get(key);

        if (value == null) {
            throw new RuntimeException("Cache key not found");
        }

        CacheSyncResponse response = new CacheSyncResponse();
        response.setKey(key);
        response.setValue(value.toString());
        response.setMessage("Cache fetched successfully");
        response.setSyncedAt(LocalDateTime.now());

        return response;
    }

    
     // Update cache
     
    public CacheSyncResponse updateCache(String key, CacheSyncRequest request) {

        if (!Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            throw new RuntimeException("Cache key not found");
        }

        redisTemplate.opsForValue().set(key, request.getValue());

        CacheSyncResponse response = new CacheSyncResponse();
        response.setKey(key);
        response.setValue(request.getValue());
        response.setRegion(request.getRegion());
        response.setMessage("Cache updated successfully");
        response.setSyncedAt(LocalDateTime.now());

        return response;
    }

    
     // Delete cache
     
    public String deleteCache(String key) {

        if (!Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            throw new RuntimeException("Cache key not found");
        }

        redisTemplate.delete(key);

        return "Cache deleted successfully";
    }

    
     // Check cache exists
     
    public boolean cacheExists(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

}
