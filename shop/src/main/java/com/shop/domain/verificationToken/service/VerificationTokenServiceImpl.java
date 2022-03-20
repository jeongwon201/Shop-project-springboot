package com.shop.domain.verificationToken.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shop.domain.verificationToken.domain.VerificationToken;
import com.shop.domain.verificationToken.repository.VerificationTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {
	
	private final VerificationTokenRepository repository;
	
	@Value("${spring.mail.username}")
	private String from;
	
	@Override
	public VerificationToken createVerificationToken(Long userId) {
		
		VerificationToken verificationToken = VerificationToken.builder()
				.userId(userId)
				.build();
		
		repository.save(verificationToken);
		
		return verificationToken;
	}

	@Override
	public VerificationToken findByTokenAndExpirationDateAfterAndExpired(String token) {
		Optional<VerificationToken> verificationToken = repository.findByTokenAndExpirationDateAfterAndExpired(token, LocalDateTime.now(), false);
		return verificationToken.orElse(null);
	}

	@Override
	public int numberOfTokensAvailable(Long userId) {
		
		List<VerificationToken> tokenList = repository.findByUserIdAndAndExpirationDateAfterAndExpired(userId, LocalDateTime.now(), false);
		
		return tokenList.size();
	}

	@Override
	public void useToken(VerificationToken verificationToken) {
		verificationToken.useToken();
		repository.save(verificationToken);
	}



}