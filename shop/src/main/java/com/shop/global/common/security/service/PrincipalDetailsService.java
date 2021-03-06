package com.shop.global.common.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.shop.domain.Account.domain.Account;
import com.shop.domain.Account.repository.AccountRepository;
import com.shop.global.common.security.domain.PrincipalDetails;

public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private AccountRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) {

		Account account = repository.findByUsername(username).orElse(Account.builder().username(username).build());

		return new PrincipalDetails(account);
	}

}
