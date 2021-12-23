<%@page import="vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  		margin: 50px 350px;
  	}
  	button {
  		margin: 20px;
  	}
  	a {
  		color:white;
  	}  	
  </style>
</head>
<body>
<%
	Board board = (Board)request.getAttribute("boardGet");
	System.out.println(board);
%>
<div class="container-fluid p-5 bg-primary text-white text-center">
  <h1>My First Bootstrap Page</h1>
  <p>Resize this responsive page to see the effect!</p> 
</div>
<div id="divwidth">
	  <div class="mb-3 mt-3">
	    <label for="email" class="form-label">Title:</label>
	    <p><%=board.getTitle() %></p>
	  </div>
	  <div class="mb-3">
	    <label for="pwd" class="form-label">writer:</label>
	    <p><%=board.getWriter() %></p>
	  </div>
	  <div>
	  	<label for="comment">Contents:</label>
	    <p><%=board.getContent() %></p>
	  </div>
	  <button type="button" onClick="location.href='modify.jsp=<%=board.getBno()%>'" class="btn btn-primary">Modify</button>
	  <button type="button" onclick="location.href='list.jsp'" class="btn btn-primary">List</button>
</div>
</body>
</html>