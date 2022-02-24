package com.shop.domain.Account.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.domain.Account.domain.Account;
import com.shop.domain.Account.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository repository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public long countAll() throws Exception {
		return repository.count();
	}
	
	@Override
	public void register(Account account) throws Exception {
		account.encodePassword(this.passwordEncoder);
		account.addAuth();
		
		repository.save(account);
	}
	
	@Override
	public int findByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void setupAdmin(Account account) throws Exception {
		account.encodePassword(this.passwordEncoder);
		account.addAdmin();

		repository.save(account);
	}
}
