package com.BookReviewPla.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpFilter;

@Configuration
public class SecurityFilterChainConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf().disable()
			.authorizeHttpRequests()
			.requestMatchers("/api/v1/**").permitAll()
			.anyRequest()
			.authenticated();
		return httpSecurity.build();
		
	}

}
