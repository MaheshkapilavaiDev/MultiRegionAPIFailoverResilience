package com.apifailoverandresilience.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apifailoverandresilience.entity.Region;
import com.apifailoverandresilience.entity.RegionHealth;

@Repository
public interface RegionHealthRepository extends JpaRepository<RegionHealth, Long>{

    Optional<RegionHealth> findByRegion(Region region);

}
