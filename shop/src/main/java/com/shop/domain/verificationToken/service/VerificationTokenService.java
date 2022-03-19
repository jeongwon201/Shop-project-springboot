package com.shop.domain.verificationToken.service;

import com.shop.domain.Account.domain.Account;
import com.shop.domain.verificationToken.domain.VerificationToken;

public interface VerificationTokenService {
	
	public VerificationToken createVerificationToken(Account account);
	
	public VerificationToken findByTokenAndExpirationDateAfterAndExpired(String token);
	
	public void useToken(VerificationToken verificationToken);
	
}