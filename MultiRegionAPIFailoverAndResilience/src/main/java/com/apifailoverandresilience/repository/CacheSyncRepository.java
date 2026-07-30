package com.apifailoverandresilience.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apifailoverandresilience.entity.CacheSync;

@Repository
public interface CacheSyncRepository extends JpaRepository<CacheSync, Long>{

}
