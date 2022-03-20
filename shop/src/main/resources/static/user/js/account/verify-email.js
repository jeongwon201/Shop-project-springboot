let index = {
	
	init: function() {
		$("#btn-submit").on("click", () => {
			this.submit();
			this.timer();
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
		}).fail(function(error) {
			if(error.status == "TOO_MANY_REQUESTS") {
				alert(error.message);
			} else {
				alert("이메일 전송에 실패하였습니다.")
			}
		});
	},
	
	timer: function() {
		let btnSubmit = $("#btn-submit");
		let count = 10;
		
		btnSubmit.attr("disabled", true);
		
		let interval =  setInterval(() => {
			btnSubmit.html(count);
			
			if(count < 0) {
				clearInterval(interval);
				btnSubmit.html("인증 링크 보내기");
				btnSubmit.attr("disabled", false);	
			}
			
			count--;
			
		}, 1000);
		
	}
}

index.init();