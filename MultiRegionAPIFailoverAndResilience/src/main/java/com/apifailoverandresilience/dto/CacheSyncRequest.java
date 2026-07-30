package com.apifailoverandresilience.dto;

import jakarta.validation.constraints.NotBlank;

public class CacheSyncRequest {

    @NotBlank(message = "Cache key is required")
    private String key;

    @NotBlank(message = "Cache value is required")
    private String value;

    @NotBlank(message = "Region name is required")
    private String region;

    public CacheSyncRequest() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}