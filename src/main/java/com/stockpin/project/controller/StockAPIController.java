package com.stockpin.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockpin.project.dto.stock.Screener;
import com.stockpin.project.dto.stock.TradeAmount;
import com.stockpin.project.dto.stock.Volume;
import com.stockpin.project.dto.stock.basic.StockInfo;
import com.stockpin.project.service.GetExcelDataService;
import com.stockpin.project.service.component.StockService;
import com.stockpin.project.service.module.ExternalApiService;
import com.stockpin.project.service.module.TokenService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class StockAPIController {

	private final TokenService tokenService;
	
	private final GetExcelDataService getExcelDataService;
	
	
	private final ExternalApiService externalApiService;
	
	private final StockService stockService;
	
	@GetMapping("detail")
	public Mono<Map<String, Object>> getStockDetail(@RequestParam("code")String code) {
		// J = 한국거래소, NX = Nextrade UN = 통합
		return externalApiService.getStock("J",code);
	}
	
	@GetMapping("detail/daily")
	public Mono<Map<String, Object>> getStockDetail(@RequestParam("code")String code, @RequestParam("period")String period) {
		// J = 한국거래소, NX = Nextrade UN = 통합
		
		return externalApiService.getStock("J", code, period);
	}

	@GetMapping("list1")
	public Mono<Map<String, Object>> getStockList1() {
		return externalApiService.getStockList("0");
	}
	
	@GetMapping("criteria")
	public Mono<Map<String, Object>> getStockCriteriaList() {
		return externalApiService.getCriteriaList();
	}
	
	@GetMapping("volume/top")
	public Mono<List<StockInfo<Volume>>> getStocksRankedByVolume() {
		return stockService.getTopRankedByVolume();
	}

	@GetMapping("trade-amount/top")
	public Mono<List<StockInfo<TradeAmount>>> getStocksRankedByTradeAmount() {
		return stockService.getTopRankedByTradeAmount();
	}
	
	@GetMapping("screener/{idx}")
	public Mono<List<StockInfo<Screener>>> getFilterStocks(@PathVariable("idx")String idx) {
		return stockService.getScreenerStock(idx);
	}
	
}
