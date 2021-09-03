<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<h2>로그인</h2>
	<form action=""  method="post">
		<div class="form-group" >
			<label for="email">Username:</label> <input type="username" name="username" class="form-control" id="username" placeholder="Enter username" name="username">
		</div>
		<div class="form-group">
			<label for="pwd">Password:</label> <input type="password" name="password" class="form-control" id="password" placeholder="Enter password" name="password">
		</div>
		<div class="form-group form-check">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox" name="remember"> Remember me
			</label>
		</div>
		<button id="btn-login" class="btn btn-primary">로그인</button>
	</form>
	
</div>

<%@ include file="../layout/footer.jsp"%>