package com.shop.global.common.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.shop.domain.Account.domain.Account;
import com.shop.domain.Account.repository.AccountRepository;
import com.shop.global.common.security.domain.OAuthAttributes;
import com.shop.global.common.security.domain.PrincipalDetails;
import com.shop.global.common.security.userInfo.KakaoUserInfo;
import com.shop.global.common.security.userInfo.OAuth2UserInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {
	
	
	private final AccountRepository repository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
		
		OAuth2UserInfo oAuth2UserInfo = null;
		String provider = oAuth2UserRequest.getClientRegistration().getRegistrationId();
		
		if(provider.equals("kakao")) {
			oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
		}
		
		Account account = Account.builder()
				.username(provider + "_" + oAuth2UserInfo.getProviderId() + "_" + oAuth2UserInfo.getEmail())
				.password()
		
		return new PrincipalDetails(account, oAuth2User.getAttributes());
	}
	
	private Account saveOrUpdate(OAuthAttributes attributes) {
		Account account = repository.findByUsername(attributes.getEmail())
				.map(entity -> entity.oAuthUpdate(attributes.getNickname()))
				.orElse(attributes.toEntity());
		
		
		
		return repository.save(account);
	}
}
