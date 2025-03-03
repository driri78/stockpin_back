package com.stockpin.project.dto.stock;

import lombok.Getter;
import lombok.Setter;

@Getter
public class TradeAmount {
	private long tradeAmout;

	public void setTradeAmout(long tradeAmout) {
		this.tradeAmout = tradeAmout * 1000;
	}
	
}
