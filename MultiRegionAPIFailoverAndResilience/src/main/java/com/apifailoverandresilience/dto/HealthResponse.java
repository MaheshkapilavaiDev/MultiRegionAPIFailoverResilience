package com.apifailoverandresilience.dto;

public class HealthResponse {

    private String component;
    private String status;
    private String message;

    public HealthResponse() {
    }

    public HealthResponse(String component, String status, String message) {
        this.component = component;
        this.status = status;
        this.message = message;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
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
}