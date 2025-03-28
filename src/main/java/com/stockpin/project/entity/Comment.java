package com.stockpin.project.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
// 댓글 테이블
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String content;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "stock_code")
	private StockInfo stockInfo;

	@OneToMany(mappedBy = "comment")
	private List<Reply> replyList;
	
	@Builder
	public Comment(long id, String content, LocalDateTime createAt, LocalDateTime updateAt, 
			User user, StockInfo stockInfo) {
		this.id = id;
		this.content = content;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.user = user;
		this.stockInfo = stockInfo;
	}
	
	
	
}
