package com.stockpin.project.entity;

import java.time.LocalDateTime;

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
// 주식거래내역 테이블
public class StockTradeHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_trade_history_seq")
	@SequenceGenerator(name = "stock_trade_history_seq", sequenceName = "stock_trade_history_seq", allocationSize = 1)
	private Long id;
	private String transactionType;
	private int quantity;
	private int price;
	private int totalPrice;
	private LocalDateTime createAt;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "stock_code")
	private StockInfo stockInfo;
	
}
