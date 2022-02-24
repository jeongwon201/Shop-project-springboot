package com.shop.domain.Account.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.domain.Account.dto.AccountDto;
import com.shop.domain.Account.service.AccountService;

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
		
		if(service.countAll() == 0) {
			return "user/account/admin";
		}
		
		return "/";
	}
	
	@GetMapping("/login")
	public String loginForm() throws Exception {
		return "user/account/login";
	}
}