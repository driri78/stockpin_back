package com.stockpin.project.service;

import org.springframework.stereotype.Service;

import com.stockpin.project.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	// 회원가입
	public void signUp() {
		
	}
	
	// 로그인
	public void signIn() {
		
	}
	
}
