package com.shop.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.dto.AddressDto;
import com.shop.dto.ResponseDto;
import com.shop.service.AddressService;
import com.shop.valid.ValidationSequence;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressApiController {
	
	private final AddressService service;
	
//	@PostMapping("/setup")
//	public ResponseEntity<ResponseDto> addressSetup(@Validated(ValidationSequence.class) AddressDto addressDto, BindingResult result) throws Exception {
//		
//		if(service.countAll() != 0) {
//			ResponseDto responseDto = ResponseDto.builder()
//			.status(HttpStatus.FORBIDDEN)
//			.message("Admin's address already exists !")
//			.data(addressDto)
//			.build();
//			
//			return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
//		}
//		
//		if(result.hasErrors()) {
//			ResponseDto responseDto = ResponseDto.builder()
//					.status(HttpStatus.BAD_REQUEST)
//					.message("Validation Error !")
//					.data(addressDto)
//					.build();
//			
//			return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
//		}
//		
//		//Address address = addressDto.
//	}
}
