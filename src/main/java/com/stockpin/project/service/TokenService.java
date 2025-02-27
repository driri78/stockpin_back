package com.stockpin.project.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatusCode;
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
	
	// open api로 토큰 발급
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
												   .onStatus(HttpStatusCode::is4xxClientError,
		                                                      response -> response.bodyToMono(Map.class)
                                                                                 .flatMap(error -> {
                                                                                	 if(error.get("error_code").equals("EGW00133")) {
                                                                                		 System.out.println(error.get("error_description"));
                                                                                	 }
                                                                                	 return Mono.error(new RuntimeException("Client error occurred"));
                                                                                 }))
												   .bodyToMono(new ParameterizedTypeReference<Map<String,String>>() {})
												   .block();
			
			String accessToken = respones.get("access_token");
			String tokenType = respones.get("token_type");
			int expiresIn = Integer.parseInt(respones.get("expires_in"));
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
	
	// Redis에서 token값 get
	public String getToken() {
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		String token = valueOperations.get("access_token");
		
		// 토큰 만료일 유효성 검사
		if(token == null || isTokenExpired()) {
			TokenResponesDTO tokenInfo = getApiToken();
			valueOperations.set("access_token", tokenInfo.getAccessToken());
			valueOperations.set("token_type", tokenInfo.getTokenType());
			valueOperations.set("expires_in", String.valueOf(tokenInfo.getExpiresIn()));
			valueOperations.set("access_token_token_expired", String.valueOf(System.currentTimeMillis() + tokenInfo.getExpiresIn()));
		}
		
		return token;
	}
	
	// 토큰 만료일 check 1일 => 86400초
	public boolean isTokenExpired() {
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		long expired = Long.parseLong(valueOperations.get("access_token_token_expired"));
		
		if(System.currentTimeMillis() < expired) {
			return false;
		}
		return true;
	}
	
}
