package com.shop.domain.Account.service;

import java.util.Optional;

import com.shop.domain.Account.domain.Account;

public interface AccountService {
	
	public long countAll() throws Exception;
	
	public void register(Account account) throws Exception;
	
	public long doubleCheck(String username) throws Exception;
	
    public void setupAdmin(Account account) throws Exception;
    
    Optional<Account> findById(Long userId) throws Exception;
    
    public void verifyEmail(Account account) throws Exception;
}