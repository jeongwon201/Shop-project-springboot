package com.shop.domain.verificationToken.service;

import com.shop.domain.verificationToken.domain.VerificationToken;

public interface VerificationTokenService {
	
	public VerificationToken createVerificationToken(Long userId);
	
	public VerificationToken findByTokenAndExpirationDateAfterAndExpired(String token);
	
	public int numberOfTokensAvailable(Long userId);
	
	public void useToken(VerificationToken verificationToken);
	
}