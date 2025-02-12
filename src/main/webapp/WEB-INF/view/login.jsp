<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="layout.jsp">
	<c:param name="content">

		<div class="container container-m">
			<c:if test="${login_error != null }">
				<div class="alert alert-danger text-center" role="alert"><c:out value="${login_error }"/></div>
			</c:if>
			<c:if test="${logout != null}">
				<div class="alert alert-primary text-center" role="alert"><c:out value="${logout}"/></div>
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

	</c:param>
</c:import>