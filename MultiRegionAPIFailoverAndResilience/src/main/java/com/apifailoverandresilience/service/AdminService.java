package com.apifailoverandresilience.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apifailoverandresilience.dto.CreateUserRequest;
import com.apifailoverandresilience.dto.MessageResponse;
import com.apifailoverandresilience.entity.Role;
import com.apifailoverandresilience.entity.User;
import com.apifailoverandresilience.repository.RoleRepository;
import com.apifailoverandresilience.repository.UserRepository;

@Service
public class AdminService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public MessageResponse createUser(CreateUserRequest request) {

		Role role = roleRepository.findByName(request.getRole())
				.orElseThrow(() -> new RuntimeException("Role not found"));

		User user = new User();

		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setEnabled(true);
		user.setRoles(Set.of(role));

		userRepository.save(user);

		return new MessageResponse("User created successfully");
	}

}
