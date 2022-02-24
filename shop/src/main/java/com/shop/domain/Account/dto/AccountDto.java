package com.shop.domain.Account.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.shop.domain.Account.domain.Account;
import com.shop.global.utils.emuns.UserRole;
import com.shop.global.utils.validation.ValidationGroups;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {
	
	@NotBlank(message = "필수 정보입니다.", groups = ValidationGroups.NotBlankGroup.class)
	@Pattern(regexp = "[A-Za-z]{1}[A-Za-z0-9_]{5,11}", message = "5 - 11자의 영문 대/소문자, 숫자, 특수기호(_)만 사용 가능하며, 첫 글자는 영문 소문자만 가능합니다.", groups = ValidationGroups.PatternGroup.class)
	private String username;
	
	@NotBlank(message = "필수 정보입니다.", groups = ValidationGroups.NotBlankGroup.class)
	@Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#^])[A-Za-z\\d@$!%*?&#^]{10,20}", message = "영문 대/소문자, 숫자, 특수문자를 포함하여 10 - 20자로 구성해야합니다.", groups = ValidationGroups.PatternGroup.class)
	private String password;
	
	@NotBlank(message = "필수 정보입니다.", groups = ValidationGroups.NotBlankGroup.class)
	@Pattern(regexp = "^[가-힣]{2,11}|[a-zA-Z]{2,11}$", message = "2 - 11자의 한글, 영문 대/소문자만 가능합니다.", groups = ValidationGroups.PatternGroup.class)
	private String nickname;
	
	private final UserRole role = UserRole.USER;
	
	public Account toEntity() {
		return Account.builder()
				.username(this.username)
				.password(this.password)
				.nickname(this.nickname)
				.build();
	}
	
}