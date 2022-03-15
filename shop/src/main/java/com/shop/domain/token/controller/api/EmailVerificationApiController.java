package com.shop.domain.token.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.token.service.EmailSender;
import com.shop.domain.token.service.VerificationTokenService;
import com.shop.global.common.response.ResponseMessage;
import com.shop.global.common.security.domain.PrincipalDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emailVerification")
public class EmailVerificationApiController {
	
	private final VerificationTokenService service;
	private final EmailSender emailSender;
	
	@PostMapping("")
	public ResponseEntity<ResponseMessage> createEmailVerification(@AuthenticationPrincipal PrincipalDetails principal) throws Exception {
		
		service.createEmailToken(principal.getAccount());
		
		SimpleMailMessage mailMessage = emailSender.setEmailVerificationMessage(principal.getAccount());
		emailSender.sendEmail(mailMessage);
		
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.CREATED, "가입하신 이메일로 인증 링크가 전송되었습니다."), HttpStatus.CREATED);
	}
	
}