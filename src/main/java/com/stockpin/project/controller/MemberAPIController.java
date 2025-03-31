package com.stockpin.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stockpin.project.service.coolsms.ExtnalCoolSmsAPIService;

import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberAPIController {

	private final ExtnalCoolSmsAPIService apiService;
	
	@PostMapping("/send")
	public SingleMessageSentResponse send() {
		return apiService.singleSendMessage();
	}
	
}
