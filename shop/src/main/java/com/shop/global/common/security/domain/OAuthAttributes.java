package com.shop.global.common.security.domain;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;

import com.shop.domain.Account.domain.Account;

import lombok.Getter;

@Getter
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String email;
	private String nickname;

	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String email, String nickname) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.email = email;
		this.nickname = nickname;
	}

	public OAuthAttributes() {

	}

	public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
		if (registrationId.equals("kakao")) {
			return ofKakao(userNameAttributeName, attributes);
		}

		return ofNaver(userNameAttributeName, attributes);
	}

	private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
		Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
		Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");

		return new OAuthAttributes(attributes, userNameAttributeName, (String) kakao_account.get("email"), (String) profile.get("nickname"));
	}

	private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
		Map<String, Object> response = (Map<String, Object>) attributes.get("response");

		return new OAuthAttributes(attributes, userNameAttributeName, (String) response.get("email"), (String) response.get("nickname"));
	}

	public Account toEntity() {
		return Account.builder()
				.username(this.email)
				.password(getRandomPassword())
				.nickname(this.nickname)
				.build();
	}

	private String getRandomPassword() {
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
