package com.shop.domain.verificationToken.service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.shop.domain.verificationToken.domain.VerificationToken;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailSender {
	
	private final JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String from;

	public MimeMessage setEmailVerificationMessage(VerificationToken verificationToken) throws Exception {

		String verifyForm = "<html><body>"
				+ "<a href='http://localhost:8000/account/verify-email/" + verificationToken.getToken() + "'>인증하기</a>"
				+ "</body><html>";
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		mimeMessage.setSubject("이메일 인증");
		mimeMessage.setText(verifyForm, "utf-8", "html");
		mimeMessage.setFrom(new InternetAddress(from));
		mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(verificationToken.getAccount().getUsername()));
		
		return mimeMessage;
	}
	
	@Async
	public void sendEmail(MimeMessage mimeMessage) {
		javaMailSender.send(mimeMessage);
	}
}