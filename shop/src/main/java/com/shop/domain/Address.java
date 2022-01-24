package com.shop.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addrId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_no")
	private Member member;
	
	@Column(length = 20, nullable = false)
	private String addrName;
	
	@Column(length = 6, nullable = false)
	private String zipCode;
	
	@Column(length = 100, nullable = false)
	private String addrSt;
	
	@Column(length = 100, nullable = false)
	private String addrDetail;
	
	@Column(length = 22, nullable = false)
	private String recipientName;
	
	@Column(length = 11, nullable = false)
	private String recipientPhone;
	
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@UpdateTimestamp
	private LocalDateTime updDate;
	
	@Column(length = 1, nullable = false)
	private String deleteYn;
	
	@Builder
	public Address(Member member, String addrName, String zipCode, String addrSt, String addrDetail, String recipientName, String recipientPhone) {
		this.member = member;
		this.addrName = addrName;
		this.zipCode = zipCode;
		this.addrSt = addrSt;
		this.addrDetail = addrDetail;
		this.recipientName = recipientName;
		this.recipientPhone = recipientPhone;
	}
}
