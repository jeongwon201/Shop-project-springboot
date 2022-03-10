package com.shop.domain.Account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.domain.Account.dto.AccountDto;
import com.shop.domain.Account.service.AccountService;
import com.shop.domain.EmailToken.domain.EmailToken;
import com.shop.domain.EmailToken.service.EmailTokenService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
	
	private final AccountService service;
	private final EmailTokenService emailTokenService;
	
	@GetMapping("/register")
	public String registerForm(AccountDto accountDto) throws Exception {
		
		return "user/account/register";
	}
	
	@GetMapping("/admin")
	public String setupAdminForm(AccountDto accountDto) throws Exception {
		
		if(service.countAll() == 0) {
			return "user/account/admin";
		}
		
		return "/";
	}
	
	@GetMapping("/verify-email/{token}")
	public String verifyEmail(@PathVariable("token") String token) throws Exception {
		
		EmailToken emailToken = emailTokenService.findByTokenAndExpirationDateAfterAndExpired(token);
		
		service.verifyEmail(emailToken.getUsername());
		
		return null;
	}
}