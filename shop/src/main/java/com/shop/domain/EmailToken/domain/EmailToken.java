package com.shop.domain.EmailToken.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.shop.domain.model.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailToken extends BaseEntity{
	
	private static final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long emailTokenId;
	
	@Column
	private LocalDateTime expirationDate;
	
	@Column
	private String username;
	
	@Column
	private String token;
	
	@Column
	private boolean expired;
	
	public void useToken() {
		expired = true;
	}
	
	@Builder
	public EmailToken(String username) {
		this.expirationDate = LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE);
		this.username = username;
		this.token = UUID.randomUUID().toString();
		this.expired = false;
	}
}