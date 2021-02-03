<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<h1>카테고리 이름</h1>
<div>
	<c:if test="${sessionScope.loginUser != null}">
		<a href="/board/write?category=${param.category}">
			<button>글쓰기</button>
		</a>
	</c:if>
</div>
<div>
	<c:choose>
		<c:when test="${fn:length(requestScope.list) == 0}">
			<div>글이 없습니다.</div>
		</c:when>
		<c:otherwise>
			<table class="basic-table">
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>조회수</td>
					<td>작성일</td>
					<td>작성자</td>
				</tr>
				<c:forEach items="${requestScope.list}" var="item">
					<tr class="record" onclick="goToDetail(${item.boardPk})">				
						<td>${item.seq}</td>
						<td><c:out value="${item.title}"/></td>
						<td>${item.hits}</td>
						<td>${item.regDt}</td>
						<td><c:out value="${item.writerNm}"/></td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	
</div>
<div>
	페이징
</div>
<script src="/res/js/board/list.js"></script>












