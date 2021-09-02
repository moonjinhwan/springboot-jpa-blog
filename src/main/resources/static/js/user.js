let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
	},

	save: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}

		$.ajax({
			url: '/blog/api/user',
			type: 'POST',
			data: JSON.stringify(data),
			contentType: "application/json; charset=UTF-8",  
			dataType: 'json',
			success: function(resp){
				alert("회원가입 완료");
				alert(resp);
				location.href="/blog";
			},
			error: function(error){
				alert(JSON.stringify(error));
			}
		});
	}
}

index.init();