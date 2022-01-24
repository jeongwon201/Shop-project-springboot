package com.shop.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.shop.domain.Member;
import com.shop.valid.ValidationGroups;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto {
	
	@NotBlank(message = "필수 정보입니다.", groups = ValidationGroups.NotBlankGroup.class)
	private String zipCode;
	
	@NotBlank(message = "필수 정보입니다.", groups = ValidationGroups.NotBlankGroup.class)
	private String addrSt;
	
	@NotBlank(message = "필수 정보입니다.", groups = ValidationGroups.NotBlankGroup.class)
	private String addrDetail;
	
	/*
	 * public Address toMemberEntity() { return Address.builder() . .build(); }
	 */
	
}