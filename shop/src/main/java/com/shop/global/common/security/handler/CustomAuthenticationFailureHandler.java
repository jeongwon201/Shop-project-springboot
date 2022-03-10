package com.shop.global.common.security.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		if (exception instanceof AuthenticationServiceException) {
			System.out.println("error : AuthenticationServiceException");
			request.setAttribute("msg", "Error");

		} else if (exception instanceof InternalAuthenticationServiceException) {
			System.out.println("error : InternalAuthenticationServiceException");
			request.setAttribute("msg", "아이디 또는 비밀번호가 틀립니다.");

		}else if (exception instanceof BadCredentialsException) {
			System.out.println("error : BadCredentialsException");
			request.setAttribute("msg", "아이디 또는 비밀번호가 틀립니다.");

		} else if (exception instanceof LockedException) {
			System.out.println("error : LockedException");
			request.setAttribute("msg", "이메일 인증을 완료해주세요.");

		} else if (exception instanceof DisabledException) {
			System.out.println("error : DisabledException");
			request.setAttribute("msg", "비활성화된 계정입니다..");

		} else if (exception instanceof AccountExpiredException) {
			System.out.println("error : AccountExpiredException");
			request.setAttribute("msg", "만료된 계정입니다..");

		} else if (exception instanceof CredentialsExpiredException) {
			System.out.println("error : CredentialsExpiredException");
			request.setAttribute("msg", "비밀번호가 만료되었습니다.");
		}
		
		request.getRequestDispatcher("/account/login").forward(request, response);
	}

}
