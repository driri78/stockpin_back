package com.stockpin.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.stockpin.project.dto.TokenResponesDTO;
import com.stockpin.project.service.TokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StockAPIController {

	private final TokenService tokenService;
	
	@GetMapping("test")
	public TokenResponesDTO getDTO() {
		TokenResponesDTO result = null;
		try {
			String url = "https://openapivts.koreainvestment.com:29443";
			//WebClient webclient = WebClient.create(url);
			// builder로 커스텀 가능
			WebClient webClient = WebClient.builder()
										   .baseUrl(url)
										   .build();
			
			Map<String, Object> requestData = new HashMap<>();
			requestData.put("grant_type", "client_credentials");
			requestData.put("appkey", "PSVRKOsVWWoHs04ufNkd2Y4WYRKMnKdgkwnU");
			requestData.put("appsecret", "O/C2GGA7fN4v8B8dML8K5MtcRCyNzPBQ61lO0nIUzhP9A+L0M0YuJmzs5p8j4buR4yv4Th8wpE11f8VVGtkzSHYLfugFMh4zfU0opujKpP2dOsbSqTaLZTlN0vbMGMZsnD0oVpzat0S0b30asXqNMHV7GAEH7cSZ9xmnvge1cAaNe9ULRss=");
			Map<String, String> response = webClient.post()
													.uri("/oauth2/tokenP")
													.contentType(MediaType.APPLICATION_JSON)
												    .bodyValue(requestData)
												    .retrieve()
												    .bodyToMono(new ParameterizedTypeReference<Map<String, String>>(){})
												    .block();
			if(response != null) {
				String accessToken = response.get("access_token");
				String tokenType = response.get("token_type");
				int expiresIn = Integer.parseInt(response.get("expires_in"));
				String acessTokenTokenExpired = response.get("acess_token_token_expired");
				
				result = TokenResponesDTO.builder()
								 .accessToken(accessToken)
								 .tokenType(tokenType)
								 .expiresIn(expiresIn)
								 .acessTokenTokenExpired(acessTokenTokenExpired)
								 .build();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@GetMapping("test1")
	public String get() {
		return tokenService.getToken();
	}
	
}
