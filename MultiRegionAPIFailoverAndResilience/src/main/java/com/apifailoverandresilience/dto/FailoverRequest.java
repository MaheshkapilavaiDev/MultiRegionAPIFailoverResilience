package com.apifailoverandresilience.dto;

import jakarta.validation.constraints.NotNull;

public class FailoverRequest {

    @NotNull(message = "Region Id is required")
    private Long regionId;

    private String reason;

    public FailoverRequest() {
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}