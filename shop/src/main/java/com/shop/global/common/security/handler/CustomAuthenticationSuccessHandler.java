package com.shop.global.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.shop.domain.Account.domain.Account;
import com.shop.global.common.security.domain.CustomUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		CustomUser customUser = (CustomUser)authentication.getPrincipal();
		
		Account account = customUser.getAccount();
		
		log.info(account.getUsername() + "님이 로그인하셨습니다.");
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
