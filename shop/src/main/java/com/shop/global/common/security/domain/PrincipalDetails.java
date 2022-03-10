package com.shop.global.common.security.domain;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.shop.domain.Account.domain.Account;
import com.shop.global.common.security.userInfo.OAuth2UserInfo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PrincipalDetails implements UserDetails, OAuth2User{
	
	private static final long serialVersionUID = 1L;
	
	private Account account;
	
	private OAuth2UserInfo oAuth2UserInfo;
	
	public PrincipalDetails(Account account) {
		this.account = account;
	}
	
	public PrincipalDetails(Account account, OAuth2UserInfo oAuth2UserInfo) {
		this.account = account;
		this.oAuth2UserInfo = oAuth2UserInfo;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return account.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getRole().getValue())).collect(Collectors.toList());
	}


	@Override
	public String getPassword() {
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return account.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return account.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return account.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return account.isEnabled();
	}

	// OAuth2User
	@Override
	public Map<String, Object> getAttributes() {
		return oAuth2UserInfo.getAttributes();
	}

	@Override
	public String getName() {
		return oAuth2UserInfo.getProviderId();
	}
	
	
}