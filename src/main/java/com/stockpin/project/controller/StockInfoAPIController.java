package com.stockpin.project.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockpin.project.dto.stock.info.StockInfoDTO;
import com.stockpin.project.service.GetExcelDataService;
import com.stockpin.project.service.StockInfoFacadeService;
import com.stockpin.project.service.kis.ExternalStockInfoService;
import com.stockpin.project.service.kis.ExternalStockPriceService;
import com.stockpin.project.service.kis.TokenService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class StockInfoAPIController {

	private final TokenService tokenService;
	
	private final GetExcelDataService getExcelDataService;
	
	private final ExternalStockPriceService externalApiService;
	private final ExternalStockInfoService externalStockInfoService;
	private final StockInfoFacadeService stockInfoService;
	
	@GetMapping("test")
	public Mono<Map<String, Object>> getStockDetail1() {
		return externalStockInfoService.getInfo("300","005930");
	}
	
	// ------------------------------------------------------------------------------------
	
	// 주식 상세보기 => 종목 정보
	@GetMapping("info")
	public Mono<StockInfoDTO> getStockDetailInfo() {
		return stockInfoService.getStockInfo("300","005930");
	}
	
}
