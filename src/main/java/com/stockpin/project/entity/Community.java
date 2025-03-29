package com.stockpin.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
// 커뮤니티 테이블
public class Community {

	private long viewCnt;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "community_seq")
	@SequenceGenerator(name = "community_seq", sequenceName = "community_seq", allocationSize = 1)
	private Long stockCode;
	
	@MapsId
	@OneToOne(mappedBy = "community")
	@JoinColumn(name = "stock_code")
	private StockInfo stockInfo;
	
}
