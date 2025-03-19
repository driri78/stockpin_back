package com.stockpin.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String pw;
	private String name;
	private String fullName;
	private String phone;
	
	@OneToOne
	@JoinColumn(name="trading_account_id", unique = true)
	private TradingAccount tradingAccount;
}
