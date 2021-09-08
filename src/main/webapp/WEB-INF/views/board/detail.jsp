<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<h2>상세보기</h2>
<div class="container">
	<button id="btn" onclick="history.back()" class="btn btn-primary">돌아가기</button>
	<c:if test="${board.user.id == principal.user.id}">
		<button id="btn-update" class="btn btn-primary">수정</button>
		<button id="btn-delete" class="btn btn-primary">삭제</button>
	</c:if>
	<form>
		작성자: <span>${board.user.username }</span></i> 글번호: <span id="id">${board.id }</span>
		<div>
			<h1>${board.title}</h1>
		</div>
		<div>
			<h3>${board.content }</h3>
		</div>
	</form>
</div>

<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>