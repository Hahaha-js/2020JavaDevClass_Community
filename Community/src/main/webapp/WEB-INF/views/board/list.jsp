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
	글갯수 : 
	<select id="selRowCnt">
		<option value="5">5개</option>
		<option value="10">10개</option>
		<option value="15">15개</option>
	</select>
</div>
<div id="listContent" data-category="${param.category}">		
</div>
<div id="pagingContent">	
</div>



<script src="/res/js/board/list.js"></script>











