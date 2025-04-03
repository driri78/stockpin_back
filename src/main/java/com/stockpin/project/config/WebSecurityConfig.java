package com.stockpin.project.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import lombok.RequiredArgsConstructor;

@EnableWebFluxSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		return http.authorizeExchange(exchange -> 
			exchange
				.pathMatchers("/api/**").permitAll()
				.anyExchange().authenticated()
		).build();
	}

}
