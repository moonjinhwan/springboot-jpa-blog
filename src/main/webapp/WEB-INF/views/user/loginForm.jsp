<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<h2>로그인</h2>
	<form action="/auth/loginProc"  method="post">
		<div class="form-group" >
			<label for="email">Username:</label> <input type="username" name="username" class="form-control" id="username" placeholder="Enter username" name="username">
		</div>
		<div class="form-group">
			<label for="pwd">Password:</label> <input type="password" name="password" class="form-control" id="password" placeholder="Enter password" name="password">
		</div>
		<button id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=a5bba68f4cc2a6ad3516d39e67df2cae&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img src="/image/kakao_login_button.png" ></a>
	</form>
	
</div>

<%@ include file="../layout/footer.jsp"%>