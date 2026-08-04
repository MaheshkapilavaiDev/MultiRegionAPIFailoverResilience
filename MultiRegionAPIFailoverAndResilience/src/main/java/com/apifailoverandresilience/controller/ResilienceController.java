package com.apifailoverandresilience.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apifailoverandresilience.service.ResilienceService;

@RestController
@RequestMapping("/api/resilience")
public class ResilienceController {

	@Autowired
	private ResilienceService resilienceService;

	// Test All Resilience Features
	/*
	 * @GetMapping("/test") public CompletableFuture<ResponseEntity<String>>
	 * testResilience() {
	 * 
	 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 * 
	 * System.out.println("User = " + auth.getName());
	 * System.out.println("Authorities = " + auth.getAuthorities());
	 * 
	 * return resilienceService.callActiveRegion() .thenApply(ResponseEntity::ok); }
	 */

	@GetMapping("/test")
	public ResponseEntity<String> testResilience() throws Exception {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		System.out.println("User = " + auth.getName());
		System.out.println("Authorities = " + auth.getAuthorities());

		String result = resilienceService.callActiveRegion().get();

		return ResponseEntity.ok(result);
	}

	@GetMapping("/retry")
	public ResponseEntity<String> retry() throws Exception {

		return ResponseEntity.ok(resilienceService.retryExample().get());
	}

	@GetMapping("/circuit-breaker")
	public ResponseEntity<String> circuitBreaker() throws Exception {

		return ResponseEntity.ok(resilienceService.circuitBreakerExample().get());
	}

	@GetMapping("/rate-limiter")
	public ResponseEntity<String> rateLimiter() throws Exception {

		return ResponseEntity.ok(resilienceService.rateLimiterExample().get());
	}

	@GetMapping("/bulkhead")
	public ResponseEntity<String> bulkhead() throws Exception {

		return ResponseEntity.ok(resilienceService.bulkheadExample().get());
	}

	@GetMapping("/time-limiter")
	public ResponseEntity<String> timeLimiter() throws Exception {

		return ResponseEntity.ok(resilienceService.simulateSlowService().get());
	}

	@GetMapping("/active-region")
	public ResponseEntity<String> activeRegion() {

		return ResponseEntity.ok(resilienceService.getActiveRegionInfo());
	}

}
