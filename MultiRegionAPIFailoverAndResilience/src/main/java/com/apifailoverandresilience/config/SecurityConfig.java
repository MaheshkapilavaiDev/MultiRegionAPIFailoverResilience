package com.apifailoverandresilience.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtFilter;
	private final JwtAuthenticationEntryPoint authenticationEntryPoint;

	public SecurityConfig(JwtAuthenticationFilter jwtFilter, JwtAuthenticationEntryPoint authenticationEntryPoint) {

		this.jwtFilter = jwtFilter;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http

				.csrf(csrf -> csrf.disable())

				.cors(Customizer.withDefaults())

				.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))

				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.authorizeHttpRequests(auth -> auth

						.requestMatchers( "/auth/**",
							    "/swagger-ui.html",
							    "/swagger-ui/**",
							    "/api-docs",
							    "/api-docs/**", "/actuator/**").permitAll()
						.requestMatchers("/admin/**",
								"/regions/**",
								"/api/resilience/**",
					            "/api/failover/**",
					           // "/api/failback/**",
					            "/metrics/**",
					            "/api/cache/sync",
					            "/api/cache/**",
					            "/audit-logs/**",
					            "/system/**",
					            "/api/monitoring/**",
					            "/api/health/**"
					            ).hasRole("ADMIN")
						// User APIs
					    .requestMatchers("/user/**")
					    .hasRole("USER")

						.anyRequest().authenticated()

				)

				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

		return configuration.getAuthenticationManager();

	}
	@Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

}
