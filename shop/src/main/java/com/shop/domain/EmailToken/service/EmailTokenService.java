package com.shop.domain.EmailToken.service;

import com.shop.domain.EmailToken.domain.EmailToken;

public interface EmailTokenService {
	
	public void createEmailToken(String username);
	
	public EmailToken findByUsernameAndTokenAndExpirationDateAfterAndExpired(String username, String token);
	
	public boolean verify(String token);
}
