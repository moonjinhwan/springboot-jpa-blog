<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<h2>글 수정</h2>
<div class="container">
	<form>
		<input type="hidden" id="id" value="${board.id}">
		<div class="form-group">
			<label for="title">Title:</label> <input type="text" class="form-control" id="title" value="${board.title }">
		</div>
		<div class="form-group">
			<label for="content">Content:</label>
			<textarea class="summernote" rows="5" id=content>${board.content }</textarea>
		</div>
	</form>
	<button id="btn-update" class="btn btn-primary">수정완료</button>
</div>
<script>
	$('.summernote').summernote({
		tabsize : 2,
		height : 300
	});
</script>
<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>