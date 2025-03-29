package com.stockpin.project.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
// 관심주식 테이블
public class StockLikes {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_likes_seq")
	@SequenceGenerator(name = "stock_likes_seq", sequenceName = "stock_likes_seq", allocationSize = 1)
	private Long id;
	
	private LocalDateTime createAt;
	
	@ManyToOne
	@JoinColumn(name = "stock_code")
	private StockInfo stockInfo;
	
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
}
