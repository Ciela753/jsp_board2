<%@page import="vo.Board"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
ArrayList<Board> arrayList = (ArrayList<Board>)request.getAttribute("boardList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap 5 Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
	<style>
		p a {
			color :white;
		}
		.gallery-div-size {
			width:137px;
		    height: 137px;
		    background-color: #C3AA9A;
		    border-radius: 10px;
		    display: inline-block;
		    margin: 40px 40px 40px 40px;
		}
	</style>
</head>
<body>

<div class="container-fluid p-5 bg-primary text-white text-center">
  <h1>My First Bootstrap Page</h1>
  
  <c:choose>
  	<c:when test="${empty member}">
		<form action="login">
			<button type="submit" class="btn btn-outline-warning">로그인</button>
		</form>
		<p><a href="join">회원가입</a><a href="#">ID/PW 찾기</a>
  	</c:when>
  	<c:otherwise>
  		<p>${member.name}님 환영합니다.</p>
  		<p><a href="join">정보수정</a> <a href="logout">로그아웃</a>
		  <p><a href="write">Regist new Post!</a></p> 
  	</c:otherwise>
  </c:choose>
</div>
  
<div class="container mt-5">
  <div class="row">
    <c:set var="endCount" value="${page.cri.amount-(page.cri.amount-1)%3 + 2}"/>
    <c:forEach begin="1" end="${endCount}" varStatus="stat">
    	<c:set var="board" value="${list[stat.index-1]}"/>
    	<c:if test="${stat.index % 3==1 }">
    	
    	</c:if>
    	<c:if test="${not empty board}">
    		<div class="gallery-div-size">
					<img class="gallery-img" src="${pageContext.request.contextPath}/display?filename=${board.attachs[0].path}/s_${board.attachs[0].uuid}" alt="images/concert-resize.jpg">
	    		<a href="detail?bno=${board.bno}">${board.title}</a>
    		</div>
    	</c:if>
    </c:forEach>
    <ul class="pagination justify-content-end">
				<li class="page-item ${page.prev ? '' : 'disabled'}">
					<a class="page-link" href="list?pageNum=${page.startPage-1}&amount=${page.cri.amount}">Previous</a>
				</li>
			  	
			  	<c:forEach begin="${page.startPage}" end="${page.endPage}" var="p">
				  	<li class="page-item ${p == page.cri.pageNum ? 'active' : ''}">
				  		<a class="page-link " href="list?pageNum=${p}&amount=${page.cri.amount}">${p}</a>
				  	</li>
			  	</c:forEach>
			  	
			  	<li class="page-item ${page.next ? '' : 'disabled'}">
			  		<a class="page-link" href="list?pageNum=${page.endPage+1}&amount=${page.cri.amount}">Next</a>
			  	</li>
	</ul>
  </div>
</div>

<div>
	<aside>
		
	</aside>
</div>

</body>
</html>
