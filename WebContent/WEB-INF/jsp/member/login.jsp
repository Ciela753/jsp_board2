<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
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
<div class="container-fluid p-5 bg-primary text-white text-center">
  <h1>My First Bootstrap Page</h1>
  <p><a href="board/list">Back to List</a></p> 
</div>
<div id="divwidth">
	<form method="post" >
	  <div class="mb-3 mt-3">
	    <label for="email" class="form-label">ID:</label>
	    <input type="text" class="form-control" id="id" placeholder="Enter Id" name="id">
	  </div>
	  <div class="mb-3 mt-3">
	    <label for="email" class="form-label">Password:</label>
	    <input type="password" class="form-control" id="password" placeholder="Enter Password" name="pwd">
	  </div>
	  <button class="btn btn-primary" id="btnJoin">로그인</button>
	  <button type="button" onclick="location.href='board/list'" class="btn btn-primary">List</button>
	</form>
</div>
</body>
</html>