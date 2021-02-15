<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<div>
		<c:choose>
			<c:when test="${requestScope.data.profileImg == null}">
				<c:set var="src" value="profile.jpg"/>
			</c:when>		
			<c:otherwise>
				<c:set var="src" value="user/${requestScope.data.userPk}/${requestScope.data.profileImg}"/>
			</c:otherwise>
		</c:choose>
		<img src="/res/img/${src}" alt="프로필 이미지">
	</div>
	<div>
		프로필 이미지 업로드 : <input type="file" id="profileImg" accept="image/*">
		<input type="button" value="업로드" onclick="profileUpload()"> 
	</div>
	<div>아이디 : ${requestScope.data.userId}</div>
	<div>이름 : ${requestScope.data.nm}</div>
	<div>성별 : ${requestScope.data.gender == 0 ? '여' : '남'}</div>
	<div>가입일 : ${requestScope.data.regDt}</div>	
</div>
<script src="/res/js/user/profile.js"></script>