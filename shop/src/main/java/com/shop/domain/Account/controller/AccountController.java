package com.shop.domain.Account.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.domain.Account.dto.AccountDto;
import com.shop.domain.Account.service.AccountService;
import com.shop.global.common.security.domain.PrincipalDetails;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

	private final AccountService service;

	@GetMapping("/register")
	public String registerForm(AccountDto accountDto) throws Exception {

		return "user/account/register";
	}

	@GetMapping("/admin")
	public String setupAdminForm(AccountDto accountDto) throws Exception {

		if (service.countAll() == 0) {
			return "user/account/admin";
		}

		return "/";
	}

	@GetMapping("/verify-email")
	public String verifyEmail(@AuthenticationPrincipal PrincipalDetails principal) throws Exception {

		if (principal.getAccount().getAuth() != "ROLE_GUEST") {
			return "/";
		}

		return "user/account/verify-email";
	}

	@GetMapping("/mypage")
	public String mypage() throws Exception {
		return null;
	}
}