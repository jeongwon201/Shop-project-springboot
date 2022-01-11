package com.shop.service;

import org.springframework.stereotype.Service;

import com.shop.domain.Member;
import com.shop.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private MemberRepository repository;
	
	@Override
	public long countAll() throws Exception {
		return 0;
	}

	@Override
	public void setupAdmin(Member member) throws Exception {

	}

}
