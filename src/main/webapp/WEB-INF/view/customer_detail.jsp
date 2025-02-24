<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="layout.jsp">
	<c:param name="content">
		
		<div class="container container-m">
			<%-- 顧客情報更新の成功可否を通知するアラート --%>
			<c:if test="${update_failed != null }">
				<div class="alert alert-danger text-center" role="alert">
					<c:out value="${update_failed }" />
				</div>
			</c:if>
			<c:if test="${update_success != null}">
				<div class="alert alert-primary text-center" role="alert">
					<c:out value="${update_success}" />
				</div>
			</c:if>
			<%-- 顧客情報更新の成功可否を通知するアラート ここまで --%>

			<%-- 第1列目　顧客情報詳細 --%>
			<div class="row">
				<!-- 				<div class="col-md-6" style="background-color: #00FF00"> -->
				<div class="col-md-6">
					<h6 class="text-center">顧客情報</h6>
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
					<form action="customer_edit" method="post" class="text-center" style="display: inline-block">
						<input type="hidden" name="customer_id" value="${customer.customer_id }">
						<button type="submit" class="btn btn-primary btn-sm px-5" name="action" value="goto_edit">編集</button>
					</form>
					<form action="customer_delete" method="post" class="text-center" style="display: inline-block">
						<input type="hidden" name="customer_id" value="${customer.customer_id }">
						<button type="submit" class="btn btn-danger btn-sm px-5" name="action" value="goto_delete">削除</button>
					</form>
				</div>
				<%-- 第1列目　顧客情報詳細 ここまで --%>

				<%-- 第2列目　お問合せ履歴 --%>
				<div class="col-md-6">
					<h6 class="text-center">お問合せ履歴</h6>
					<table class="table table-striped">
						<tbody>
							<tr>
								<th scope="row">お問合せ日時</th>
								<th>内容</th>
								<th>ステータス</th>
								<th scope="row">更新日時</th>
							</tr>
							<tr>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<%-- 第2列目　お問合せ履歴 ここまで --%>
		</div>

	</c:param>
</c:import>