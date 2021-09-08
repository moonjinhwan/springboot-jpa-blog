<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>
<div class="container">
	<c:forEach items="${boards.content}" var="board">
		<div class="card m-1">
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4>
				<a href="/board/${board.id}" class="btn btn-primary stretched-link">상세보기</a>
			</div>
		</div>
	</c:forEach>
	<ul class="pagination">
		<c:choose>
			<c:when test="boards.first">
				<li class="page-item"><a class="page-link disabled" href="?page=${board.number-1}">Previous</a></li>	
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${board.number-1}">Previous</a></li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="boards.last">
				<li class="page-item"><a class="page-link disabled" href="?page=${board.number+1}">Next</a></li>	
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${board.number+1}">Next</a></li>
			</c:otherwise>
		</c:choose>		
	</ul>
</div>
<%@ include file="layout/footer.jsp"%>