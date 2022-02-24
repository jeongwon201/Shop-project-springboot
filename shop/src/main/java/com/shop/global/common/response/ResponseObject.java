package com.shop.global.common.response;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ResponseObject<T> {
	private HttpStatus status;
	private T data;
	
	public ResponseObject(HttpStatus status) {
		this.status = status;
	}
	
	public ResponseObject(HttpStatus status, T data) {
		this.status = status;
		this.data = data;
	}
	
}