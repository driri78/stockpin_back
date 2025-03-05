package com.stockpin.project.service.module;

import java.util.Map;
import java.util.function.Consumer;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.stockpin.project.config.APIConfig;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExternalApiService {

	private final TokenService tokenService;
	
	private final APIConfig apiConfig;
	
	String URL = "https://openapi.koreainvestment.com:9443";
	
	public void setRequestHeader(HttpHeaders header, String token, String trId) {
		String appkey = apiConfig.getAppkey();
		String appsecret = apiConfig.getAppsecret();
		
		header.set("content-type", "application/json; charset=utf-8");
		header.set("authorization","Bearer " + token);
		header.set("appkey", appkey);
		header.set("appsecret", appsecret);
		header.set("tr_id", trId);
		// P = 개인 B = 법인
		header.set("custtype", "P");
	}
	
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
						    .headers(header -> {
							 this.setRequestHeader(header, token, trId);
						    })
						    .retrieve()
						    .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>(){})
						    .onErrorResume(e -> Mono.error(new RuntimeException("ERR : getStock")));
			});
	}
	
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
						    .headers(header -> {
							    this.setRequestHeader(header, token, trId);
						    })
						    .retrieve()
						    .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>(){})
						    .onErrorResume(e -> Mono.error(new RuntimeException("ERR : getStock(period)")));
			});
	}
	
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
					 .headers(header -> {
						 this.setRequestHeader(header, token, trId);
					 })
					 .retrieve()
					 .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>(){})
					 .onErrorResume(e -> Mono.error(new RuntimeException("ERR : getCriteriaList")));
			});
		
	}
	
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
					 .headers(header -> {
						 this.setRequestHeader(header, token, trId);
					 })
					 .retrieve()
					 .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>(){})
					 .onErrorResume(e -> Mono.error(new RuntimeException("ERR : getStockList")));
			});
	}
	
}
