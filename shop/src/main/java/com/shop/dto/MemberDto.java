package com.shop.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.shop.domain.Member;
import com.shop.valid.ValidationGroups;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {
	
	@NotBlank(message = "필수 정보입니다.", groups = ValidationGroups.NotBlankGroup.class)
	@Pattern(regexp = "[A-Za-z]{1}[A-Za-z0-9_]{5,11}", message = "5 - 11자의 영문 대/소문자, 숫자, 특수기호(_)만 사용 가능하며, 첫 글자는 영문 소문자만 가능합니다.", groups = ValidationGroups.PatternGroup.class)
	private String userId;
	
	@NotBlank(message = "필수 정보입니다.", groups = ValidationGroups.NotBlankGroup.class)
	@Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#^])[A-Za-z\\d@$!%*?&#^]{10,20}", message = "영문 대/소문자, 숫자, 특수문자를 포함하여 10 - 20자로 구성해야합니다.", groups = ValidationGroups.PatternGroup.class)
	private String userPw;
	
	@NotBlank(message = "필수 정보입니다.", groups = ValidationGroups.NotBlankGroup.class)
	@Pattern(regexp = "^[가-힣]{2,11}|[a-zA-Z]{2,11}$", message = "2 - 11자의 한글, 영문 대/소문자만 가능합니다.", groups = ValidationGroups.PatternGroup.class)
	private String userName;
	
	@NotBlank(message = "필수 정보입니다.", groups = ValidationGroups.NotBlankGroup.class)
	@Pattern(regexp = "([0-9]{2,3})([0-9]{3,4})([0-9]{4})", message = "올바른 전화번호 형식을 입력해주세요.", groups = ValidationGroups.PatternGroup.class)
	private String phone;
	
	@NotBlank(message = "필수 정보입니다.", groups = ValidationGroups.NotBlankGroup.class)
	@Pattern(regexp = "^([\\w\\.\\_\\-])*[a-zA-Z0-9]+([\\w\\.\\_\\-])*([a-zA-Z0-9])+([\\w\\.\\_\\-])+@([a-zA-Z0-9]+\\.)+[a-zA-Z0-9]{2,8}$", message = "올바른 이메일 형식을 입력해주세요.", groups = ValidationGroups.PatternGroup.class)
	private String email;
	
	public Member toMemberEntity() {
		return Member.builder()
				.userId(userId)
				.userPw(userPw)
				.userName(userName)
				.phone(phone)
				.email(email)
				.build();
	}
	
}