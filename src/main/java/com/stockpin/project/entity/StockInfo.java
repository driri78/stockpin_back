package com.stockpin.project.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
// 주식정보 테이블
public class StockInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_info_seq")
	@SequenceGenerator(name = "stock_info_seq", sequenceName = "stock_info_seq", allocationSize = 1)
	private String code; // 주식코드
	private String name; // 주식이름
	private String marketType; // 시장타입
	private String url; // 홈페이지 url
	private int tdyVwCnt; // 하루 조회수
	
	@OneToOne
	private Community community;
	
	@OneToMany(mappedBy = "stockInfo")
	private List<Comments> commentList;
	
	@OneToMany(mappedBy = "stockInfo")
	private List<StockTradeHistory> stockTradeHistoryList;
	
	@OneToMany(mappedBy = "stockInfo")
	private List<StockLikes> stockLikesList;
	
	@OneToMany(mappedBy = "stockInfo")
	private List<Stock> stockList;
}
