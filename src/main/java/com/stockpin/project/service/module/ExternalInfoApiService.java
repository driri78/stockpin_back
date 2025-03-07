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
public class ExternalInfoApiService {
	private final TokenService tokenService;
	
	private final APIConfig apiConfig;
	
	String URL = "https://openapi.koreainvestment.com:9443";
	
	// 종목정보 => 주식기본조회
	public Mono<Map<String,Object>> getStockInfo(String code){
		String trId = "CTPF1002R";
		
		return tokenService.getToken().flatMap(token -> {
			WebClient webClient = WebClient.builder()
										   .baseUrl(URL)
										   .build();
			return webClient.get()
							.uri(uri -> uri.path("/uapi/domestic-stock/v1/quotations/search-stock-info")
									       .queryParam("PRDT_TYPE_CD", "300")
										   .queryParam("PDNO", code)
										   .build()
						    )
				     		.headers(header->ServiceUtility.setRequestHeader(header, token, trId, apiConfig))
				     		.retrieve()
				     		.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
			
		});
		
	}
}
