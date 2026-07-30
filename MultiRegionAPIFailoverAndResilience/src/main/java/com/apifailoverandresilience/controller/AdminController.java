package com.apifailoverandresilience.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apifailoverandresilience.dto.CreateUserRequest;
import com.apifailoverandresilience.dto.MessageResponse;
import com.apifailoverandresilience.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private  AdminService adminService;


	@PostMapping("/users")
	public ResponseEntity<MessageResponse> createUser(@RequestBody CreateUserRequest request) {

		return ResponseEntity.ok(adminService.createUser(request));
	}
}
