package com.apifailoverandresilience.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import com.apifailoverandresilience.dto.AuditLogResponse;
import com.apifailoverandresilience.entity.AuditLog;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long>{

	Page<AuditLog> findByUsername(String username, Pageable pageable);

	Page<AuditLog> findByAction(String action, Pageable pageable);

	Page<AuditLog> findByModuleName(String moduleName, Pageable pageable);

	Page<AuditLog> findByStatus(String status, Pageable pageable);

	Page<AuditLog> findByCreatedAtBetween(LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable);

}
