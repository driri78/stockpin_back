package com.stockpin.project.dto.ksitoken;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenResponseDTO {
	private String accessToken;
	private String tokenType;
	private Integer expiresIn;
	private String acessTokenTokenExpired;
	
	@Builder
	public TokenResponseDTO(String accessToken, String tokenType, Integer expiresIn, String acessTokenTokenExpired) {
		this.accessToken = accessToken;
		this.tokenType = tokenType;
		this.expiresIn = expiresIn;
		this.acessTokenTokenExpired = acessTokenTokenExpired;
	}
}