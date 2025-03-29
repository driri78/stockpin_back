package com.stockpin.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockpin.project.entity.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Long>{

}
