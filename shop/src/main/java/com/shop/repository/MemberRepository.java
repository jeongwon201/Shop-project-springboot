package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	public List<Member> findByUserId(String userId);
}
