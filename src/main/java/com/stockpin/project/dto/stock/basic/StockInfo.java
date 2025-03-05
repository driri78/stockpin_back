package com.stockpin.project.dto.stock.basic;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StockInfo<T> {
	private T data;
	private String name;
	private String code;
	private long price;
	private double chgRate;
	
	@Builder
	public StockInfo(T data, String name, String code, long price, double chgRate) {
		this.data = data;
		this.name = name;
		this.code = code;
		this.price = price;
		this.chgRate = chgRate;
	}
	
	
}
