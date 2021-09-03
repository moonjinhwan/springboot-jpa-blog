let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		
		$("#btn-login").on("click", () => {
			this.login();
		});
	},

	save: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}

		$.ajax({
			url: '/api/user',
			type: 'POST',
			data: JSON.stringify(data),
			contentType: "application/json; charset=UTF-8",  
			dataType: 'json',
			success: function(resp){
				//alert("회원가입 완료");
				//alert(resp);
				location.href="/";
			},
			error: function(error){
				alert(JSON.stringify(error));
			}
		});
	},
	
	login: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
		}

		$.ajax({
			url: '/api/user/login',
			type: 'POST',
			data: JSON.stringify(data),
			contentType: "application/json; charset=UTF-8",  
			dataType: 'json',
			success: function(resp){
				//alert("로그인");
				//alert(resp);
				location.href="/";
			},
			error: function(error){
				alert(JSON.stringify(error));
			}
		});
	}
}

index.init();