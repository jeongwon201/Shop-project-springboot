package com.shop.domain.Account.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shop.domain.model.BaseEntity;

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

	@Column(length = 50, nullable = false, unique = true)
	private String username;
	
	@Column(length = 100, nullable = false)
	private String password;

	@Column(length = 22, nullable = false)
	private String nickname;
	
	@Column(nullable = false)
	private String oAuth;
	
	@Column(nullable = false)
	private boolean isAccountNonExpired = true;

	@Column(nullable = false)
	private boolean isAccountNonLocked = false;

	@Column(nullable = false)
	private boolean isCredentialsNonExpired = true;
	
	@Column(nullable = false)
	private boolean isEnabled = true;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="userId")
	private List<Auth> authList = new ArrayList<Auth>();
	
	public void addAdmin() {
		Auth auth = new Auth();
		auth.setAdmin();
		authList.add(auth);
	}
	
	public void addAuth() {
		Auth auth = new Auth();
		authList.add(auth);
	}
	
	public void encodePassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
	}
	
	public void isAccountNonLockedUpdate() {
		this.isAccountNonLocked = false;
	}
	
	@Builder
    public Account(String username, String password, String nickname, String oAuth) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.oAuth = oAuth;
    }
}
