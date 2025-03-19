package com.stockpin.project.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TradingAccount {

	@Id
	private long id;
	private String accountNumber;
	private String pw;
	private LocalDateTime createAt;
	
	@OneToMany(mappedBy = "tradingAccount")
	private List<TransactionHistory> transactionHistory;
	
}
