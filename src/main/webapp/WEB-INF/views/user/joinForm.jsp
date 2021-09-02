<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<h2>Stacked form</h2>
	<form action="/action_page.php">
		<div class="form-group">
			<label for="username">username:</label> <input type="username" class="form-control" id="username" placeholder="Enter username" name="username">
		</div>
		<div class="form-group">
			<label for="password">password:</label> <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
		</div>
		<div class="form-group">
			<label for="email">Email:</label> <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
		</div>
		<div class="form-group">
			<label for="role">role:</label> <input type="text" class="form-control" id="role" placeholder="Enter role" name="role">
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</div>
<%@ include file="../layout/footer.jsp"%>
