<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- <c:import url="layout.jsp">
	<c:param name="content"> --%>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CustomerManagement</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<!-- オリジナルCSSの読み込み -->
<link rel="stylesheet" type="text/css" href="css/components.css">
<link rel="stylesheet" type="text/css" href="css/table-fixed.css">
</head>

<div class="container container-m">

	<p class="text-center fs-4">顧客管理システム ログイン認証</p>

	<c:if test="${login_error != null }">
		<div class="alert alert-danger text-center" role="alert">
			<c:out value="${login_error }" />
		</div>
	</c:if>
	<c:if test="${logout != null}">
		<div class="alert alert-success text-center" role="alert">
			<c:out value="${logout}" />
		</div>
	</c:if>

	<form action="index.html" method="post">
		<div class="mb-3">
			<label for="exampleInputEmail1" class="form-label">UserID</label> <input type="text" name="user_id" class="form-control" id="user_id"
				aria-describedby="emailHelp">
		</div>
		<div class="mb-3">
			<label for="exampleInputPassword1" class="form-label">Password</label> <input type="password" name="password" class="form-control" id="password">
		</div>
		<button type="submit" class="btn btn-primary">ログイン</button>
	</form>
</div>

<%-- フッター --%>
<footer class="footer mt-4 py-3 bg-light">
	<div class="text-center">
		<span class="text-muted">By TakoPikaPika co., &copy; All Right Reserved.</span>&nbsp;&nbsp;&nbsp;&nbsp; <a href="">Privacy Policy</a> <a href="">Terms</a>
		<marquee>
			<img src="<c:url value='img/tako1.png' />">
		</marquee>
	</div>
</footer>
<%-- フッター --%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>


<%-- 	</c:param>
</c:import> --%>