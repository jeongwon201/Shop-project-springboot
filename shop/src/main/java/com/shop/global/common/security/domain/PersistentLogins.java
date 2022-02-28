package com.shop.global.common.security.domain;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersistentLogins {
	@Id
	@Column(length = 64)
	private String series;

	@Column(length = 64)
	private String username;

	@Column(length = 64)
	private String token;

	@CreationTimestamp
	@Column(name="last_used")
	private Timestamp lastUsed;
}
