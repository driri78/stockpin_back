package com.stockpin.project.service.component;

import java.util.Map;
import java.util.function.Consumer;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.stockpin.project.config.APIConfig;
import com.stockpin.project.service.module.TokenService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StockService {

	private final TokenService tokenService;
	
	private final APIConfig apiConfig;
	
	public void setRequestHeader(HttpHeaders header, String token, String trId, String custType) {
		header.set("content-type", "application/json; charset=utf-8");
		header.set("authorization","Bearer " + token);
		header.set("appkey", apiConfig.getAppkey());
		header.set("appsecret", apiConfig.getAppsecret());
		header.set("tr_id", trId);
		header.set("custtype", custType);
	}
	
	public Mono<Map<String, Object>> getStockData(String fidCondMarktDivCode, String fidInputIscd) {
		return tokenService.getToken().flatMap(token ->{
			String url = "https://openapi.koreainvestment.com:9443";
			
			WebClient webClient = WebClient.builder()
										   .baseUrl(url)
										   .build();
			return webClient.get()
					 .uri(uri -> uri.path("/uapi/domestic-stock/v1/quotations/inquire-price")
					 		   	 	.queryParam("FID_COND_MRKT_DIV_CODE", fidCondMarktDivCode)
					 		   	 	.queryParam("FID_INPUT_ISCD", fidInputIscd)
					 		   	 	.build()
					 )
					 .headers(header -> {
						 this.setRequestHeader(header, token, "FHKST01010100", "P");
					 })
					 .retrieve()
					 .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>(){})
					 .onErrorResume(e -> Mono.error(new RuntimeException("ERR : getStockData")));
			});
	}
	
}
