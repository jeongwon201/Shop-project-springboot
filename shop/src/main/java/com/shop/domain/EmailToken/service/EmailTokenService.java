package com.shop.domain.EmailToken.service;

import com.shop.domain.EmailToken.domain.EmailToken;

public interface EmailTokenService {
	
	public void createEmailToken(String username);
	
	public EmailToken findByTokenAndExpirationDateAfterAndExpired(String token);
	
}