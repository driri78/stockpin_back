package com.stockpin.project.service.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.stockpin.project.dto.stock.StockInfo;
import com.stockpin.project.dto.stock.TradeAmount;
import com.stockpin.project.dto.stock.Volume;
import com.stockpin.project.service.module.ExternalApiService;
import com.stockpin.project.util.Fomatter;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StockService {
	
	/*seq 
	 * 0 연속상승주
	 * 1 거래대금 
	 * 2 거래량
	 */
	
	private final ExternalApiService externalApiService;
	
	// 거래량 상위 100 
	public Mono<List<StockInfo<Volume>>> getTopRankedByVolume(){
		return externalApiService.getStockList("2").flatMap(response -> {
			List<Map<String, String>> res = (List<Map<String, String>>) response.get("output2");
			List<StockInfo<Volume>> result = res.stream()
												.map(stockData -> {
													Volume volume = new Volume();
													volume.setAcmlVol(Fomatter.parseLong(stockData.get("acml_vol")));
													
													return StockInfo.<Volume>builder()
																	.name(stockData.get("name"))
																	.code(stockData.get("code"))
																	.price(Fomatter.parseLong(stockData.get("price")))
																	.chgRate(Fomatter.parseDouble(stockData.get("chgrate")))
																	.data(volume)
																	.build();
												})
												.collect(Collectors.toList());
			return Mono.just(result);
		});
	}
	
	// 거래대금 상위 100
    public Mono<List<StockInfo<TradeAmount>>> getTopRankedByTradeAmount(){
    	return externalApiService.getStockList("1").flatMap(response -> {
			List<Map<String, String>> res = (List<Map<String, String>>) response.get("output2");
			List<StockInfo<TradeAmount>> result = res.stream()
												.map(stockData -> {
													TradeAmount tradeAmount = new TradeAmount();
													tradeAmount.setTradeAmout(Fomatter.parseLong(stockData.get("trade_amt")));
													
													return StockInfo.<TradeAmount>builder()
																	.name(stockData.get("name"))
																	.code(stockData.get("code"))
																	.price(Fomatter.parseLong(stockData.get("price")))
																	.chgRate(Fomatter.parseDouble(stockData.get("chgrate")))
																	.data(tradeAmount)
																	.build();
												})
												.collect(Collectors.toList());
			return Mono.just(result);
		});
    }
	 
	
}
