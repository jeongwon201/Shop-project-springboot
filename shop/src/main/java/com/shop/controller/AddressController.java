package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.service.AddressService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

	private final AddressService service;
	
	@GetMapping("/setup")
	public String addressForm() {
		return "address/setup";
	}
}
