package com.apifailoverandresilience.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apifailoverandresilience.entity.RequestLog;

@Repository
public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {

}
