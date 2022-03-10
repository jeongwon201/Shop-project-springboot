package com.shop.domain.EmailToken.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.shop.domain.Account.service.EmailSenderService;
import com.shop.domain.EmailToken.domain.EmailToken;
import com.shop.domain.EmailToken.repository.EmailTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailTokenServiceImpl implements EmailTokenService {
	
	private final EmailTokenRepository repository;
	private final EmailSenderService emailSenderService;
	
	@Override
	public void createEmailToken(String username) {
		EmailToken emailToken = EmailToken.builder()
				.username(username)
				.build();
		
		repository.save(emailToken);
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(username);
		mailMessage.setSubject("이메일 인증");
		mailMessage.setText("http://localhost:8000/verification-email?token=" + emailToken.getEmailTokenId());
		emailSenderService.sendEmail(mailMessage);
	}

	@Override
	public EmailToken findByUsernameAndTokenAndExpirationDateAfterAndExpired(String username, String token) {
		Optional<EmailToken> verificationToken = repository.findByUsernameAndTokenAndExpirationDateAfterAndExpired(username, token, LocalDateTime.now(), false);
		return verificationToken.orElse(null);
	}

	@Override
	public boolean verify(String token) {
		
		if()
		
		return true;
	}

}
