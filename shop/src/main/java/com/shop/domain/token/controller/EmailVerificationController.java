package com.shop.domain.token.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.global.common.security.domain.PrincipalDetails;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/emailVerification")
public class EmailVerificationController {
	
	@GetMapping("")
	public String verifyEmail(@AuthenticationPrincipal PrincipalDetails principal) throws Exception {
		
		if(principal.getAccount().getAuth() != "ROLE_GUEST") {
			return "/";
		}
		
		return "user/emailVerification/verify-email";
	}
	
}