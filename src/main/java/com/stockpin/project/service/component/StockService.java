package com.stockpin.project.service.component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.stockpin.project.dto.stock.Screener;
import com.stockpin.project.dto.stock.TradeAmount;
import com.stockpin.project.dto.stock.Volume;
import com.stockpin.project.dto.stock.basic.StockInfo;
import com.stockpin.project.service.module.ExternalApiService;
import com.stockpin.project.util.Fomatter;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StockService {
	private final ExternalApiService externalApiService;
	
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
	
	// 거래대금 상위 100
    public Mono<List<StockInfo<TradeAmount>>> getTopRankedByTradeAmount(){
    	return externalApiService.getStockList("0").flatMap(response -> {
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
	
	// 거래량 상위 100 
	public Mono<List<StockInfo<Volume>>> getTopRankedByVolume(){
		return externalApiService.getStockList("1").flatMap(response -> {
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
	
	public Mono<List<StockInfo<Screener>>> getScreenerStock(String idx){
		if(Fomatter.parseInt(idx) < 2 || Fomatter.parseInt(idx) > 8) {
			throw new RuntimeException("잘못된 idx값입니다...");
		}
		return externalApiService.getStockList(idx).flatMap(response -> {
			List<Map<String, String>> res = (List<Map<String, String>>) response.get("output2");
			List<StockInfo<Screener>> result = res.stream()
												  .map(stockData -> {
													  Screener filterInfo = new Screener();
													  filterInfo.setAcmlVol(Fomatter.parseLong(stockData.get("acml_vol")));
													  filterInfo.setStotPrice(Fomatter.parseLong(stockData.get("stotprice")));
													
													  return StockInfo.<Screener>builder()
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
}
