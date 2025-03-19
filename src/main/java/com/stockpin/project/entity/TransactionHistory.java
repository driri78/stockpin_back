package com.stockpin.project.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TransactionHistory {

	@Id
	private long id;
	private String amount;
	private String transactionType;
	private LocalDateTime createAt;
	
	@ManyToOne
	@JoinColumn(name = "trading_account_id")
	private TradingAccount tradingAccount;
	
}
