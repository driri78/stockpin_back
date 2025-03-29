package com.stockpin.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockpin.project.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
}
