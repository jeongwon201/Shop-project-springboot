package com.shop.service;

import org.springframework.stereotype.Service;

import com.shop.domain.Member;
import com.shop.domain.MemberAuth;
import com.shop.enums.MemberAuthEnum;
import com.shop.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository repository;
	
	@Override
	public long countAll() throws Exception {
		return repository.count();
	}

	@Override
	public void setupAdmin(Member member) throws Exception {
		MemberAuth memberAuth = new MemberAuth();
		memberAuth.setAuth(MemberAuthEnum.ADMIN);
		member.addAuth(memberAuth);
		System.out.println(member.getUserPw());
		repository.save(member);
	}

}
