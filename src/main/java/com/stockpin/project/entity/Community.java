package com.stockpin.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Community {

	private long viewCnt;
	
	@OneToOne
	@JoinColumn(name = "stock_code", unique = true)
	private StockInfo stockInfo;
	
}
