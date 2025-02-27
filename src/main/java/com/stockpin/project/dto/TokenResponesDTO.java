package com.stockpin.project.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenResponesDTO {
	private String accessToken;
	private String tokenType;
	private String expiresIn;
	private String acessTokenTokenExpired;
	
	@Builder
	public TokenResponesDTO(String accessToken, String tokenType, String expiresIn, String acessTokenTokenExpired) {
		this.accessToken = accessToken;
		this.tokenType = tokenType;
		this.expiresIn = expiresIn;
		this.acessTokenTokenExpired = acessTokenTokenExpired;
	}
	
}
