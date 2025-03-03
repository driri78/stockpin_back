package com.stockpin.project.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "api")
public class APIConfig {
	private String appkey;
	private String appsecret;
	private String userId;
}
