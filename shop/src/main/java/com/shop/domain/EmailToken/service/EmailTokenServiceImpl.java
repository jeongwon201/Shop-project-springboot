package com.shop.domain.EmailToken.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.shop.domain.EmailToken.domain.EmailToken;
import com.shop.domain.EmailToken.repository.EmailTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailTokenServiceImpl implements EmailTokenService {
	
	private final EmailTokenRepository repository;
	private final EmailSenderService emailSenderService;
	
	@Value("${spring.mail.username}")
	private String from;
	
	@Override
	public void createEmailToken(String username) {
		EmailToken emailToken = EmailToken.builder()
				.username(username)
				.build();
		
		repository.save(emailToken);
		
		System.out.println("from으닝어깅어이ㅓ  :  " + from);
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(username);
		mailMessage.setFrom(from);
		mailMessage.setSubject("이메일 인증");
		mailMessage.setText("http://localhost:8000/token=" + emailToken.getEmailTokenId());
		emailSenderService.sendEmail(mailMessage);
	}

	@Override
	public EmailToken findByTokenAndExpirationDateAfterAndExpired(String token) {
		Optional<EmailToken> verificationToken = repository.findByTokenAndExpirationDateAfterAndExpired(token, LocalDateTime.now(), false);
		return verificationToken.orElse(null);
	}

}
