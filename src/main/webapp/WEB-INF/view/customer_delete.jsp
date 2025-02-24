<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="layout.jsp">
	<c:param name="content">

		<div class="container container-m">
		
			<c:choose>
				<c:when test="${delete_error != null }">
					<div class="alert alert-danger text-center" role="alert"><c:out value="${delete_error }"/></div>
				</c:when>
				<c:when test="${delete_execute != null }">
					<div class="alert alert-danger text-center" role="alert"><c:out value="${delete_execute }"/></div>
				</c:when>
				<c:otherwise>
					<h6 class="text-center">以下の顧客情報を削除します。</h6>
				</c:otherwise>
			</c:choose>
			
			<table class="table table-striped">
				<tbody>
					<tr>
						<th scope="row">顧客ID</th>
						<td>${customer.customer_id }</td>
						<th>地区</th>
						<td>${customer.area_name }</td>
					</tr>
					<tr>
						<th scope="row">顧客名</th>
						<td colspan="3" class="text-start">${customer.customer_name }（${customer.customer_name_kana }）</td>
					</tr>
					<tr>
						<th scope="row">住所</th>
						<td colspan="3" class="text-start">〒${customer.postal_code }<br />${customer.adress }</td>
					</tr>
					<tr>
						<th scope="row">担当者様</th>
						<td colspan="3" class="text-start">${customer.contact_person_name }（${customer.contact_person_name_kana }）</td>
					</tr>
					<tr>
						<th scope="row">TEL</th>
						<td colspan="3" class="text-start">${customer.contact_person_tel }</td>
					</tr>
					<tr>
						<th scope="row">営業担当</th>
						<td colspan="3" class="text-start">${customer.user_name }</td>
					</tr>
					<tr>
						<th scope="row">更新日時</th>
						<td colspan="3" class="text-start"><fmt:formatDate value="${customer.update_datetime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
					</tr>
				</tbody>
			</table>

			<c:choose>
				<c:when test="${delete_execute == null }">
					<form action="customer_delete" method="post" class="text-center" autocomplete="off">
						<input type="hidden" name="customer_id" value="${customer.customer_id }">
						ユーザーID：<input type="text" name="user_id">　
						パスワード：<input type="password" autocomplete="new-password" name="password"><br><br>
						<button type="submit" class="btn btn btn-danger btn-sm px-5" name="action" value="execute_delete" onclick="return confirm('削除してもよろしいですか？')">削除</button>
						<button type="submit" class="btn btn-primary btn-sm px-5" name="action" value="goto_detail">顧客情報画面へ戻る</button>
					</form>
				</c:when>
				<c:otherwise>
					<form action="customer_delete" method="post" class="text-center">
					<input type="hidden" name="customer_id" value="${customer.customer_id }">
						<button type="submit" class="btn btn-primary btn-sm px-5" name="action" value="goto_list">顧客一覧</button>
					</form>
				</c:otherwise>
			</c:choose>
			
		</div>
	</c:param>
</c:import>