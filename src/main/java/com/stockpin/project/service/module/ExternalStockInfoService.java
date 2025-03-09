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
public class ExternalStockInfoService {

	private final TokenService tokenService;
	
	private final APIConfig apiConfig;
	
	// 기본시세 => 주식현재가 시세(아직 안쓰이는 메서드)
	public Mono<Map<String, Object>> getStock(String marketCode, String stockCode) {
		String trId = "FHKST01010100";

		return tokenService.getToken().flatMap(token -> {
			WebClient webClient = WebClient.builder()
										   .baseUrl(apiConfig.getUrl())
										   .build();
			return webClient.get()
							.uri(uri -> uri.path("/uapi/domestic-stock/v1/quotations/inquire-price")
										   .queryParam("FID_COND_MRKT_DIV_CODE", marketCode)
										   .queryParam("FID_INPUT_ISCD", stockCode)
										   .build()
						    )
							.headers(header -> ServiceUtility.setRequestHeader(header, token, trId, apiConfig))
							.retrieve()
							.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
							}).onErrorResume(err -> Mono.error(new RuntimeException("ERR : ExternalStockInfoService => getStock\n" + err)));
		});
	}

	// 종목정보 => 주식기본조회
	public Mono<Map<String, Object>> getInfo(String typeCode, String code) {
		String trId = "CTPF1002R";

		return tokenService.getToken().flatMap(token -> {
			WebClient webClient = WebClient.builder()
										   .baseUrl(apiConfig.getUrl())
										   .build();
			return webClient.get()
							.uri(uri -> uri.path("/uapi/domestic-stock/v1/quotations/search-stock-info")
									       .queryParam("PRDT_TYPE_CD", typeCode)
									       .queryParam("PDNO", code)
									       .build()
					        )
							.headers(header -> ServiceUtility.setRequestHeader(header, token, trId, apiConfig))
							.retrieve()
							.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
							.onErrorResume(err -> Mono.error(new RuntimeException("ERR : ExternalStockInfoService => getInfo\n" + err)));

		});

	}
}
