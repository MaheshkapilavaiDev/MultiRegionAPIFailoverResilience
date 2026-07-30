package com.apifailoverandresilience.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.apifailoverandresilience.dto.AuthResponse;
import com.apifailoverandresilience.dto.LoginRequest;
import com.apifailoverandresilience.dto.MessageResponse;
import com.apifailoverandresilience.dto.RegisterRequest;
import com.apifailoverandresilience.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/register")
	public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest request) {

		return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {

		AuthResponse response = authService.login(request);

		return ResponseEntity.ok(response);
	}

}
