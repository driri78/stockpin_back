package com.stockpin.project.service.coolsms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Service
@RequiredArgsConstructor
public class ExtnalCoolSmsAPIService {

	@Value("${api.sms.appkey}")
	private String appKey;
	
	@Value("${api.sms.appsecret}")
	private String appSecret;
	
	@Value("${api.sms.sender}")
	private String sender;
	
	@Value("${api.sms.provider}")
	private String provider;
	
	private DefaultMessageService messageService;
	
	@PostConstruct
	public void init() {
		messageService = NurigoApp.INSTANCE.initialize(appKey, appSecret, provider);
	}
	
	public SingleMessageSentResponse singleSendMessage() {
		Message message = new Message();
		message.setFrom(sender);
		message.setTo(sender);
		message.setText("1234");
		SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));
		return response;
	}
}
