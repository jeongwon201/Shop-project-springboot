package com.shop.domain.Account.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.shop.domain.model.BaseEntity;
import com.shop.domain.token.domain.VerificationToken;
import com.shop.global.utils.emuns.UserRole;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(unique = true)
	private String username;
	
	@Column
	private String password;

	@Column
	private String nickname;
	
	@Column
	private String oAuth;
	
	@Column
	private String auth = UserRole.GUEST.getValue();
	
	@Column
	private boolean isAccountNonExpired = true;

	@Column
	private boolean isAccountNonLocked = true;

	@Column
	private boolean isCredentialsNonExpired = true;
	
	@Column
	private boolean isEnabled = true;
	
	public void updateAuth(UserRole auth) {
		this.auth = auth.getValue();
	}
	
	public void encodePassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
	}
	
	@Builder
    public Account(String username, String password, String nickname, String oAuth) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.oAuth = oAuth;
    }
}
