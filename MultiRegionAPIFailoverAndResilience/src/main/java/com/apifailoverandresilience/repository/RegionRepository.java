package com.apifailoverandresilience.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apifailoverandresilience.entity.Region;
import com.apifailoverandresilience.enums.RegionStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

	Optional<Region> findByRegionName(String regionName);

    List<Region> findByStatus(RegionStatus status);

    List<Region> findAllByOrderByPriorityAsc();

	boolean existsByRegionCode(String regionCode);


    Optional<Region> findFirstByStatusOrderByPriorityAsc(String string);

}
