<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="ja">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CustomerManagement</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<!-- オリジナルCSSの読み込み -->
<link rel="stylesheet" type="text/css" href="css/components.css">
<link rel="stylesheet" type="text/css" href="css/table-fixed.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
</head>

<body>

	<%-- ナビゲーションバー --%>
	<nav class="navbar navbar-expand-lg fixed-top" style="background-color: #e3f2fd;">
		<div class="container-fluid">
			<a class="navbar-brand" href="index.html?action=logout" onclick="return confirm('ログアウトしてもよろしいですか？')"> 
				<img src="img/crm.png" alt="logo">顧客管理システム
			</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active" aria-current="page" href="index.html?action=home">顧客一覧</a></li>
					<li class="nav-item"><a class="nav-link" href="customer_insert?action=goto_insert_page">顧客登録</a></li>
					<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Dropdown </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#">Action</a></li>
							<li><a class="dropdown-item" href="#">Another action</a></li>
							<li>
								<hr class="dropdown-divider">
							</li>
							<li><a class="dropdown-item" href="#">Something else here</a></li>
						</ul></li>
<!-- 					<li class="nav-item"><a class="nav-link disabled">Disabled</a></li> -->
				</ul>
				
				<c:if test="${loginUser != null}">
					<div class="navbar-nav me-auto mb-2 mb-lg-0 bg-info-subtle"><c:out value="${loginUser.user_name }"/></div>
				</c:if>
				
				
				<form class="d-flex" role="search">
					<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
			</div>
		</div>
	</nav>
	<%-- ナビゲーションバー --%>
	
	<%-- メインコンテンツ --%>
	<div id="content">${param.content}</div>
	<%-- メインコンテンツ --%>

	<%-- フッター --%>
	<footer class="footer mt-4 py-3 bg-light">
		<div class="text-center">			
			<span class="text-muted">By TakoPikaPika co., &copy; All Right Reserved.</span>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="">Privacy Policy</a> <a href="">Terms</a>
			<marquee><img src="<c:url value='img/tako1.png' />"></marquee>
		</div>
	</footer>
	<%-- フッター --%>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>

</html>