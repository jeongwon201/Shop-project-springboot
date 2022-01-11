package com.shop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of="userNo")
@ToString
@Entity
@Table(name="member")
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userNo;
	
	@Column(length = 15, nullable=false)
	private String userId;
	
	@Column(length = 15, nullable=false)
	private String userPw;
	
	@Column(length = 20, nullable=false)
	private String userName;
	
	@Column(length = 11, nullable=false)
	private String phone;
	
	@Column(length = 30, nullable=false)
	private String email;
	
	@Column(length = 16, nullable = false)
	private String nickname;
	
	@Column(length = 1, nullable = false)
	private String deleteYn;
	
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@UpdateTimestamp
	private LocalDateTime updDate;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_no")
	private List<MemberAuth> authList = new ArrayList<MemberAuth>();
	
	public void addAuth(MemberAuth auth) {
		authList.add(auth);
	}
}
