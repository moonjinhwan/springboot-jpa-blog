let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
	},

	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		}

		$.ajax({
			url: '/api/board',
			type: 'POST',
			data: JSON.stringify(data),
			contentType: "application/json; charset=UTF-8",  
			dataType: 'json',
			success: function(resp){
				alert("글쓰기가 완료되었습니다.");
				location.href="/";
			},
			error: function(error){
				alert(JSON.stringify(error));
			}
		});
	}
}

index.init();