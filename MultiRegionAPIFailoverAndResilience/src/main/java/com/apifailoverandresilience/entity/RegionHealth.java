package com.apifailoverandresilience.entity;

import com.apifailoverandresilience.enums.HealthStatus;

import jakarta.persistence.*;

@Entity
@Table(name="region_health")
public class RegionHealth {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="region_id")
    private Region region;

    @Enumerated(EnumType.STRING)
    private HealthStatus healthStatus;

    private Long latency;

    private Double cpuUsage;

    private Double memoryUsage;

    private String lastChecked;

    public RegionHealth() {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public HealthStatus getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(HealthStatus healthStatus) {
		this.healthStatus = healthStatus;
	}

	public Long getLatency() {
		return latency;
	}

	public void setLatency(Long latency) {
		this.latency = latency;
	}

	public Double getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(Double cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public Double getMemoryUsage() {
		return memoryUsage;
	}

	public void setMemoryUsage(Double memoryUsage) {
		this.memoryUsage = memoryUsage;
	}

	public String getLastChecked() {
		return lastChecked;
	}

	public void setLastChecked(String lastChecked) {
		this.lastChecked = lastChecked;
	}

    

}