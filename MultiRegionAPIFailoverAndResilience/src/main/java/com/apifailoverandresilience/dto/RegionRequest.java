package com.apifailoverandresilience.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegionRequest {

    @NotBlank(message = "Region name is required")
    private String regionName;

    @NotBlank(message = "Region code is required")
    private String regionCode;

    @NotBlank(message = "Base URL is required")
    private String baseUrl;

    @NotNull(message = "Priority is required")
    @Min(value = 1, message = "Priority must be greater than 0")
    @Max(value = 10, message = "Priority cannot exceed 10")
    private Integer priority;

    @NotBlank(message = "Mode is required")
    private String mode;

    @NotBlank(message = "Status is required")
    private String status;

    @NotBlank(message = "Health is required")
    private String health;

    public RegionRequest() {
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }
}