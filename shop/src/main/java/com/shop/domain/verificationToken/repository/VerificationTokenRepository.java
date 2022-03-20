package com.shop.domain.verificationToken.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.domain.verificationToken.domain.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
	Optional<VerificationToken> findByTokenAndExpirationDateAfterAndExpired(String token, LocalDateTime now, boolean expired);
	
	List<VerificationToken> findByUserIdAndAndExpirationDateAfterAndExpired(Long userId, LocalDateTime now, boolean expired);
}