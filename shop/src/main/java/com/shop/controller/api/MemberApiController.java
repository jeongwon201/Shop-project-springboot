package com.shop.controller.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.Member;
import com.shop.dto.MemberDto;
import com.shop.dto.ResponseDto;
import com.shop.service.MemberService;
import com.shop.valid.ValidationSequence;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberApiController {

	private final PasswordEncoder passwordEncoder;
	private final MemberService service;

	@PostMapping("/setup")
	public ResponseEntity<ResponseDto> setupAdmin(@Validated(ValidationSequence.class) MemberDto memberDto, BindingResult result) throws Exception {
		if (service.countAll() != 0) {
			ResponseDto responseDto = ResponseDto.builder()
					.status(HttpStatus.FORBIDDEN)
					.message("Admin already exists !")
					.data(memberDto)
					.build();
			
			return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
		}

		if (result.hasErrors()) {
			ResponseDto responseDto = ResponseDto.builder()
					.status(HttpStatus.BAD_REQUEST)
					.message("Validation Error !")
					.data(memberDto)
					.build();
			
			List<FieldError> errorList = result.getFieldErrors();
	        for (FieldError error : errorList) {
	            System.out.print(error.getField() + " [" + error.getCode() + "] : ");
	            System.out.println(error.getDefaultMessage());
	        }
	        
			return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
		}
		
		Member member = memberDto.toMemberEntity();
		String inputPw = member.getUserPw();
		member.setUserPw(passwordEncoder.encode(inputPw));
		
		service.setupAdmin(member);
		
		ResponseDto responseDto = ResponseDto.builder()
				.status(HttpStatus.CREATED)
				.message("Success !")
				.data(memberDto)
				.build();
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}
}