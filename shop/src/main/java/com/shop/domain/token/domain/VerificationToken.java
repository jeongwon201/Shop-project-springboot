package com.shop.domain.token.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.shop.domain.Account.domain.Account;
import com.shop.domain.model.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerificationToken extends BaseEntity{
	
	private static final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long emailVerificationId;
	
	@OneToOne
	@JoinColumn(name = "userId")
	private Account account;
	
	@Column
	private String token;
	
	@Column
	private LocalDateTime expirationDate;

	@Column
	private boolean isVerified = false;
	

	public void useToken() {
		this.isVerified = true;
	}

	@Builder
	public VerificationToken(Account account) {
		this.expirationDate = LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE);
		this.account = account;
		this.token = account.getUserId() + "-" + UUID.randomUUID().toString();
		this.isVerified = false;
	}
}