package com.shop.domain.Account.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.domain.Account.domain.Account;
import com.shop.domain.Account.dto.AccountDto;
import com.shop.domain.Account.service.AccountService;
import com.shop.domain.verificationToken.domain.VerificationToken;
import com.shop.domain.verificationToken.service.VerificationTokenService;
import com.shop.global.common.security.domain.PrincipalDetails;
import com.shop.global.utils.emuns.UserRole;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

	private final AccountService service;
	private final VerificationTokenService verificationTokenService;

	@GetMapping("/register")
	public String registerForm(AccountDto accountDto) throws Exception {

		return "user/account/register";
	}

	@GetMapping("/admin")
	public String setupAdminForm(AccountDto accountDto) throws Exception {

		if (service.countAll() == 0) {
			return "user/account/admin";
		}

		return "/";
	}
	
	@PreAuthorize("hasRole('GUEST')")
	@GetMapping("/verify-email")
	public String verifyEmail(@AuthenticationPrincipal PrincipalDetails principal) throws Exception {

		if (principal.getAccount().getAuth().equals(UserRole.GUEST.getValue())) {
			return "user/account/verify-email";
		}

		return "redirect:/";
	}
	
	@PutMapping("/verify-email/{token}")
	public String updateAuthToUser(@PathVariable("token") String token) throws Exception {
		VerificationToken verificationToken = verificationTokenService.findByTokenAndExpirationDateAfterAndExpired(token);
		
		Account account = service.findById(verificationToken.getAccount().getUserId()).orElse(null);
		
		if(account == null) {
			System.out.println("토큰 정보가 일치하지 않거나, 만료된 토큰입니다.");
		}
		
		account.updateAuth(UserRole.USER);
		
		return "redirect:/account/verify-email";
	}

	@GetMapping("/mypage")
	public String mypage() throws Exception {
		return null;
	}
}