package com.shop.domain.Account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.Account.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Optional<Account> findByUsername(String username);
	
}