package com.stockpin.project.service.module;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.stockpin.project.config.APIConfig;
import com.stockpin.project.util.ServiceUtility;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExternalApiService {
	private final TokenService tokenService;
	private final APIConfig apiConfig;
	
	String URL = "https://openapi.koreainvestment.com:9443";
	
	// 기본시세 => 주식현재가 일자별
	public Mono<Map<String, Object>> getStock(String marketCode, String stockCode, String period) {
		String trId = "FHKST01010400";
		
		return tokenService.getToken().flatMap(token ->{
			WebClient webClient = WebClient.builder()
										   .baseUrl(URL)
										   .build();
			return webClient.get()
						    .uri(uri -> uri.path("/uapi/domestic-stock/v1/quotations/inquire-daily-price")
						 		   	 	.queryParam("FID_COND_MRKT_DIV_CODE", marketCode)
						 		   	 	.queryParam("FID_INPUT_ISCD", stockCode)
						 		   	 	.queryParam("FID_PERIOD_DIV_CODE", period)
						 		   	 	.queryParam("FID_ORG_ADJ_PRC", "1")
						 		   	 	.build()
						    )
						    .headers(header -> ServiceUtility.setRequestHeader(header, token, trId, apiConfig))
						    .retrieve()
						    .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>(){})
						    .onErrorResume(e -> Mono.error(new RuntimeException("ERR : getStock(period)")));
			});
	}
	
	// 시세분석 => 종목조건검색 목록조회
	public Mono<Map<String, Object>> getCriteriaList() {
		String userId = apiConfig.getUserId();
		String trId = "HHKST03900300";
		
		return tokenService.getToken().flatMap(token ->{
			WebClient webClient = WebClient.builder()
										   .baseUrl(URL)
										   .build();
			return webClient.get()
					 .uri(uri -> uri.path("/uapi/domestic-stock/v1/quotations/psearch-title")
					 		   	 	.queryParam("user_id", userId)
					 		   	 	.build()
					 )
					 .headers(header -> ServiceUtility.setRequestHeader(header, token, trId, apiConfig))
					 .retrieve()
					 .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>(){})
					 .onErrorResume(e -> Mono.error(new RuntimeException("ERR : getCriteriaList")));
			});
		
	}
	
	// 시세분석 => 종목조건검색조회
	public Mono<Map<String, Object>> getStockList(String seq) {
		String userId = apiConfig.getUserId();
		String trId = "HHKST03900400";
		
		return tokenService.getToken().flatMap(token ->{
			WebClient webClient = WebClient.builder()
										   .baseUrl(URL)
										   .build();
			return webClient.get()
						 .uri(uri -> uri.path("/uapi/domestic-stock/v1/quotations/psearch-result")
						 		   	 	.queryParam("user_id", userId)
						 		   	 	.queryParam("seq", seq)
						 		   	 	.build()
						 )
						 .headers(header -> ServiceUtility.setRequestHeader(header, token, trId, apiConfig))
						 .retrieve()
						 .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>(){})
						 .onErrorResume(e -> Mono.error(new RuntimeException("ERR : getStockList")));
			});
	}
	
	// 기본시세 => 주식당일분봉조회
	public Mono<Map<String,Object>> getMinuteCandlestick(){
		String trId = "FHKST03010200";
		
		return tokenService.getToken().flatMap(token -> {
			WebClient webClient = WebClient.builder()
										   .baseUrl(URL)
										   .build();
			return webClient.get()
					 		.uri(uri -> uri.path("/uapi/domestic-stock/v1/quotations/inquire-time-itemchartprice")
					 				       .queryParam("FID_COND_MRKT_DIV_CODE", "J")
					 				       .queryParam("FID_INPUT_ISCD", "005930")
					 				       .queryParam("FID_INPUT_HOUR_1", String.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"))))
					 				       .queryParam("FID_PW_DATA_INCU_YN", "Y")
					 				       .queryParam("FID_ETC_CLS_CODE", "00")
					 				       .build()
				 			)
					 		.headers(header -> ServiceUtility.setRequestHeader(header, token, trId, apiConfig))
							.retrieve()
							.bodyToMono(new ParameterizedTypeReference<Map<String,Object>>(){});
		});
		
	}
	
	// 기본시세 => 국내주식기간별시세(일/주/월/년)
	// period => D:일봉 W:주봉, M:월봉, Y:년봉
	public Mono<Map<String,Object>> getQuote(String startDate, String endDate, String period){
		String trId = "FHKST03010100";
		
		return tokenService.getToken().flatMap(token -> {
			WebClient webClient = WebClient.builder()
										   .baseUrl(URL)
										   .build();
			return webClient.get()
					 		.uri(uri -> uri.path("/uapi/domestic-stock/v1/quotations/inquire-time-dailychartprice")
					 				       .queryParam("FID_COND_MRKT_DIV_CODE", "J")
					 				       .queryParam("FID_INPUT_ISCD", "005930")
					 				       .queryParam("FID_INPUT_DATE_1", startDate)
					 				       .queryParam("FID_INPUT_DATE_2", endDate)
					 				       .queryParam("FID_PERIOD_DIV_CODE", period)
					 				       .queryParam("FID_ORG_ADJ_PRC", "0")
					 				       .build()
				 			)
					 		.headers(header -> ServiceUtility.setRequestHeader(header, token, trId, apiConfig))
							.retrieve()
							.bodyToMono(new ParameterizedTypeReference<Map<String,Object>>(){});
		});
		
	}
	// -------------------------------------------------------------------------------------
	// 기본시세 => 주식현재가 시세(아직 안쓰이는 메서드)
		public Mono<Map<String, Object>> getStock(String marketCode, String stockCode) {
			String trId = "FHKST01010100";
			
			return tokenService.getToken().flatMap(token ->{
				WebClient webClient = WebClient.builder()
											   .baseUrl(URL)
											   .build();
				return webClient.get()
							    .uri(uri -> uri.path("/uapi/domestic-stock/v1/quotations/inquire-price")
							 		   	 	.queryParam("FID_COND_MRKT_DIV_CODE", marketCode)
							 		   	 	.queryParam("FID_INPUT_ISCD", stockCode)
							 		   	 	.build()
							    )
							    .headers(header -> ServiceUtility.setRequestHeader(header, token, trId, apiConfig))
							    .retrieve()
							    .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>(){})
							    .onErrorResume(e -> Mono.error(new RuntimeException("ERR : getStock")));
				});
		}
	
	// 종목정보 => 주식기본조회
	public Mono<Map<String,Object>> getInfo(String typeCode, String code){
		String trId = "CTPF1002R";
		
		return tokenService.getToken().flatMap(token -> {
			WebClient webClient = WebClient.builder()
										   .baseUrl(URL)
										   .build();
			return webClient.get()
							.uri(uri -> uri.path("/uapi/domestic-stock/v1/quotations/search-stock-info")
									       .queryParam("PRDT_TYPE_CD", typeCode)
										   .queryParam("PDNO", code)
										   .build()
						    )
				     		.headers(header->ServiceUtility.setRequestHeader(header, token, trId, apiConfig))
				     		.retrieve()
				     		.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
			
		});
		
	}
	
}
