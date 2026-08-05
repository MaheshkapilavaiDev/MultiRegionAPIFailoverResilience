# Multi-Region API Failover & Resilience System

## Overview

The **Multi-Region API Failover & Resilience System** is a Spring Boot application designed to provide high availability, fault tolerance, and resilience for distributed services. The application automatically switches traffic between multiple regions during failures and ensures uninterrupted service using Resilience4j patterns.

---

# Features

- JWT Authentication & Authorization
- Role-Based Access Control (RBAC)
- Region Management (CRUD)
- Automatic Region Failover
- Automatic Region Failback
- Active Region Monitoring
- Retry Pattern
- Circuit Breaker Pattern
- Rate Limiter
- Bulkhead Pattern
- Time Limiter
- Redis Cache Integration
- Cross-Region Cache Synchronization
- Monitoring Dashboard APIs
- Health Check APIs
- Audit Logging
- Pagination & Filtering
- API Versioning
- Global Exception Handling
- Swagger Documentation
- Spring Boot Actuator
- Docker Support
- Unit Testing using JUnit & Mockito

---

# Technology Stack

- Java 21
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- Redis
- Resilience4j
- JWT
- Maven
- Docker
- Swagger / OpenAPI
- Spring Boot Actuator
- JUnit 5
- Mockito

---

# Project Structure

```
src/main/java
│
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── service
├── util
└── security
```

---

# Modules

## Authentication

- User Registration
- User Login
- JWT Token Generation
- JWT Authentication
- Role-Based Authorization

### APIs

| Method | Endpoint |
|----------|-------------------------|
| POST | /auth/register |
| POST | /auth/login |

---

## Region Management

- Create Region
- Update Region
- Delete Region
- Get Region
- Get All Regions

### APIs

| Method | Endpoint |
|----------|-------------------------|
| POST | /regions |
| GET | /regions |
| GET | /regions/{id} |
| PUT | /regions/{id} |
| DELETE | /regions/{id} |

---

## Failover Module

- Manual Failover
- Manual Failback
- Active Region Status

### APIs

| Method | Endpoint |
|----------|----------------------------|
| POST | /api/failover/failover |
| POST | /api/failover/failback/{id} |
| GET | /api/system/status |

---

## Resilience Module

Implemented using **Resilience4j**

### Retry

Retries failed requests automatically.

```
GET /api/resilience/retry
```

---

### Circuit Breaker

Prevents repeated calls to failing services.

```
GET /api/resilience/circuit-breaker
```

---

### Rate Limiter

Limits excessive client requests.

```
GET /api/resilience/rate-limiter
```

---

### Bulkhead

Limits concurrent requests using Thread Pool.

```
GET /api/resilience/bulkhead
```

---

### Time Limiter

Returns fallback response when execution exceeds timeout.

```
GET /api/resilience/time-limiter
```

---

### Active Region

```
GET /api/resilience/active-region
```

---

### Test Endpoint

```
GET /api/resilience/test
```

---

## Cache Module

Redis Cache Integration

### APIs

| Method | Endpoint |
|----------|------------------------|
| POST | /api/cache/sync |
| GET | /api/cache/regions |

---

## Monitoring Module

Tracks application statistics.

### APIs

| Method | Endpoint |
|----------|-------------------------------|
| GET | /api/monitoring/dashboard |
| GET | /api/monitoring/stats |

Features

- Total Requests
- Successful Requests
- Failed Requests
- Average Response Time

---

## Health Module

Checks application health.

### API

```
GET /api/health
```

Returns

- Database Status
- Redis Status
- Application Status

---

## Audit Log Module

Tracks user activities.

Captured Events

- Login
- Registration
- Create Region
- Update Region
- Delete Region
- Failover
- Failback
- Cache Sync

### APIs

| Method | Endpoint |
|----------|----------------------------|
| GET | /audit-logs |
| GET | /audit-logs/{id} |
| GET | /audit-logs/filter |

Supports

- Pagination
- Date Filtering

---

## Swagger

```
http://localhost:8080/swagger-ui.html
```

---

## Actuator

### Health

```
GET /actuator/health
```

### Metrics

```
GET /actuator/metrics
```

### Prometheus

```
GET /actuator/prometheus
```

---

# Database

Database

```
MySQL
```

Database Name

```
multiregiondb
```

---

# Redis

Host

```
localhost
```

Port

```
6379
```

---

# Build

```
mvn clean install
```

---

# Run

```
mvn spring-boot:run
```

---

# Docker

Build

```
docker build -t multiregion-api .
```

Run

```
docker run -p 8080:8080 multiregion-api
```

---

# Security

- JWT Authentication
- BCrypt Password Encoder
- Stateless Session
- Role-Based Access Control
- Protected REST APIs

---

# Global Exception Handling

Handles

- Resource Not Found
- Validation Errors
- Unauthorized Access
- Forbidden Access
- Runtime Exceptions
- Internal Server Errors

---

# Testing

API Testing

- Postman

---

# End-to-End Flow

1. Register User
2. Login and Generate JWT
3. Create Regions
4. View Regions
5. Perform Failover
6. Verify Active Region
7. Perform Failback
8. Synchronize Redis Cache
9. View Monitoring Dashboard
10. Check Application Health
11. View Audit Logs
12. Test Retry
13. Test Circuit Breaker
14. Test Rate Limiter
15. Test Bulkhead
16. Test Time Limiter
17. Verify Actuator Endpoints
18. Verify Swagger Documentation

---

# Author

**Mahesh Kapilavai**

Java Backend Developer