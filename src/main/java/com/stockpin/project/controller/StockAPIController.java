package com.stockpin.project.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockpin.project.dto.detail.QuoteDaily;
import com.stockpin.project.dto.stock.price.Screener;
import com.stockpin.project.dto.stock.price.TradeAmount;
import com.stockpin.project.dto.stock.price.Volume;
import com.stockpin.project.service.GetExcelDataService;
import com.stockpin.project.service.component.StockPriceService;
import com.stockpin.project.service.module.ExternalInfoApiService;
import com.stockpin.project.service.module.ExternalPriceApiService;
import com.stockpin.project.service.module.TokenService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class StockAPIController {

	private final TokenService tokenService;
	
	private final GetExcelDataService getExcelDataService;
	
	private final ExternalPriceApiService externalPriceApiService;
	
	private final ExternalInfoApiService externalInfoApiService;
	
	private final StockPriceService stockService;
	
	@GetMapping("test")
	public Mono<Map<String, Object>> getStockDetail1() {
		return externalInfoApiService.getStockInfo("005930");
	}
	
	@GetMapping("detail")
	public Mono<Map<String, Object>> getStockDetail(@RequestParam("code")String code) {
		// J = 한국거래소, NX = Nextrade UN = 통합
		return externalPriceApiService.getStock("J",code);
	}
	
	@GetMapping("detail/daily")
	public Mono<Map<String, Object>> getStockDetail(@RequestParam("code")String code, @RequestParam("period")String period) {
		// J = 한국거래소, NX = Nextrade UN = 통합
		return externalPriceApiService.getStock("J", code, period);
	}

	@GetMapping("list1")
	public Mono<Map<String, Object>> getStockList1() {
		return externalPriceApiService.getStockList("0");
	}
	
	@GetMapping("criteria")
	public Mono<Map<String, Object>> getStockCriteriaList() {
		return externalPriceApiService.getCriteriaList();
	}
	
	// ------------------------------------------------------------------------------------
	
	
}
