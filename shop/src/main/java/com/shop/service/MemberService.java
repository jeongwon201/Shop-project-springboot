package com.shop.service;

import com.shop.domain.Member;

public interface MemberService {
	
	public long countAll() throws Exception;
	
	public void setupAdmin(Member member) throws Exception;
}