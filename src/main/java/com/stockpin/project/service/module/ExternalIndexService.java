package com.stockpin.project.service.module;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.stockpin.project.config.APIConfig;
import com.stockpin.project.util.ServiceUtility;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExternalIndexService {

	private final TokenService tokenService;
	
	private final APIConfig apiConfig;
	
	public Mono<Map<String,Object>> getIndex(){
		String trId = "FHPUP02100000";
		
		return tokenService.getToken().flatMap(token -> {
			WebClient webClient = WebClient.builder()
										   .baseUrl(apiConfig.getUrl())
										   .build();
			
			return webClient.get()
							.uri(uri -> uri.path("/uapi/domestic-stock/v1/quotations/inquire-index-price")
										   .queryParam("FID_COND_MRKT_DIV_CODE", "U")
										   .queryParam("FID_INPUT_ISCD", "1001")
										   .build()
						    )
							.headers(header -> ServiceUtility.setRequestHeader(header, token, trId, apiConfig))
							.retrieve()
							.bodyToMono(new ParameterizedTypeReference<Map<String,Object>>() {})
							.onErrorResume(err -> Mono.error(new RuntimeException("ERR : getIndex\n" + err)));
		});
		
	}
	
	
}
