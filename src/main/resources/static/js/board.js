let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
		$("#btn-replySave").on("click", () => {
			this.replySave();
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
			success: function(resp) {
				alert("글쓰기가 완료되었습니다.");
				location.href = "/";
			},
			error: function(error) {
				alert(JSON.stringify(error));
			}
		});
	},
	deleteById: function() {
		let id = $("#id").text();
		$.ajax({
			url: '/api/board/' + id,
			type: 'DELETE',
			dataType: 'json',
			success: function(resp) {
				alert("삭제 완료되었습니다.");
				location.href = "/";
			},
			error: function(error) {
				alert(JSON.stringify(error));
			}
		});
	},
	update: function() {
		let id = $("#id").val();
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		}
		$.ajax({
			url: '/api/board/'+id,
			type: 'PUT',
			data: JSON.stringify(data),
			contentType: "application/json; charset=UTF-8",
			dataType: 'json',
			success: function(resp) {
				alert("글수정이 완료되었습니다.");
				location.href = "/";
			},
			error: function(error) {
				alert(JSON.stringify(error));
			}
		});
	},
	replySave: function(){
		let data = {	
			content: $("#content").val(),
		}
		let boardId= $("#boardId").val();
		 
		$.ajax({
			url: `/api/board/${boardId}/reply`,
			type: 'POST',
			data: JSON.stringify(data),
			contentType: "application/json; charset=UTF-8",
			dataType: 'json',
			success: function(resp) {
				alert("댓글쓰기가 완료되었습니다.");
				location.href = `/board/${boardId}`;
			},
			error: function(error) {
				alert(JSON.stringify(error));
			}
		});
	},
	replyDelete: function(boardId, replyId){
		$.ajax({
			url: `/api/board/${boardId}/reply/${replyId}`,
			type: 'DELETE',
			contentType: "application/json; charset=UTF-8",
			dataType: 'json',
			success: function(resp) {
				alert("글삭제가 완료되었습니다.");
				location.href = `/board/${boardId}`;
			},
			error: function(error) {
				alert(JSON.stringify(error));
			}
		});
	}
}
index.init();