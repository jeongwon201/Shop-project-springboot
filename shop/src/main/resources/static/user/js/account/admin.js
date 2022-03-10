let index = {
	
	usernameError: true,
	passwordError: true,
	passwordConfirmError: true,
	nicknameError: true,

	init: function() {
		$("#btn-save").on("click", () => {
			let validResult = this.validAll();
			if (validResult !== "pass") {
				validResult.focus();
				return false;
			} else {
				this.save();
			}
		});

		$("#username").on("keyup", () => {
			this.valid(1);
		});

		$("#password").on("keyup", () => {
			this.valid(2);
		});

		$("#passwordConfirm").on("keyup", () => {
			this.valid(3);
		});

		$("#nickname").on("keyup", () => {
			this.valid(4);
		});

	},

	save: function() {
		
		let account = $("#account").serialize();

		$.ajax({
			type: "POST",
			url: "/account/admin",
			data: account,
		}).done(function(res) {
			if(res.status == "CREATED") {
				alert("관리자 생성이 완료되었습니다.");
				location.href="/account/login";
			} else {
				alert("관리자 생성에 실패하였습니다.")
			}
			
		}).fail(function(res) {
			if(res.status == "FORBIDDEN") {
				alert("이미 관리자가 존재합니다.");
			} else if(res.status == "UNPROCESSABLE_ENTITY") {
				alert("필드 양식에 맞는 값을 입력해주세요.")
			} else {
				alert(res.status);
				alert("관리자 생성에 실패하였습니다.");
			}
		});
	},

	valid: function(param) {

		const username = $("#username");
		const password = $("#password");
		const passwordConfirm = $("#passwordConfirm");
		const nickname = $("#nickname");

		switch (param) {
			case 1: {
				const idPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
				const error = $("#username_error");

				if (username.val() === "") {
					error.html("필수 정보입니다.");
					this.usernameError = true;
				} else if (!idPattern.test(username.val())) {
					error.html("올바른 형식의 이메일을 입력해주세요.");
					this.usernameError = true;
				} else {
					error.html("");
					this.usernameError = false;
				}

				break;
			}

			case 2: {
				const pwPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&#^()_+])[A-Za-z\d@$!%*?&#^()_+]{10,20}$/;
				const error = $("#password_error");

				if (password.val() === "") {
					error.html("필수 정보입니다.");
					this.passwordError = true;
				} else if (!pwPattern.test(password.val())) {
					error.html("영문 대/소문자, 숫자, 특수문자를 포함하여 10 - 20자로 구성해야합니다.");
					this.passwordError = true;
				} else {
					error.html("");
					this.passwordError = false;
				}

				if (passwordConfirm.val() !== "" && password.val() !== passwordConfirm.val()) {
					index.valid(3);
				} else {
					index.valid(3);
				}

				break;
			}

			case 3: {
				const error = $("#passwordConfirm_error");

				if (passwordConfirm.val() === password.val() && passwordConfirm.val() != "") {
					error.html("");
					this.passwordConfirmError = false;
				} else if (passwordConfirm.val() !== password.val()) {
					error.html("비밀번호가 일치하지 않습니다.");
					this.passwordConfirmError = true;
				}
				if (passwordConfirm.val() === "") {
					error.html("필수 정보입니다.");
					this.passwordConfirmError = true;
				}

				break;
			}

			case 4: {
				const namePattern = /^[가-힣]{2,11}|[a-zA-Z]{2,11}$/;
				const error = $("#nickname_error");

				if (nickname.val() === "") {
					error.html("필수 정보입니다.");
					this.nicknameError = true;
				} else if (!namePattern.test(nickname.val()) || nickname.val().indexOf(" ") > -1) {
					error.html("2 - 11자의 한글, 영문 대/소문자만 가능합니다.");
					this.nicknameError = true;
				} else {
					error.html("");
					this.nicknameError = false;
				}

				break;
			}


			default: {

				break;
			}
		}
	},

	validAll: function() {
		for (i = 1; i < 5; i++) {
			this.valid(i);
		}

		if (this.usernameError === true) {
			return $("#username");
		} else if (this.passwordError === true) {
			return $("#password");
		} else if (this.passwordConfirmError === true) {
			return $("#passwordConfirm");
		} else if (this.nicknameError === true) {
			return $("#nickname");
		} else {
			return "pass";
		}
	},
}

index.init();