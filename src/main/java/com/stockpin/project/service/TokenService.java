package com.stockpin.project.service;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.stockpin.project.config.APIConfig;
import com.stockpin.project.dto.TokenRequestDTO;
import com.stockpin.project.dto.TokenResponesDTO;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TokenService {
	private final RedisTemplate<String, String> redisTemplate;
	
	private final APIConfig apiConfig;
	
	public TokenResponesDTO getApiToken() {
		TokenResponesDTO result = null;
		
		String URL = "https://openapivts.koreainvestment.com:29443";
		WebClient webClient = WebClient.builder()
									   .baseUrl(URL)
									   .build();
		
		
		try {
			TokenRequestDTO requestData = new TokenRequestDTO(apiConfig.getAppkey(), 
					  										  apiConfig.getAppsecret());
			Map<String,String> respones = webClient.post()
												   .uri("/oauth2/tokenP")
												   .contentType(MediaType.APPLICATION_JSON)
												   .bodyValue(requestData)
												   .retrieve()
												   .onStatus(status -> status.is4xxClientError(), // 4xx 범위 체크
		                                                      clientResponse -> clientResponse.bodyToMono(String.class)
		                                                                                     .flatMap(error -> {
		                                                                                         // 에러 메시지 처리
		                                                                                         System.out.println("4xx Client Error: " + error);
		                                                                                         return Mono.error(new RuntimeException("Client error occurred"));
		                                                                                     }))
												   .bodyToMono(new ParameterizedTypeReference<Map<String,String>>() {})
												   .block();
			
			String accessToken = respones.get("access_token");
			String tokenType = respones.get("token_type");
			String expiresIn = respones.get("expires_in");
			String acessTokenTokenExpired = respones.get("acess_token_token_expired");
			
			if(respones != null) {
				result = TokenResponesDTO.builder()
				.accessToken(accessToken)
				.tokenType(tokenType)
				.expiresIn(expiresIn)
				.acessTokenTokenExpired(acessTokenTokenExpired)
				.build();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String getToken() {
		String token = redisTemplate.opsForValue().get("");
		
		if(token == null || isTokenExpired()) {
			
		}
		
		return token;
		
	}
	
	public boolean isTokenExpired() {
		String expired = redisTemplate.opsForValue().get("");
		
		return true;
	}
	
}
