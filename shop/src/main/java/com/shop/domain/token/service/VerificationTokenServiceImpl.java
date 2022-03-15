package com.shop.domain.token.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.shop.domain.Account.domain.Account;
import com.shop.domain.token.domain.VerificationToken;
import com.shop.domain.token.repository.VerificationTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {
	
	private final VerificationTokenRepository repository;
	private final EmailSender emailSenderService;
	
	@Value("${spring.mail.username}")
	private String from;
	
	@Override
	public void createEmailToken(Account account) {
		VerificationToken emailToken = VerificationToken.builder()
				.account(account)
				.build();
		
		repository.save(emailToken);
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(account.getUsername());
		mailMessage.setFrom(from);
		mailMessage.setSubject("이메일 인증");
		mailMessage.setText("http://localhost:8000/token=" + emailToken.getToken());
		emailSenderService.sendEmail(mailMessage);
	}

	@Override
	public VerificationToken findByTokenAndExpirationDateAfterAndExpired(String token) {
		Optional<VerificationToken> verificationToken = repository.findByTokenAndExpirationDateAfterAndExpired(token, LocalDateTime.now(), false);
		return verificationToken.orElse(null);
	}

}
