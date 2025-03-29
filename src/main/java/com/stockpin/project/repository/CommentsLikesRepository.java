package com.stockpin.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockpin.project.entity.CommentsLikes;

public interface CommentsLikesRepository extends JpaRepository<CommentsLikes, Long>{

}
