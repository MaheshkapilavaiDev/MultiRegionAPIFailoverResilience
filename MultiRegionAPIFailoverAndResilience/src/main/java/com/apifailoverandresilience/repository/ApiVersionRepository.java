package com.apifailoverandresilience.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apifailoverandresilience.entity.ApiVersion;

@Repository
public interface ApiVersionRepository extends JpaRepository<ApiVersion, Long> {

	Optional<ApiVersion> findByVersion(String version);

}
