package com.apifailoverandresilience.entity;

import com.apifailoverandresilience.enums.CacheStatus;

import jakarta.persistence.*;

@Entity
@Table(name="cache_sync")
public class CacheSync {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String cacheKey;

    private String sourceRegion;

    private String destinationRegion;

    @Enumerated(EnumType.STRING)
    private CacheStatus status;

    private String syncTime;

    public CacheSync() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCacheKey() {
		return cacheKey;
	}

	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}

	public String getSourceRegion() {
		return sourceRegion;
	}

	public void setSourceRegion(String sourceRegion) {
		this.sourceRegion = sourceRegion;
	}

	public String getDestinationRegion() {
		return destinationRegion;
	}

	public void setDestinationRegion(String destinationRegion) {
		this.destinationRegion = destinationRegion;
	}

	public CacheStatus getStatus() {
		return status;
	}

	public void setStatus(CacheStatus status) {
		this.status = status;
	}

	public String getSyncTime() {
		return syncTime;
	}

	public void setSyncTime(String syncTime) {
		this.syncTime = syncTime;
	}

   

}
