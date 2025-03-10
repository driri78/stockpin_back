package com.stockpin.project.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockpin.project.dto.stock.QuoteDaily;
import com.stockpin.project.dto.stock.info.StockInfoDTO;
import com.stockpin.project.dto.stock.price.ScreenerDTO;
import com.stockpin.project.dto.stock.price.StockPriceDTO;
import com.stockpin.project.dto.stock.price.TradeAmountDTO;
import com.stockpin.project.dto.stock.price.VolumeDTO;
import com.stockpin.project.service.component.StockPriceService;
import com.stockpin.project.service.module.ExternalStockPriceService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock/price")
public class StockPriceAPIController {

	private final StockPriceService stockPriceService;
	
	private final ExternalStockPriceService externalApiService;
	
	@GetMapping("test")
	public Mono<Map<String, Object>> getStockCriteriaList() {
		return externalApiService.getCriteriaList();
	}

	// -----------------------------------------------------------------------
	
	// 홈 => 거래량 순위
	@GetMapping("volume/top")
	public Mono<List<StockPriceDTO<VolumeDTO>>> getStocksRankedByVolume() {
		return stockPriceService.getTopRankedByVolume();
	}
	
	// 홈 => 거래대금 순위
	@GetMapping("trade-amount/top")
	public Mono<List<StockPriceDTO<TradeAmountDTO>>> getStocksRankedByTradeAmount() {
		return stockPriceService.getTopRankedByTradeAmount();
	}
	
	// 주식모아보기 => stockPin 추천필터
	@GetMapping("screener/{idx}")
	public Mono<List<StockPriceDTO<ScreenerDTO>>> getFilterStocks(@PathVariable("idx")String idx) {
		return stockPriceService.getScreenerStock(idx);
	}
	
	// 주식상세보기 => 일별 시세
	@GetMapping("quote/{period}")
	public Mono<List<QuoteDaily>> getStocksQuoteDaily(@PathVariable("period")String period){
		return stockPriceService.getStockQuote(String.valueOf(LocalDateTime.now().minusDays(100).format(DateTimeFormatter.ofPattern("YYYYMMdd"))), 
				  String.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMdd"))), period);
	}
	
}
