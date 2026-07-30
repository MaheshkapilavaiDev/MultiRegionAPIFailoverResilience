package com.apifailoverandresilience.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apifailoverandresilience.config.CustomUserDetails;
import com.apifailoverandresilience.config.JwtService;
import com.apifailoverandresilience.dto.AuthResponse;
import com.apifailoverandresilience.dto.LoginRequest;
import com.apifailoverandresilience.dto.MessageResponse;
import com.apifailoverandresilience.dto.RegisterRequest;
import com.apifailoverandresilience.entity.Role;
import com.apifailoverandresilience.entity.User;
import com.apifailoverandresilience.repository.RoleRepository;
import com.apifailoverandresilience.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private  AuditLogService auditLogService;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private MonitoringService monitoringService;
	
	public MessageResponse register(RegisterRequest request) {

		if (userRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email already exists");
		}

		Role role = roleRepository.findByName("ROLE_USER")
				.orElseThrow(() -> new RuntimeException("ROLE_USER not found"));

		Set<Role> roles = new HashSet<>();
		roles.add(role);

		User user = new User();

		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setEnabled(true);
		user.setRoles(roles);

		userRepository.save(user);
		
		auditLogService.saveAuditLog(
		        request.getEmail(),
		        "REGISTER",
		        "AUTH",
		        "User registered successfully",
		        "127.0.0.1",
		        "SUCCESS");

		return new MessageResponse("User Registered Successfully");
	}

	public AuthResponse login(LoginRequest request) {

	    return monitoringService.recordResponseTime(() -> {

	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        request.getEmail(),
	                        request.getPassword()));

	        CustomUserDetails userDetails =
	                (CustomUserDetails) authentication.getPrincipal();

	        String token = jwtService.generateToken(userDetails);

	        auditLogService.saveAuditLog(
	                request.getEmail(),
	                "LOGIN",
	                "AUTH",
	                "User logged into the application",
	                "127.0.0.1",
	                "SUCCESS");

	        return new AuthResponse(token, "Login Successful");
	    });
	}
}
