package com.shop.domain.Account.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.Account.domain.Account;
import com.shop.domain.Account.dto.AccountDto;
import com.shop.domain.Account.service.AccountService;
import com.shop.domain.token.domain.VerificationToken;
import com.shop.domain.token.service.EmailSender;
import com.shop.domain.token.service.VerificationTokenService;
import com.shop.global.common.response.ResponseMessage;
import com.shop.global.common.security.domain.PrincipalDetails;
import com.shop.global.utils.emuns.UserRole;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountApiController {

	private final AccountService service;
	private final VerificationTokenService tokenService;
	private final EmailSender emailSender;

	@PostMapping("")
	public ResponseEntity<ResponseMessage> register(@Validated AccountDto accountDto, BindingResult bindingResult) throws Exception {
		if (service.countAll() == 0) {
			return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.FORBIDDEN, "관리자를 먼저 생성하세요."), HttpStatus.FORBIDDEN);
		}

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.UNPROCESSABLE_ENTITY, "양식에 맞는 값을 입력하세요."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		if (service.doubleCheck(accountDto.getUsername()) != 0) {
			return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.CONFLICT, "이미 사용중인 이메일입니다."), HttpStatus.CONFLICT);

		}

		service.register(accountDto.toEntity());
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.CREATED, "계정 생성이 완료되었습니다."), HttpStatus.CREATED);
	}

	@GetMapping("/{username}")
	public ResponseEntity<ResponseMessage> register(@PathVariable String username) throws Exception {

		if (service.doubleCheck(username) != 0) {
			return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.CONFLICT, "이미 사용중인 이메일입니다."), HttpStatus.CONFLICT);
		}

		return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.OK, ""), HttpStatus.OK);
	}

	@PostMapping("/admin")
	public ResponseEntity<ResponseMessage> registerAdmin(@Validated AccountDto accountDto, BindingResult bindingResult) throws Exception {
		if (service.countAll() != 0) {
			return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.FORBIDDEN, "이미 생성하였습니다."), HttpStatus.FORBIDDEN);
		}

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.UNPROCESSABLE_ENTITY, "양식에 맞는 값을 입력하세요."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		service.setupAdmin(accountDto.toEntity());

		return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.CREATED, "계정 생성이 완료되었습니다."), HttpStatus.CREATED);
	}
	
	@PostMapping("/verify-email")
	public ResponseEntity<ResponseMessage> createEmailVerification(@AuthenticationPrincipal PrincipalDetails principal) throws Exception {
		
		tokenService.createEmailToken(principal.getAccount());
		
		SimpleMailMessage mailMessage = emailSender.setEmailVerificationMessage(principal.getAccount());
		emailSender.sendEmail(mailMessage);
		
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.CREATED, "가입하신 이메일로 인증 링크가 전송되었습니다."), HttpStatus.CREATED);
	}
	
	@PutMapping("/verify-email/{token}")
	public ResponseEntity<ResponseMessage> updateAuthToUser(@PathVariable("token") String token) throws Exception {
		VerificationToken verificationToken = tokenService.findByTokenAndExpirationDateAfterAndExpired(token);
		
		Account account = service.findById(verificationToken.getAccount().getUserId()).orElse(null);
		
		if(account == null) {
			
		}
		
		account.updateAuth(UserRole.USER);
		
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.CREATED, "가입하신 이메일로 인증 링크가 전송되었습니다."), HttpStatus.CREATED);
	}

}