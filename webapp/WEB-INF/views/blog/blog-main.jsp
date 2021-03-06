<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/blogheader.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postVo.title }</h4>
					<p>
						${postVo.content }
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${map.pList }" var="vo" varStatus="status">
					<li><a href="${pageContext.servletContext.contextPath }/${blogVo.blogid }?no=${vo.no }&ctitle=${vo.ctitle }">${vo.title }</a> <span>${vo.date }</span>	</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath }${blogVo.logo }">
			</div>
		</div>

		<div id="navigation">
		
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${map.cList }" var="vo" varStatus="status">
				<li><a href="${pageContext.servletContext.contextPath }/${blogVo.blogid }?ctitle=${vo.title }">${vo.title }</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<c:import url="/WEB-INF/views/include/blogfooter.jsp" />
	</div>
</body>
</html>