package com.stockpin.project.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class TradingAccount {

	private long id;
	private String accountNumber;
	private String pw;
	private LocalDateTime createAt;
	
	private User user;
	
}
