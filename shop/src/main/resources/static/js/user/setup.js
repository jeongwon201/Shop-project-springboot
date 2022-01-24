let index = {

	userIdError: true,
	userPwError: true,
	userPwConfirmError: true,
	userNameError: true,
	phoneError: true,
	emailError: true,
	
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

		$("#userId").on("keyup", () => {
			this.valid(1);
		});

		$("#userPw").on("keyup", () => {
			this.valid(2);
		});

		$("#userPwConfirm").on("keyup", () => {
			this.valid(3);
		});

		$("#userName").on("keyup", () => {
			this.valid(4);
		});

		$("#phone").on("keyup", () => {
			this.valid(5);
		});

		$("#email").on("keyup", () => {
			this.valid(6);
		});
		
	},

	save: function() {

		let member = $("#member").serialize();

		$.ajax({
			type: "POST",
			url: "/user/setup",
			data: member,
		}).done(function(resp) {
			alert(resp.status);
			if (resp.status == "CREATED") {
				alert("관리자 등록이 완료되었습니다.");
				location.href = "/address/setup";
			}
		}).fail(function(error) {
			if (error.status == "BAD_REQUEST") {
				alert("입력하신 정보를 다시 확인하세요.")
			} else if (error.status == "FORBIDDEN") {
				alert("이미 관리자가 존재합니다.")
			} else {
				alert("관리자를 생성할 수 없습니다.")
			}
		});
	},

	valid: function(param) {

		const userId = $("#userId");
		const userPw = $("#userPw");
		const userPwConfirm = $("#userPwConfirm");
		const userName = $("#userName");
		const phone = $("#phone");
		const email = $("#email")

		switch (param) {
			case 1: {
				const idPattern = /^[a-z]+[a-z0-9_]{5,11}$/;
				const idPattern2 = /^[a-z]/;
				const error = $("#userId_error");

				if (userId.val() === "") {
					error.html("필수 정보입니다.");
					this.userIdError = true;
				} else if (!idPattern2.test(userId.val())) {
					error.html("첫 글자는 영문 소문자만 가능합니다.");
					this.userIdError = true;
				} else if (!idPattern.test(userId.val())) {
					error.html("5 - 11자의 영문 대/소문자, 숫자, 특수기호(_)만 사용 가능합니다.");
					this.userIdError = true;
				} else {
					error.html("");
					this.userIdError = false;
				}

				break;
			}

			case 2: {
				const pwPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&#^()_+])[A-Za-z\d@$!%*?&#^()_+]{10,20}$/;
				const error = $("#userPw_error");

				if (userPw.val() === "") {
					error.html("필수 정보입니다.");
					this.userPwError = true;
				} else if (!pwPattern.test(userPw.val())) {
					error.html("영문 대/소문자, 숫자, 특수문자를 포함하여 10 - 20자로 구성해야합니다.");
					this.userPwError = true;
				} else {
					error.html("");
					this.userPwError = false;
				}

				if (userPwConfirm.val() !== "" && userPw.val() !== userPwConfirm.val()) {
					index.valid(3);
				} else {
					index.valid(3);
				}

				break;
			}

			case 3: {
				const error = $("#userPwConfirm_error");

				if (userPwConfirm.val() === userPw.val() && userPwConfirm.val() != "") {
					error.html("");
					this.userPwConfirmError = false;
				} else if (userPwConfirm.val() !== userPw.val()) {
					error.html("비밀번호가 일치하지 않습니다.");
					this.userPwConfirmError = true;
				}
				if (userPwConfirm.val() === "") {
					error.html("필수 정보입니다.");
					this.userPwConfirmError = true;
				}

				break;
			}

			case 4: {
				const namePattern = /^[가-힣]{2,11}|[a-zA-Z]{2,11}$/;
				const error = $("#userName_error");

				if (userName.val() === "") {
					error.html("필수 정보입니다.");
					this.userNameError = true;
				} else if (!namePattern.test(userName.val()) || userName.val().indexOf(" ") > -1) {
					error.html("2 - 11자의 한글, 영문 대/소문자만 가능합니다.");
					this.userNameError = true;
				} else {
					error.html("");
					this.userNameError = false;
				}

				break;
			}

			case 5: {
				const isPhoneNum = /([0-9]{2,3})([0-9]{3,4})([0-9]{4})/;
				const error = $("#phone_error");

				if (phone.val() === "") {
					error.html("필수 정보입니다.");
					this.phoneError = true;
				} else if (!isPhoneNum.test(phone.val())) {
					error.html("올바른 형식의 전화번호를 입력해주세요. (- 제외)");
					this.phoneError = true;
				} else {
					error.html("");
					this.phoneError = false;
				}

				break;
			}

			case 6: {
				const emailPattern = /^([\w\.\_\-])*[a-zA-Z0-9]+([\w\.\_\-])*([a-zA-Z0-9])+([\w\.\_\-])+@([a-zA-Z0-9]+\.)+[a-zA-Z0-9]{2,8}$/;
				const error = $("#email_error");

				if (email.val() === "") {
					error.html("필수 정보입니다.");
					this.emailError = true;
				} else if (!emailPattern.test(email.val())) {
					error.html("올바른 형식의 Email을 입력해주세요.");
					this.emailError = true;
				} else {
					error.html("");
					this.emailError = false;
				}

				break;
			}

			default: {

				break;
			}
		}
	},

	validAll: function() {
		for (i = 1; i < 8; i++) {
			this.valid(i);
		}

		if (this.userIdError === true) {
			return $("#userId");
		} else if (this.userPwError === true) {
			return $("#userPw");
		} else if (this.userPwConfirmError === true) {
			return $("#userPwConfirm");
		} else if (this.userNameError === true) {
			return $("#userName");
		} else if (this.phoneError === true) {
			return $("#phone");
		} else if (this.emailError === true) {
			return $("#email");
		} else if (this.addrError === true) {
			return $("#userId");
		} else {
			return "pass";
		}
	},
}

index.init();