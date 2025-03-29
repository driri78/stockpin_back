package com.stockpin.project.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
// 유저 테이블
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
	@SequenceGenerator(name = "member_seq", sequenceName = "member_seq", allocationSize = 1)
	private Long id;
	private String pw;
	private String name;
	private String fullName;
	private String phone;
	
	@OneToOne
	@JoinColumn(name="trading_account_id", unique = true)
	private TradingAccount tradingAccount;
	
	@OneToMany(mappedBy = "member")
	private List<Comments> commentsList;
	
	@OneToMany(mappedBy = "member")
	private List<StockTradeHistory> stockTradeHistoryList;

	@OneToMany(mappedBy = "member")
	private List<Reply> replyList;
	
	@OneToMany(mappedBy = "member")
	private List<StockLikes> stockLikesList;
	
	@OneToMany(mappedBy = "member")
	private List<Stock> stockList;
}
