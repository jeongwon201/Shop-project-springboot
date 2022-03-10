package com.shop.domain.Account.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.Account.dto.AccountDto;
import com.shop.domain.Account.service.AccountService;
import com.shop.domain.EmailToken.service.EmailTokenService;
import com.shop.global.common.response.ResponseObject;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountApiController {

	private final AccountService service;
	private final EmailTokenService emailTokenService;
	
	@PostMapping("")
	public ResponseEntity<?> register(@Validated AccountDto accountDto, BindingResult bindingResult) throws Exception {
		if (service.countAll() == 0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseObject<>(HttpStatus.FORBIDDEN));
		}

		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseObject<>(HttpStatus.UNPROCESSABLE_ENTITY));
		}

		if (service.doubleCheck(accountDto.getUsername()) != 0) {
			
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseObject<>(HttpStatus.CONFLICT));
		}
		
		service.register(accountDto.toEntity());
		emailTokenService.createEmailToken(accountDto.getUsername());

		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject<>(HttpStatus.CREATED));
	}

	@GetMapping("/{username}")
	public ResponseEntity<?> register(@PathVariable String username) throws Exception {

		if (service.doubleCheck(username) != 0) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseObject<>(HttpStatus.CONFLICT));
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject<>(HttpStatus.OK));
	}

	@PostMapping("/admin")
	public ResponseEntity<?> registerAdmin(@Validated AccountDto accountDto, BindingResult bindingResult) throws Exception {
		if (service.countAll() != 0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseObject<>(HttpStatus.FORBIDDEN));
		}

		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseObject<>(HttpStatus.UNPROCESSABLE_ENTITY));
		}

		service.setupAdmin(accountDto.toEntity());
		emailTokenService.createEmailToken(accountDto.getUsername());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject<>(HttpStatus.CREATED));
	}

}