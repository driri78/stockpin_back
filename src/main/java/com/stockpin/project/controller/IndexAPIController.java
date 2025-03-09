package com.stockpin.project.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockpin.project.service.module.ExternalIndexService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/index")
public class IndexAPIController {
	
	private final ExternalIndexService externalIndexService;
	
	@GetMapping("test")
	public Mono<Map<String, Object>> getIndex() {
		return externalIndexService.getIndex();
	}
	
}
