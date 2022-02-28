package com.shop.domain.Account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class LoginController {
	
	@GetMapping("/login")
	public String loginForm(String error) throws Exception {
		System.out.println(error);
		return "user/account/login";
	}
}
