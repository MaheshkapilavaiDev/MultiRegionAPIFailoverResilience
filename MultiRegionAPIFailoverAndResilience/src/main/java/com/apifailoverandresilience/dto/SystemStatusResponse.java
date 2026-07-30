package com.apifailoverandresilience.dto;

import java.util.List;

public class SystemStatusResponse {

    private String activeRegion;

    private Integer healthyRegions;

    private Integer failedRegions;

    private List<HealthResponse> regions;

    public SystemStatusResponse() {
    }

	public String getActiveRegion() {
		return activeRegion;
	}

	public void setActiveRegion(String activeRegion) {
		this.activeRegion = activeRegion;
	}

	public Integer getHealthyRegions() {
		return healthyRegions;
	}

	public void setHealthyRegions(Integer healthyRegions) {
		this.healthyRegions = healthyRegions;
	}

	public Integer getFailedRegions() {
		return failedRegions;
	}

	public void setFailedRegions(Integer failedRegions) {
		this.failedRegions = failedRegions;
	}

	public List<HealthResponse> getRegions() {
		return regions;
	}

	public void setRegions(List<HealthResponse> regions) {
		this.regions = regions;
	}

}
