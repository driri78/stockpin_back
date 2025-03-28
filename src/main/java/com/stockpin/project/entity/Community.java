package com.stockpin.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
// 커뮤니티 테이블
public class Community {

	private long viewCnt;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long stockCode;
	
	@MapsId
	@OneToOne
	@JoinColumn(name = "stock_code")
	private StockInfo stockInfo;
	
}
