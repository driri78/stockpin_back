package com.stockpin.project.dto.stock.price;

import lombok.Getter;
import lombok.Setter;

@Getter
public class TradeAmountDTO {
	private long tradeAmout; // 거래대금

	public void setTradeAmout(long tradeAmout) {
		this.tradeAmout = tradeAmout * 1000;
	}
	
}
