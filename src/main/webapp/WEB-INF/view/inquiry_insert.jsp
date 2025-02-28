<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:import url="layout.jsp">
	<c:param name="content">
		<div class="container container-m">

			<h6 class="text-center">新規お問合せ入力</h6>
			<%-- ${ fn:length(status_list) } --%>
			<form action="inquiry_insert" method="post" class="text-center">
				<div class="table_inquiry d-flex justify-content-center">
					<table class="w-75 table table-striped table-bordered border-primary">
						<tbody>
							<tr>
								<th scope="row" class="table-info text-wrap">問合せID</th>
								<td><c:out value="${max_id }" /></td>
								<th class="table-info text-wrap">ステータス</th>
								<td><select name="status">
										<c:forEach var="status" items="${status_list }">
											<c:choose>
												<c:when test="${status.status_code == '00' }">
													<option value="${status.status_code }" selected="selected"><c:out value="${status.status_name }" /></option>
												</c:when>
												<c:otherwise>
													<option value="${status.status_code }"><c:out value="${status.status_name }" /></option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<th scope="row" class="table-info text-wrap">顧客名</th>
								<td><c:out value="${customer.customer_name }" /></td>
								<th scope="row" class="table-info text-wrap">問合せ日時</th>
								<td><input type="datetime-local" name="inquiry_datetime" id="cal"></td>
							</tr>
							<tr>
								<th class="table-info text-wrap">ご担当者様</th>
								<td><c:out value="${customer.contact_person_name }" /></td>
								<th class="table-info text-wrap">営業担当</th>
								<td><c:out value="${customer.user_name }" /></td>
							</tr>
							<tr>
								<th scope="row" class="table-info">お問合内容</th>
								<td colspan="3" class="text-start text-wrap"><textarea name="inquiry_contents" cols="70" rows="3" required="required"></textarea></td>
							</tr>
							<tr>
								<th scope="row" class="table-info">回答</th>
								<td colspan="3" class="text-start text-wrap"><textarea name="reply_contents" cols="70" rows="3" required="required"></textarea></td>
							</tr>
						</tbody>
					</table>
				</div>

				<input type="hidden" name="customer_id" value="${customer.customer_id }">
				<button type="submit" class="btn btn-primary btn-sm px-5" name="action" value="inquiry_insert_execute">登録</button>
				<button type="submit" class="btn btn-primary btn-sm px-5" name="action" value="goto_customer_detail">顧客詳細画面へ戻る</button>
			</form>

		</div>
	</c:param>
</c:import>
<script src="js/script1.js"></script>