package com.shop.global.common.security.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.shop.domain.Account.domain.Account;

public class CustomUser extends User{
	
	private static final long serialVersionUID = 1L;
	
	private Account account;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CustomUser(Account account) {
		super(account.getUsername(), account.getPassword(), account.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getRole().getValue())).collect(Collectors.toList()));
		
		this.account = account;
	}
	
	public Account getAccount() {
		return account;
	}
	
}
