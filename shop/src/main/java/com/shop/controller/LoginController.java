package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {
	
	@GetMapping("/login")
	public String loginForm(String error, String logout, Model model) throws Exception {
		if(error != null) {
			model.addAttribute("error", "Login Error !");
		}
		
		if(logout != null) {
			model.addAttribute("logout", "Logout !");
		}
		
		return "auth/login";
	}
}
