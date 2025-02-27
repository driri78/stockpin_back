package com.stockpin.project.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenRequestDTO {
	private String appkey;
	private String appsecret;
	private String grant_type = "client_credentials";
	
	@Builder
	public TokenRequestDTO(String apiKey, String appsecret) {
		this.appkey = apiKey;
		this.appsecret = appsecret;
	}
}