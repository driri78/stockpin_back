package com.stockpin.project.dto.ksitoken;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TokenResponseDTO {
	private String accessToken;
	private String tokenType;
	private Integer expiresIn;
	private String accessTokenTokenExpired;
	
	@Builder
	public TokenResponseDTO(String accessToken, String tokenType, Integer expiresIn, String accessTokenTokenExpired) {
		this.accessToken = accessToken;
		this.tokenType = tokenType;
		this.expiresIn = expiresIn;
		this.accessTokenTokenExpired = accessTokenTokenExpired;
	}
}