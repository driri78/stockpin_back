package com.stockpin.project.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExternalApiService {

	private final TokenService tokenService;
	
	public void getStockQuotes() {
		
	}
	
}
