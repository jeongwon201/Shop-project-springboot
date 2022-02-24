package com.shop.domain.Account.service;

import com.shop.domain.Account.domain.Account;

public interface AccountService {
	
	public long countAll() throws Exception;
	
	public void register(Account account) throws Exception;
	
	public int findByUsername(String username) throws Exception;
	
    public void setupAdmin(Account account) throws Exception;
    
}