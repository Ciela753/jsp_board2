<%@page import="vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap 5 Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv='X-UA-Compatible' content='IE=edge'>
<title>Page Title</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js" ></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
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
<form method="post">
<div id="divwidth" >
	  <div class="mb-3 mt-3">
	    <label class="form-label">Title:</label>
	    <p>${boardGet.title}</p>
	  </div>
	  <div class="mb-3">
	    <label class="form-label">writer:</label>
	    <p>${boardGet.writer}</p>
	  </div>
	  <div>
	  	<label for="comment">Contents:</label>
	    <p>${boardGet.content}</p>
	  </div>
	  <button type="button" onClick="location.href='modify?bno=${boardGet.bno}'" class="btn btn-primary">Modify</button>
	  <button type="submit" class="btn btn-primary">delete</button>
	  <button type="button" onclick="location.href='list'" class="btn btn-primary">List</button>
</div>
</form>
<c:if test="${not empty member}">
	<div class="col-10 mx-auto" id="divwidth" >
		<div class="form-group clearfix">
			<p>${member.name}</p>
			<form id="frmReplyWrite" >
				<input type="text" class="form-control" placeholder="댓글 제목을 입력하세요" name="title" id="title">
				<textarea class="form-control my-1" placeholder="댓글을 입력하세요" name="content" id="content"></textarea>
				<input type="hidden" name="bno" value="${boardGet.bno}">
				<input type="hidden" name="id" value="${member.id}">
				<button class="btn btn-primaty float-right" id="btnReplyWrite">등록</button>
			</form>
		</div>	
	</div>	
</c:if>
<div class="container col-10 mx-auto reply-wrapper">
</div>
<!-- The Modal -->
<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Modal Heading</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        Modal body..
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" id="btnRm">삭제</button>
      </div>

    </div>
  </div>
</div>
<script>
var cp = "${pageContext.request.contextPath}";
var bno = '${param.bno}';
$(function() {
	showList();
	function showList() {
		var url = cp + "/reply/list?bno=" + bno;
		console.log(url);
		
		$.getJSON(url).done(function(data) {
			console.log(data);
			
			var str = "";
			for(var i in data) {
				str += '<div class="card my-3 border-secondary" data-rno="' + data[i].rno + '">'
				str += '	<div class="card-header bg-dark text-light">' + data[i].title + '</div>'
				str += '	<div class="card-body">' + data[i].content + '</div>'
				str += '</div>'
			}
			$(".reply-wrapper").html(str);
		})
	}
	
	// 이벤트 위임 댓글 상세 이벤트
	$(".reply-wrapper").on("click", ".card", function() {
		var url = cp + "/reply?rno=" + $(this).data("rno");
		$.getJSON(url).done(function(data) {
			console.log(data);
			console.log(data.title);
			console.log(data.content);
			$("#myModal")
			.data("rno", data.rno)
				.find(".modal-title").text(data.title)
			.end()
				.find(".modal-body").text(data.content)
			.end()
				.modal("show");
				
			//$("#myModal").modal("show");
		});
	})
	
	$("#btnRm").click(function() {
		//alert($(this).closest(".modal").data("rno"));
		var rno = $(this).closest(".modal").data("rno");
		var url = cp + "/reply?rno=" + rno;
		
		// ajax
		$.ajax(url, {
			method:"delete",
			success : function(data) {
				// 성공적으로 종료
				showList();
				$("#myModal").modal("hide");
			}
		});
	});
	
	$("#title, #content").keyup(function() {
		var titleLen = $("#title").val().trim().length;
		var contentLen = $("#content").val().trim().length;
		if(titleLen && contentLen) {
			$("#btnReplyWrite").removeClass("disabled");
		}
		else {
			$("#btnReplyWrite").addClass("disabled");
		}
	});
	
	$("#frmReplyWrite").submit(function() {
		event.preventDefault();
		if($("#btnReplyWrite").is(".disabled")) return;
		
		var reply = {};
		reply.title = $(this.title).val();
		reply.content = $(this.content).val();
		reply.id = $(this.id).val();
		reply.bno = $(this.bno).val();
		
		var data = JSON.stringify(reply);
		
		var frm = this;
		var url = cp + "/reply"
		$.ajax(url, {
			method:"post",
			data : {"jsonData" : data},
			success : function(data) {
				showList();
				frm.reset();
				$("#btnReplyWrite").addClass("disabled");
			}
		})
	});
});
</script>
</body>
</html>