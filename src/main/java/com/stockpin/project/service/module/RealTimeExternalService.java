package com.stockpin.project.service.module;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RealTimeExternalService {

	private final TokenService tokenService;
	
}
