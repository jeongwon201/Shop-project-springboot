package com.shop.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.Member;
import com.shop.dto.ResponseDto;
import com.shop.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberApiController {

	private final MemberService service;
	
//	@PostMapping("/setup")
//	public ResponseEntity<ResponseDto> setupAdmin(@Validated Member member, BindingResult result) throws Exception {
//		
//	}
}
