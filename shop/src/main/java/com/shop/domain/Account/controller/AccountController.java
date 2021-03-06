package com.shop.domain.Account.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@PreAuthorize("hasRole('GUEST')")
	@GetMapping("/verify-email/{token}")
	public String updateAuthToUser(@PathVariable("token") String token, RedirectAttributes rttr) throws Exception {
		VerificationToken verificationToken = verificationTokenService.findByTokenAndExpirationDateAfterAndExpired(token);
		
		Account account = service.findById(verificationToken.getUserId()).orElse(null);
		
		if(account == null) {
			System.out.println("?????? ????????? ???????????? ?????????, ????????? ???????????????.");
			rttr.addAttribute("msg", "?????? ????????? ???????????? ?????????, ????????? ???????????????.");
			return "redirect:/account/verify-email";
		}
		
		service.verifyEmail(account);
		verificationTokenService.useToken(verificationToken);
		
		rttr.addFlashAttribute("msg", "verifyEmailSuccess");
		
		return "redirect:/";
	}

	@GetMapping("/mypage")
	public String mypage() throws Exception {
		return null;
	}
}