package com.shop.global.common.response;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ResponseMessage {
	private HttpStatus status;
	private String message;
	
	public ResponseMessage(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
}