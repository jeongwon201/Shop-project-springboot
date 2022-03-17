let index = {
	
	init: function() {
		$("#btn-submit").on("click", () => {
			this.submit();
		});
	},

	submit: function() {
		
		let frm = $("#frm").serialize();
		
		$.ajax({
			type: "POST",
			url: "/account/verify-email",
			data: frm,
		}).done(function(res) {
			if(res.status == "CREATED") {
				alert(res.message);
			} else {
				alert("이메일 전송에 실패하였습니다.")
			}
		}).fail(function(res) {
			alert("이메일 전송에 실패하였습니다.")
		});
	}
}

index.init();