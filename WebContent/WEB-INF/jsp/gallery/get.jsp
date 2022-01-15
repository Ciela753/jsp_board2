<%@page import="vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap 5 Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
  	#divwidth {
  		margin: 50px 300px;
  	}
  	button {
  		margin: 10px;
  	}
  	a {
  		color:white;
  	}  	
  </style>
</head>
<body>
<div class="container-fluid p-5 bg-primary text-white text-center">
  <h1>My First Bootstrap Page</h1>
  <p>Resize this responsive page to see the effect!</p> 
</div>
<div class="container mt-5">
	<table class="detail-table">
		<tr>
			<td>${galleryGet.title}</td>
			<td>${galleryGet.regDate}</td>
		</tr>
		<tr>
			<td>
				<c:forEach items="${galleryGet.attachs }" var="attach">
					<p><a href="${pageContext.request.contextPath}/download?filename=${attach.path}/${attch.uuid}">${attach.origin}</a></p>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td colspan="2">${galleryGet.writer}</td>
		</tr>
		<tr>
			<td>${galleryGet.content}</td>
		</tr>
		<tr>
			<td colspan="2">
				<form method="post">
					<button type="button" onClick="location.href='modify?bno=${galleryGet.bno}'" class="btn btn-primary">Modify</button>
			  		<button type="submit" class="btn btn-primary">delete</button>
		  		    <button type="button" onclick="location.href='list'" class="btn btn-primary">List</button>
				</form>
			</td>
		</tr>
	</table>
</div>
</body>
</html>