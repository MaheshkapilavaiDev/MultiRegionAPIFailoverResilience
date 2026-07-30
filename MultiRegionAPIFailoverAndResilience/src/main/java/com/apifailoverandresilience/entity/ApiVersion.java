package com.apifailoverandresilience.entity;

import com.apifailoverandresilience.enums.ApiVersionStatus;

import jakarta.persistence.*;

@Entity
@Table(name="api_versions")
public class ApiVersion {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String version;

    @Enumerated(EnumType.STRING)
    private ApiVersionStatus status;

    private String releaseDate;

    public ApiVersion() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public ApiVersionStatus getStatus() {
		return status;
	}

	public void setStatus(ApiVersionStatus status) {
		this.status = status;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

}
