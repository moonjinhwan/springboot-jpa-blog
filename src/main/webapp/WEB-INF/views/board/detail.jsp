<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<h2>상세보기</h2>
<div class="container">
	<button id="btn" onclick="history.back()" class="btn btn-primary">돌아가기</button>
	<c:if test="${board.user.id == principal.user.id}">
		<a id="btn-update-form" href="/board/${board.id}/updateForm" class="btn btn-primary">수정</a>
		<button id="btn-delete" class="btn btn-primary">삭제</button>
	</c:if>
	<form>
		작성자: <span>${board.user.username}</span></i> 글번호: <span id="id">${board.id}</span> <br />
		<div>
			<h1>${board.title}</h1>
		</div>
		<hr />
		<div>
			<h3>${board.content}</h3>
		</div>
	</form>
	<hr />
	<div class="card">
		<div class="card-body">
			<textarea class="form-control" rows="1"></textarea>
		</div>
		<div class="card-footer">
			<button type="button" class="btn btn-primary">등록</button>
		</div>
	</div>

	<br />
	<div class="card">
		<div class="card-header">댓글 리스트</div>
		<ul id="" class="list-group">
			<li class="list-group-item d-flex justify-content-between">
				<div>댓글 내용</div>
				<div class="d-flex">
					<div class="font-italic">작성자 : liam &nbsp;</div>
					<button class="badge">삭제</button>
				</div>
			</li>
		</ul>
	</div>


</div>

<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>