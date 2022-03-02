package com.shop.domain.Account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.domain.Account.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	long countByUsername(String username);
	
	Optional<Account> findByUsername(String username);
}