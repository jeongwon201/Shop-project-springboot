let index = {

	userIdError: true,
	userPwError: true,
	userPwConfirmError: true,
	userNameError: true,
	phoneError: true,
	emailError: true,
	addrError: true,

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


		$("#addrDetail").on("keyup", () => {
			this.valid(7);
		});

		$("#zipCode").on("click", () => {
			this.addrSearch();
		});
		
	},

	save: function() {

		let data = $("#setup").serialize();

		$.ajax({
			type: "POST",
			url: "/user/setup",
			data: data,
		}).done(function(resp) {
			alert(resp);
			if (resp.status == 201) {
				alert("관리자 등록이 완료되었습니다.");
				location.href = "/";
			}
		}).fail(function(error) {
			if (error.status == 400) {
				alert("입력하신 정보를 다시 확인하세요.")
			} else if (error.status == 403) {
				alert("관리자를 생성할 수 없습니다.")
			}
		});
	},

	valid: function(param) {

		const zipCode = $("#zipCode")
		const addrSt = $("#addrSt")
		const addrDetail = $("#addrDetail")

		switch (param) {

			case 7: {
				const error = $("#addr_error");
				
				if (zipCode.val() === "") {
					error.html("우편 번호를 입력하세요.");
					addrError = true;
				} else if(addrSt.val() === "") {
					error.html("도로명 주소를 입력하세요.");
					addrError = true;
				} else if (addrDetail.val() === "") {
					error.html("상세 주소를 입력해주세요.");
					addrError = true;
				} else {
					error.html("");
					addrError = false;
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

	addrSearch: function() {
		new daum.Postcode({
			oncomplete: function(data) {
				let fullRoadAddr = data.roadAddress;
				let extraRoadAddr = '';

				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraRoadAddr += data.bname;
				}
				
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				
				if (extraRoadAddr !== '') {
					extraRoadAddr = ' (' + extraRoadAddr + ')';
				}
				
				if (fullRoadAddr !== '') {
					fullRoadAddr += extraRoadAddr;
				}

				$("#zipCode").val(data.zonecode);
				$("#addrSt").val(fullRoadAddr);
				
				index.valid(7);
			}
		}).open();
		
	}
}

index.init();