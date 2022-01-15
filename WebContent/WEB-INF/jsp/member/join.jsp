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
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js" ></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
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
	  <div class="mb-3 mt-3">
	    <label for="email" class="form-label">Email:</label>
	    <input type="text" class="form-control" id="email" placeholder="Enter Email" name="email">
	  </div>
	  <div class="mb-3 mt-3">
	    <label for="email" class="form-label">Name:</label>
	    <input type="text" class="form-control" id="name" placeholder="Enter Name" name="name">
	  </div>
	  
	  <button class="btn btn-primary" id="btnJoin">가입하기</button>
	  <button type="button" onclick="location.href='board/list'" class="btn btn-primary">List</button>
	</form>
</div>
<script>
	$(function() {
		$("#btnJoin").click(function() {
			var id =$("#id").val();
			if(id) {
				$.ajax("idValid?id="+id, {
					success : function(data) {
						if(data/1){
							alert("가입 성공");
							location.href="login";
						}
						else {
							alert("이미 가입된 회원입니다.");
							$("#id").focus();
						}
					}
				
				})
			}
			 event.preventDefault();
		})
	});
</script>
</body>
</html>