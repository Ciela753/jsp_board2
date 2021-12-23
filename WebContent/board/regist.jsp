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
  <p><a href="list.jsp">Back to List</a></p> 
</div>
<div id="divwidth">
	<form action="register" method="post" >
	  <div class="mb-3 mt-3">
	    <label for="email" class="form-label">Title:</label>
	    <input type="text" class="form-control" id="email" placeholder="Enter Title" name="title">
	  </div>
	  <div class="mb-3">
	    <label for="pwd" class="form-label">writer:</label>
	    <input type="text" class="form-control" id="pwd" placeholder="Enter Writer" name="writer">
	  </div>
	  <div>
	  	<label for="comment">Contents:</label>
		<textarea class="form-control" rows="5" id="comment" name="content"></textarea>
	  </div>
	  <button type="submit" class="btn btn-primary">Submit</button>
	  <button type="button" onclick="location.href='list.jsp'" class="btn btn-primary">List</button>
	</form>
</div>

</body>
</html>