package com.shop.domain.EmailToken;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.domain.EmailToken.service.EmailTokenService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/verify-email")
public class EmailTokenController {
	
	private final EmailTokenService service;
	
	@GetMapping("/{token}")
	public String verify(@PathVariable("token") String token) {
		
		if(service.verify(token) != true) {
			return null;
		}
		
		return null;
	}
}
