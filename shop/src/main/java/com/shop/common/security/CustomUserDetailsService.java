package com.shop.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.shop.domain.CustomUser;
import com.shop.domain.Member;
import com.shop.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("username = " + username);
		
		Member member = repository.findByUserId(username).get(0);
		
		return member == null ? null : new CustomUser(member);
	}

}
