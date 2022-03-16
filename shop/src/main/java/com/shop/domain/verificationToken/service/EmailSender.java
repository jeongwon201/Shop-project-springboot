package com.shop.domain.verificationToken.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.shop.domain.Account.domain.Account;
import com.shop.domain.verificationToken.domain.VerificationToken;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailSender {
	
	private final JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String from;

	public SimpleMailMessage setEmailVerificationMessage(VerificationToken verificationToken) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(verificationToken.getAccount().getUsername());
		mailMessage.setFrom(from);
		mailMessage.setSubject("이메일 인증");
		mailMessage.setText("http://localhost:8000/account/verify-email/" + verificationToken.getToken());
		
		return mailMessage;
	}
	
	@Async
	public void sendEmail(SimpleMailMessage email) {
		javaMailSender.send(email);
	}
}