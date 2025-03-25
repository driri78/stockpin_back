package com.stockpin.project.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class StockInfo {

	private String code;
	private String name;
	private String marketType;
	private String url;
	private int count;
	
	@OneToMany(mappedBy = "stockInfo")
	private List<Comment> commentList;
	
	@OneToMany(mappedBy = "stockInfo")
	private List<StockTradeHistory> stockTradeHistoryList;
	
}
