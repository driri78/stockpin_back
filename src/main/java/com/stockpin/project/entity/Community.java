package com.stockpin.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Community {

	private long viewCnt;
	
	@Id
	private long stockCode;
	
	@MapsId
	@OneToOne
	@JoinColumn(name = "stock_code")
	private StockInfo stockInfo;
	
}
