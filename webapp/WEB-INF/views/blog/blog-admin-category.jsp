<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style type="text/css">
.ui-dialog .ui-dialog-buttonpane .ui-dialog-buttonset {
	float: none;
	text-align: center;
}

.ui-dialog .ui-dialog-buttonpane button {
	margin-left: 10px;
	margin-right:auto;
}

#dialog-message p {
	padding: 20px 0;
	font-weight : bold;
	font-size : 1.0em;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script>

<script>


//jqeury plugin
(function($){
	
	$.fn.hello = function(){
		console.log($(this).attr("id")+"---------> hello!!!");
	}
	
})(jQuery);

var isEnd = false;	
var tableliTemplate = new EJS( { url:"${pageContext.request.contextPath }/assets/js/template/table-li.ejs" } );
var tablelistTemplate = new EJS( { url:"${pageContext.request.contextPath }/assets/js/template/table-list.ejs" } );
var messageBox = function(title, message, callback) {
	$("#dialog-message").attr("title", title);
	$("#dialog-message p").text(message);
	$("#dialog-message").dialog({
		modal : true,
		buttons : {
			"확인" : function() {
				$(this).dialog("close");
			}
		},
		close : callback || function() { //||앞의 명제가 참이면 function을 실행하지 않고 거짓이면 function실행

		}
	});
}

var render = function(vo) {
/*	var html = "<tr>" + 
	"<td>" + "1" + "</td>" +
	"<td>" + vo.title +"</td>" +
	"<td>" + vo.postnum +"</td>" +
	"<td>" + vo.description +"</td>" +
	"<td> <a id='delete-category' href='#delete-category' data-no='"+ vo.no +"'>"+
	"<img src='${pageContext.request.contextPath}/assets/images/delete.jpg'>"+
		"</a>" +
	"</td>" +
	"</tr>";*/
	
	console.log("vo!!!!"+vo.title+vo.description+vo.no);
	var html = tableliTemplate.render(vo);
	$(".admin-cat tbody").append(html);
}

var fetchList = function() {

	$.ajax({
		url : "${pageContext.request.contextPath }/${authUser.id }/admin/category/api",
		type : "get",
		dataType : "json",
		data : "",
		success : function(response) {
			if (response.result === "fail") {
				console.error(response.message);
				return;
			}

		/*	$.each(response.data, function(index, vo) {
				render(vo);
			});*/
		
			var html = tablelistTemplate.render(response);
			$(".admin-cat tbody").append(html);
			$(".admin-cat tbody").hello();

		},
		error : function(jqXHR, status, e) {
			console.error(status + " : " + e);
		}
	});
}

$(function(){	
	
	fetchList();
	console.log("fetch 실행");
	//////////////////////////adddddddddddd
	
	$("#add-form").submit(function(event){
		event.preventDefault();
		
		var vo={};
		
		vo.title = $("#title").val();
		if(vo.title === ""){
			messageBox("카테고리 관리", "카테고리 이름을 입력해주세요.",
					function() {
						$("#title").focus();
					});
			$("#title").focus();
			return;
		}
		
		vo.description = $("#description").val();
		
		if(vo.description === ""){
			messageBox("카테고리 관리", "카테고리 설명을 입력해주세요.",
					function() {
						$("#description").focus();
					});
			$("#description").focus();
			return;
		}
		
		console.log(vo.title+":"+vo.description);
		
		console.log("validation.ok");

		$.ajax({
				url : "${pageContext.request.contextPath }/${authUser.id }/admin/category/api" ,
				type : "post",
				dataType : "json",
				data : JSON.stringify( vo ),
				contentType: 'application/json; charset=utf-8',
			success : function(response) {
					if (response.result === "fail") {
						console.error(response.message);
						return;
					}
					
					console.log( response.data );
					response.data.contextPath = "${pageContext.request.contextPath }";
					render(response.data); 
					console.log("response.data : "+response.data);
					$("#add-form")[0].reset();
			},
			error : function(jqXHR, status, e) {
					console.error(status + " : " + e);
				}
			});
	});
	
	
	/////삭제
	$(document).on("click", ".admin-cat a", function(event) {
		event.preventDefault();
		var no = $(this).data("no");
		console.log(no);
		
		$.ajax({
			url : "${pageContext.request.contextPath }/${authUser.id }/admin/category/delete/api" ,
			type : "post",
			dataType : "json",
			data : "no=" + no,
		success : function(response) {
				if (response.result === "fail") {
					console.error(response.message);
					return;
				}
				console.log("deletedate:"+response.data);
				$(".admin-cat a[data-no='"+response.data+"']").parent().parent().remove();
		},
		error : function(jqXHR, status, e) {
				console.error(status + " : " + e);
			}
		});

	});
	
})


</script>

</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/blogheader.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/include/blogadminmenu.jsp">
					<c:param name="menu" value="admincategory" />
				</c:import>
				<table class="admin-cat">
					<tbody>
						<tr>
							<th>번호</th>
							<th>카테고리명</th>
							<th>포스트 수</th>
							<th>설명</th>
							<th>삭제</th>
						</tr>
					</tbody>
				</table>


				<h4 class="n-c">새로운 카테고리 추가</h4>
				<form method="post" id="add-form"
					action="">
					<table id="admin-cat-add">
						<tr>
							<td class="t">카테고리명</td>
							<td><input type="text" id="title" name="title" value=""></td>
						</tr>
						<tr>
							<td class="t">설명</td>
							<td><input type="text" id="description" name="description" value=""></td>
						</tr>
						<tr>
							<td class="s">&nbsp;</td>
							<td><input type="submit" id="btn-add" value="카테고리 추가"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div id="dialog-message" title="" style="display: none">
				<p></p>
		</div>
		<c:import url="/WEB-INF/views/include/blogfooter.jsp" />
	</div>
</body>
</html>