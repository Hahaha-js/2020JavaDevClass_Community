<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<form action="/board/write" method="post">
		<input type="hidden" name="category" value="${param.category}">
		<input type="hidden" name="boardPk" value="0">
		<div><input type="text" name="title" placeholder="제목" required></div>
		<div><textarea name="ctnt" placeholder="내용" required></textarea></div>
		<div><input type="submit" value="등록"></div>
	</form>
</div>