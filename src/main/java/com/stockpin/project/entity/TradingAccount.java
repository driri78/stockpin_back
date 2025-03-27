package com.stockpin.project.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
// 계좌 테이블
public class TradingAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String accountNumber;
	private String pw;
	private LocalDateTime createAt;
	
	@OneToMany(mappedBy = "tradingAccount")
	private List<TransactionHistory> transactionHistoryList;
	
}
