package com.stockpin.project.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockpin.project.dto.stock.info.StockInfoDTO;
import com.stockpin.project.service.GetExcelDataService;
import com.stockpin.project.service.component.StockService;
import com.stockpin.project.service.module.ExternalApiService;
import com.stockpin.project.service.module.TokenService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class StockInfoAPIController {

	private final TokenService tokenService;
	
	private final GetExcelDataService getExcelDataService;
	
	private final ExternalApiService externalApiService;
	
	private final StockService stockService;
	
	@GetMapping("test")
	public Mono<StockInfoDTO> getStockDetail1() {
		return stockService.getStockInfo("300","005930");
	}
	
	// ------------------------------------------------------------------------------------
	
	// 주식 상세보기 => 종목 정보
	@GetMapping("info")
	public Mono<StockInfoDTO> getStockDetailInfo() {
		return stockService.getStockInfo("300","005930");
	}
	
}
