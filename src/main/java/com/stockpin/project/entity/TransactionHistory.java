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
// 거래내역 테이블
public class TransactionHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_history_seq")
	@SequenceGenerator(name = "transaction_history_seq", sequenceName = "transaction_history_seq", allocationSize = 1)
	private Long id;
	private String amount;
	private String transactionType;
	private LocalDateTime createAt;
	
	@ManyToOne
	@JoinColumn(name = "trading_account_id")
	private TradingAccount tradingAccount;
	
}
