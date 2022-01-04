<%@page import="vo.Board"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
		  <p><a href="board/regist">Regist new Post!</a></p> 
  	</c:otherwise>
  </c:choose>
</div>
  
<div class="container mt-5">
  <div class="row">
    <table class="table table-striped">
    <thead>
      <tr>
        <th>Bno</th>
        <th>Title</th>
        <th>Writer</th>
        <th>Regdate</th>
      </tr>
    </thead>
    <tbody>
    
    <%for(int i=0; i<arrayList.size(); i++) {%>
      <tr>
        <td><%=arrayList.get(i).getBno()%></td>
        <td><a href="get?bno=<%=arrayList.get(i).getBno()%>"><%=arrayList.get(i).getTitle()%></a></td>
        <td><%=arrayList.get(i).getWriter()%></td>
        <td><%=arrayList.get(i).getRegDate()%></td>
      </tr>
     <%} %>
    </tbody>
  </table>
  </div>
</div>

<div>
	<aside>
		
	</aside>
</div>

</body>
</html>
