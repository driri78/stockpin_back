package com.stockpin.project.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockpin.project.dto.detail.QuoteDaily;
import com.stockpin.project.dto.stock.price.Screener;
import com.stockpin.project.dto.stock.price.StockInfo;
import com.stockpin.project.dto.stock.price.TradeAmount;
import com.stockpin.project.dto.stock.price.Volume;
import com.stockpin.project.service.component.StockPriceService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock/price")
public class StockPriceAPIController {

	private final StockPriceService stockPriceService;
	
	@GetMapping("volume/top")
	public Mono<List<StockInfo<Volume>>> getStocksRankedByVolume() {
		return stockPriceService.getTopRankedByVolume();
	}

	@GetMapping("trade-amount/top")
	public Mono<List<StockInfo<TradeAmount>>> getStocksRankedByTradeAmount() {
		return stockPriceService.getTopRankedByTradeAmount();
	}
	
	@GetMapping("screener/{idx}")
	public Mono<List<StockInfo<Screener>>> getFilterStocks(@PathVariable("idx")String idx) {
		return stockPriceService.getScreenerStock(idx);
	}
	
	@GetMapping("quote/{period}")
	public Mono<List<QuoteDaily>> getStocksQuoteDaily(@PathVariable("period")String period){
		return stockPriceService.getStockQuote(String.valueOf(LocalDateTime.now().minusDays(100).format(DateTimeFormatter.ofPattern("YYYYMMdd"))), 
				  String.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMdd"))), period);
	}
	
}
