package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.domain.Member;
import com.shop.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

	private final MemberService service;
	
	@GetMapping("/setup")
	public String setupAdminForm(Member member, Model model) throws Exception {
		
		if(service.countAll() == 0) {
			return "user/setup";
		}
		return "user/setup-fail";
	}

}