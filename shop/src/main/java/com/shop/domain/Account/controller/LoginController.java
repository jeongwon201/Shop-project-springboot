package com.shop.domain.Account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class LoginController {
	
	@RequestMapping("/login")
	public String loginForm() throws Exception {
		return "user/account/login";
	}
}
