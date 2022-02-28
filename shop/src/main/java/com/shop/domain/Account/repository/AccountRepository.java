package com.shop.domain.Account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.Account.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	long countByUsername(String username);
	
	public List<Account> findByUsername(String username);
}