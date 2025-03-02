package com.stockpin.project.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockpin.project.service.GetExcelDataService;
import com.stockpin.project.service.component.StockService;
import com.stockpin.project.service.module.TokenService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class StockAPIController {

	private final TokenService tokenService;
	
	private final GetExcelDataService getExcelDataService;
	
	private final StockService stockService;
	
	@GetMapping("detail")
	public Mono<Map<String, Object>> getStockDetailInfo(@RequestParam("code")String code) {
		// J = 한국거래소, NX = Nextrade UN = 통합
		return stockService.getStockData("J",code);
	}
	
}
