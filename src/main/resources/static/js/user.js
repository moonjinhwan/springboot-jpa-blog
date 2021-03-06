let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		
		$("#btn-update").on("click", () => {
			this.update();
		});
	},

	save: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}

		$.ajax({
			url: '/auth/joinProc',
			type: 'POST',
			data: JSON.stringify(data),
			contentType: "application/json; charset=UTF-8",
			dataType: 'json',
			success: function(resp) {
				alert("회원가입 완료");
				location.href = "/";
			},
			error: function(error) {
				alert(JSON.stringify(error));
			}
		});
	},
	update: function() {
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}

		$.ajax({
			url: '/api/user',
			type: 'PUT',
			data: JSON.stringify(data),
			contentType: "application/json; charset=UTF-8",
			dataType: 'json',
			success: function(resp) {
				alert("회원정보 수정 완료");
				location.href = "/";
			},
			error: function(error) {
				alert(JSON.stringify(error));
			}
		});
	}
}

index.init();