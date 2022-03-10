package com.shop.domain.EmailToken.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.EmailToken.domain.EmailToken;

public interface EmailTokenRepository extends JpaRepository<EmailToken, Long> {
	Optional<EmailToken> findByUsernameAndTokenAndExpirationDateAfterAndExpired(String username, String token, LocalDateTime now, boolean expired);
}
