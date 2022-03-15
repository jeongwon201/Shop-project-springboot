package com.shop.global.common.response;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ResponseObject<T> {
	private HttpStatus status;
	private T data;
	private String message;
	
	public ResponseObject(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public ResponseObject(HttpStatus status, T data, String message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}
	
}