package com.apifailoverandresilience.dto;

import java.time.LocalDateTime;

public class FailoverResponse {

    private String sourceRegion;
    private String targetRegion;
    private String baseUrl;
    private String status;
    private String message;
    private LocalDateTime failoverTime;

    public FailoverResponse() {
    }

    public String getSourceRegion() {
        return sourceRegion;
    }

    public void setSourceRegion(String sourceRegion) {
        this.sourceRegion = sourceRegion;
    }

    public String getTargetRegion() {
        return targetRegion;
    }

    public void setTargetRegion(String targetRegion) {
        this.targetRegion = targetRegion;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getFailoverTime() {
        return failoverTime;
    }

    public void setFailoverTime(LocalDateTime failoverTime) {
        this.failoverTime = failoverTime;
    }
}
