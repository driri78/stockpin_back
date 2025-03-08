package com.stockpin.project.dto.stock.price;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StockPriceDTO<T> {
	private T data;
	private String name; // 종목명
	private String code; // 종목 코드
	private long price; // 주가
	private double chgRate; // 등락률
	
	@Builder
	public StockPriceDTO(T data, String name, String code, long price, double chgRate) {
		this.data = data;
		this.name = name;
		this.code = code;
		this.price = price;
		this.chgRate = chgRate;
	}
	
	
}
