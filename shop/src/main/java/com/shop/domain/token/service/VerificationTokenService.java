package com.shop.domain.token.service;

import com.shop.domain.Account.domain.Account;
import com.shop.domain.token.domain.VerificationToken;

public interface VerificationTokenService {
	
	public void createEmailToken(Account account);
	
	public VerificationToken findByTokenAndExpirationDateAfterAndExpired(String token);
	
}