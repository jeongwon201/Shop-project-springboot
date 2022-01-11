package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
