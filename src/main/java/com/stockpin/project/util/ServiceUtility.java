package com.stockpin.project.util;

import org.springframework.http.HttpHeaders;

import com.stockpin.project.config.APIConfig;

public class ServiceUtility {
	
	public static void setRequestHeader(HttpHeaders header, String token, String trId, APIConfig apiConfig) {
		String appkey = apiConfig.getAppkey();
		String appsecret = apiConfig.getAppsecret();
		
		header.set("content-type", "application/json; charset=utf-8");
		header.set("authorization","Bearer " + token);
		header.set("appkey", appkey);
		header.set("appsecret", appsecret);
		header.set("tr_id", trId);
		// P = 개인 B = 법인
		header.set("custtype", "P");
	}
}
