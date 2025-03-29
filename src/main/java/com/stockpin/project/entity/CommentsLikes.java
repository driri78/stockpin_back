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
// 댓글 좋아요(대댓글 포함)
public class CommentsLikes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commentsLikes_seq")
	@SequenceGenerator(name = "commentsLikes_seq", sequenceName = "commentsLikes_seq", allocationSize = 1)
	private Long id;
	
	private LocalDateTime createAt;
	
	@ManyToOne
	@JoinColumn(name = "comments_id")
	private Comments comments;
	
	@ManyToOne
	@JoinColumn(name = "reply_id")
	private Reply reply;
	
}
