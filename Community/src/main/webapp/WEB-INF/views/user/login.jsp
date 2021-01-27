<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<form action="/user/login" method="post">
		<div><input type="text" name="userId" placeholder="ID"></div>
		<div><input type="password" name="userPw" placeholder="PASSWORD"></div>
		<div><input type="submit" value="Login"></div>
	</form>	
	<a href="/user/join">Join</a>
</div>