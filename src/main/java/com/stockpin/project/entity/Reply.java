package com.stockpin.project.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
// 대댓글 테이블
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String content; // 댓글내용
	private LocalDateTime createAt; // 생성일
	private LocalDateTime updateAt; // 업데이트일
	
	@ManyToOne
	@JoinColumn(name = "comment_id")
	private Comment comment;
	
	
}
