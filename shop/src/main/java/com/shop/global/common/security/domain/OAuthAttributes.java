package com.shop.global.common.security.domain;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.shop.domain.Account.domain.Account;

import lombok.Getter;

@Getter
public class OAuthAttributes {
	
	private String provider;
	private OAuth2User oAuth2User;
	
	public OAuthAttributes(String provider, OAuth2User oAuth2User) {
		this.provider = provider;
		this.oAuth2User = oAuth2User;
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
