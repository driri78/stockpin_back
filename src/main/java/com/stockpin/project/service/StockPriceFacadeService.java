package com.stockpin.project.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.stockpin.project.dto.stock.QuoteDaily;
import com.stockpin.project.dto.stock.info.StockInfoDTO;
import com.stockpin.project.dto.stock.price.ScreenerDTO;
import com.stockpin.project.dto.stock.price.StockPriceDTO;
import com.stockpin.project.dto.stock.price.TradeAmountDTO;
import com.stockpin.project.dto.stock.price.VolumeDTO;
import com.stockpin.project.service.kis.ExternalStockPriceService;
import com.stockpin.project.util.Converter;
import com.stockpin.project.util.Fomatter;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StockPriceFacadeService {
	private final ExternalStockPriceService externalStockPriceService;
	
	/*seq 
      0 : "거래대금 상위100"
      1 : "거래량 상위100"
      2 : "돈 잘버는 회사 찾기" : 매출액 이익률 20%이상, ROE 15%이상
      3 : "배당 상승주" : 배당 수익률 최근결산 기준 3퍼이상, 배당성향 최근결산 기준 5%이사 15%이하
      4 : "아직 저렴한 가치주" : PER 최근분기 기준 0배이상 15배 이하, PBR 최근분기 기준 0배이상 1.5배 이하, 순이익 증가율 최근 3년평균 기준 0%이상
      5 : "연속 성장주" : 5봉 이내에서 5봉 종가 상승
      6 : "저평가 상승주" : PER 최근분기 기준 0배이상 20배이하, 매출액 증가율 최근3년평균 기준 10%이상, 순이익 증가율 최근3년평균 기준 20%이상
      7 : "저평가 탈출주" : 5봉기간동안 종가 신고가 갱신, PER 최근분기 기준 0배이상 10배 이하, PBR 최근분기 기준 0배이상 1.5배 이하
	 */
	
	// 홈 => 거래대금 상위 100
    public Mono<List<StockPriceDTO<TradeAmountDTO>>> getTopRankedByTradeAmount(){
    	return externalStockPriceService.getStockList("0").flatMap(response -> {
			List<Map<String, String>> stockList = Converter.convertTolistOfMap(response.get("output2"));
			List<StockPriceDTO<TradeAmountDTO>> result = stockList.stream()
														 .map(stockData -> {
															 TradeAmountDTO tradeAmount = new TradeAmountDTO();
															 tradeAmount.setTradeAmout(Fomatter.parseLong(stockData.get("trade_amt")));
															
															 return StockPriceDTO.<TradeAmountDTO>builder()
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
	
	// 홈 => 거래량 상위 100
	public Mono<List<StockPriceDTO<VolumeDTO>>> getTopRankedByVolume(){
		return externalStockPriceService.getStockList("1").flatMap(response -> {
			List<Map<String, String>> stockList = Converter.convertTolistOfMap(response.get("output2"));
			List<StockPriceDTO<VolumeDTO>> result = stockList.stream()
													.map(stockData -> {
														VolumeDTO volume = new VolumeDTO();
														volume.setAcmlVol(Fomatter.parseLong(stockData.get("acml_vol")));
														
														return StockPriceDTO.<VolumeDTO>builder()
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
	
	// 주식모아보기 => Screener StockList
	public Mono<List<StockPriceDTO<ScreenerDTO>>> getScreenerStock(String idx){
		if(Fomatter.parseInt(idx) < 2 || Fomatter.parseInt(idx) > 8) {
			throw new RuntimeException("잘못된 idx값입니다...");
		}
		return externalStockPriceService.getStockList(idx).flatMap(response -> {
			List<Map<String, String>> stockList = Converter.convertTolistOfMap(response.get("output2"));
			List<StockPriceDTO<ScreenerDTO>> result = stockList.stream()
													  .map(stockData -> {
														  ScreenerDTO filterInfo = new ScreenerDTO();
														  filterInfo.setAcmlVol(Fomatter.parseLong(stockData.get("acml_vol")));
														  filterInfo.setStotPrice(Fomatter.parseLong(stockData.get("stotprice")));
														
														  return StockPriceDTO.<ScreenerDTO>builder()
																		  .name(stockData.get("name"))
																		  .code(stockData.get("code"))
																		  .price(Fomatter.parseLong(stockData.get("price")))
																		  .chgRate(Fomatter.parseDouble(stockData.get("chgrate")))
																		  .data(filterInfo)
																		  .build();
													  })
					.collect(Collectors.toList());
			return Mono.just(result);
		});
	}
	
	// detail => 시세
	public Mono<List<QuoteDaily>> getStockQuote(String startDate, String endDate, String period){
		return externalStockPriceService.getQuote(startDate, endDate, period).flatMap(response -> {
			List<Map<String, String>> stockList = Converter.convertTolistOfMap(response.get("output2"));
			List<QuoteDaily> result = stockList.stream()
											   .map(stockData -> {
												   return new QuoteDaily(stockData);
											   })
											   .collect(Collectors.toList());
			return Mono.just(result);
		});
	}
}