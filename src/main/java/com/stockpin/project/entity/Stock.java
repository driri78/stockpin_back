package com.stockpin.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
// 유저의 주식정보
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_seq")
	@SequenceGenerator(name = "stock_seq", sequenceName = "stock_seq", allocationSize = 1)
	private Long id;
	private Integer quantity;
	private Integer price;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "stock_code")
	private StockInfo stockInfo;
	
}
