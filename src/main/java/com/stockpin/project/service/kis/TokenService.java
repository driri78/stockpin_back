package com.stockpin.project.service.kis;

import java.time.LocalDateTime;
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
import com.stockpin.project.dto.ksitoken.TokenRequestDTO;
import com.stockpin.project.dto.ksitoken.TokenResponseDTO;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TokenService {
	private final RedisTemplate<String, String> redisTemplate;
	
	private final APIConfig apiConfig;

	String URL = "https://openapi.koreainvestment.com:9443";
	
	// Redis에서 token값 get
	public Mono<String> getToken(){
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		String token = valueOperations.get("access_token");
		
		// 토큰 만료일 유효성 검사
		if(token == null || isTokenExpired()) {
			return getApiToken();
			
		}
		return Mono.just(token);
	}
	
	public boolean isTokenExpired() {
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime expired = LocalDateTime.parse(valueOperations.get("access_token_token_expired"), formatter);
		
		if(LocalDateTime.now().isAfter(expired)) {
			return true;
		}
		return false;
	}
	
	// open api로 토큰 발급
	private Mono<String> getApiToken() {
		WebClient webClient = WebClient.builder()
									   .baseUrl(URL)
									   .build();
		TokenRequestDTO requestData = new TokenRequestDTO(apiConfig.getAppkey(), 
				  										  apiConfig.getAppsecret());
		return webClient.post()
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
			     .bodyToMono(TokenResponseDTO.class)
			     .map(response -> {
			    	 ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
			    	 valueOperations.set("access_token", response.getAccessToken());
					 valueOperations.set("token_type", response.getTokenType());
					 valueOperations.set("expires_in", String.valueOf(response.getExpiresIn()));
					 valueOperations.set("access_token_token_expired", response.getAcessTokenTokenExpired());
			    	 return response.getAccessToken();
				   });
	}
	
	public Mono<String> getWebSocketApiToken() {
		WebClient webClient = WebClient.builder()
									   .baseUrl(URL)
									   .build();
		TokenRequestDTO requestData = new TokenRequestDTO(apiConfig.getAppkey(), 
				  										  apiConfig.getAppsecret());
		return webClient.post()
			     .uri("/oauth2/Approval")
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
			     .onErrorResume(e -> Mono.error(new RuntimeException("ERR : getWebSocketApiToken")))
			     .map(response -> {
			    	 return response.get("approval_key");
				   });
	}
	
	
}
