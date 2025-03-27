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
// 주식정보 테이블
public class StockInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String code; // 주식코드
	private String name; // 주식이름
	private String marketType; // 시장타입
	private String url; // 홈페이지 url
	private int tdyVwCnt; // 하루 조회수
	
	@OneToMany(mappedBy = "stockInfo")
	private List<Comment> commentList;
	
	@OneToMany(mappedBy = "stockInfo")
	private List<StockTradeHistory> stockTradeHistoryList;
	
}
