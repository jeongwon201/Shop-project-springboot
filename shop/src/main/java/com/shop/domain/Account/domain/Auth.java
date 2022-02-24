package com.shop.domain.Account.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shop.domain.model.BaseEntity;
import com.shop.global.utils.emuns.UserRole;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auth extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long authId;
	
	@Column(name="userId")
	private Long userId;
	
	@Column(length = 10)
	@Enumerated(value = EnumType.STRING)
	private UserRole role = UserRole.USER;
	
	public void setAdmin() {
		this.role = UserRole.ADMIN;
	}
	
}
