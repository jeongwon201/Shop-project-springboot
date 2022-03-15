package com.shop.domain.token.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.token.domain.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
	Optional<VerificationToken> findByTokenAndExpirationDateAfterAndExpired(String token, LocalDateTime now, boolean expired);
}
