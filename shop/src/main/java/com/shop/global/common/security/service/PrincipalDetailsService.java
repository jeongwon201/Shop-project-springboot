package com.shop.global.common.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.shop.domain.Account.domain.Account;
import com.shop.domain.Account.repository.AccountRepository;
import com.shop.global.common.security.domain.PrincipalDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrincipalDetailsService implements UserDetailsService {
	
	@Autowired
	private AccountRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Account account = repository.findByUsername(username).orElse(null);
		
		return account == null ? null : new PrincipalDetails(account);
	}

}
