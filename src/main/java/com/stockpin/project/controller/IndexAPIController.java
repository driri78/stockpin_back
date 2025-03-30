package com.stockpin.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockpin.project.dto.index.IndexPriceDTO;
import com.stockpin.project.service.IndexFacadeService;
import com.stockpin.project.service.kis.ExternalIndexService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/index")
public class IndexAPIController {
	
	private final IndexFacadeService indexService;
	
	@GetMapping("test")
	public Mono<List<IndexPriceDTO>> getIndex() {
		return indexService.getIndexFiveMinutePrice("1001");
	}
	
	// 코스피
	@GetMapping("kospi")
	public Mono<List<IndexPriceDTO>> getKospiIndex() {
		return indexService.getIndexFiveMinutePrice("0001");
	}
	
	// 코스닥
	@GetMapping("kosdaq")
	public Mono<List<IndexPriceDTO>> getKosdaqIndex() {
		return indexService.getIndexFiveMinutePrice("1001");
	}
	
}
