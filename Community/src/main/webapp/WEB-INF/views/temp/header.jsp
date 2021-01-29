<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<header>	
	<c:if test="${sessionScope.loginUser !=  null}">
		${sessionScope.loginUser.nm}님 환영합니다
	</c:if>
	<nav>
		<ul class="menuItems">
			<li>
				<c:choose>
					<c:when test="${sessionScope.loginUser ==  null}">
						<a href="/user/login">로그인</a>
					</c:when>
					<c:otherwise>
						<a href="/user/logout">로그아웃</a>
					</c:otherwise>
				</c:choose>			
			</li>
		</ul>	
	</nav>	
</header>