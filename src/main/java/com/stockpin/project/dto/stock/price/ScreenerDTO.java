package com.stockpin.project.dto.stock.price;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScreenerDTO {
	private long stotPrice; // 시가총액
	private long acmlVol; // 거래량
}
