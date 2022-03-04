package com.shop.global.common.security.service;

import java.security.SecureRandom;
import java.util.Date;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.shop.domain.Account.domain.Account;
import com.shop.domain.Account.repository.AccountRepository;
import com.shop.global.common.security.domain.PrincipalDetails;
import com.shop.global.common.security.userInfo.KakaoUserInfo;
import com.shop.global.common.security.userInfo.OAuth2UserInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {
	
	
	private final AccountRepository repository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
		
		OAuth2UserInfo oAuth2UserInfo = null;
		String provider = oAuth2UserRequest.getClientRegistration().getRegistrationId();
		
		if(provider.equals("kakao")) {
			oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
		}
		
		Account oAuthAccount = Account.builder()
				.username(provider + "_" + oAuth2UserInfo.getProviderId() + "_" + oAuth2UserInfo.getEmail())
				.password(setRandomPassword())
				.nickname(oAuth2UserInfo.getName())
				.oAuth(provider)
				.build();
		
		Account account = repository.findByUsername(oAuthAccount.getUsername())
				.orElse(account = oAuthAccount);
		
		return new PrincipalDetails(account, oAuth2UserInfo);
	}
	
	
	public String setRandomPassword() {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&' };

		StringBuffer sb = new StringBuffer();
		SecureRandom sr = new SecureRandom();
		sr.setSeed(new Date().getTime());

		int idx = 0;
		int len = charSet.length;
		for (int i = 0; i < 15; i++) {
			idx = sr.nextInt(len);
			sb.append(charSet[idx]);
		}

		return sb.toString();
	}
}
