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
import jakarta.persistence.SequenceGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
// 댓글 테이블
public class Comments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_seq")
	@SequenceGenerator(name = "comments_seq", sequenceName = "comments_seq", allocationSize = 1)
	private Long id;
	private String content;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "stock_code")
	private StockInfo stockInfo;

	@OneToMany(mappedBy = "comments")
	private List<Reply> replyList;
	
	@OneToMany(mappedBy = "comments")
	private List<CommentsLikes> commentsLikes;
	
	@Builder
	public Comments(long id, String content, LocalDateTime createAt, LocalDateTime updateAt, 
			Member member, StockInfo stockInfo) {
		this.id = id;
		this.content = content;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.member = member;
		this.stockInfo = stockInfo;
	}
	
	
	
}
