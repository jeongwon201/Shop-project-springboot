package com.shop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberAuthEnum {
	ADMIN("ADMIN"),
	EMPLOYEE("EMPLOYEE"),
	CUSTOMER("CUSTOMER");
	
	private String description;
}