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
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userNo;

	@Column(length = 11, nullable = false)
	private String userId;

	@Column(length = 60, nullable = false)
	private String userPw;

	@Column(length = 22, nullable = false)
	private String userName;

	@Column(length = 50, nullable = false)
	private String email;

	@Column(length = 11, nullable = false)
	private String phone;

	@Column(length = 1, nullable = false)
	private String deleteYn = "N";

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

	@Builder
	public Member(String userId, String userPw, String userName, String phone, String email) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
	}
}
