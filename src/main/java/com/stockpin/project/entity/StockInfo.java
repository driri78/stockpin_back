package com.stockpin.project.entity;

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
public class StockInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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
