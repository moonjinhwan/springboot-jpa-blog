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
		<form>
			<input type="hidden" id="boardId" value="${board.id}">
			<div class="card-body">
				<textarea class="form-control" id="content" rows="1"></textarea>
			</div>
			<div class="card-footer">
				<button type="button" id="btn-replySave" class="btn btn-primary">등록</button>
			</div>
		</form>
	</div>

	<br />
	<div class="card">
		<div class="card-header">댓글 리스트</div>
		<ul id="comment--box" class="list-group">
			<c:forEach var="reply" items="${board.replys}">
				<li id="comment--1" class="list-group-item d-flex justify-content-between">
					<div>${reply.content}</div>
					<div class="d-flex">
						<div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
						<button class="badge">삭제</button>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>


</div>

<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>