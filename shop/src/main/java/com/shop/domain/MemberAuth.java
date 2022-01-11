package com.shop.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.shop.enums.MemberAuthEnum;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of="memberAuthId")
@ToString
@Entity
@Table(name="member_auth")
public class MemberAuth {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberAuthId;
	
	@Column(name="user_no")
	private Long userNo;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 15, nullable=false)
	private MemberAuthEnum auth;
	
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@UpdateTimestamp
	private LocalDateTime updDate;
}