<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="layout.jsp">
	<c:param name="content">

		<div class="container container-m">
			<form action="customer_edit" method="post" class="text-center">
				<table class="table table-striped">
					<tbody>
						<tr>
							<th scope="row">顧客ID</th>
							<td>${customer.customer_id }</td>
							<th>地区</th>
							<td class="text-start">
								<select name="area">
									<c:forEach var="area" items="${area_list }">
										<c:choose>
											<c:when test="${ area.area_name==customer.area_name }">
												<option value="${area.area_code}" selected="selected"><c:out value="${ area.area_name}"/></option>
											</c:when>
											<c:otherwise>
												<option value="${area.area_code}"><c:out value="${ area.area_name}"/></option>
											</c:otherwise>									
										</c:choose>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th scope="row">顧客名</th>
							<td colspan="3" class="text-start"><input type="text" name="customer_name" value="${customer.customer_name }" size="70"></td>
						</tr>
						<tr>
							<th scope="row">カナ</th>
							<td colspan="3" class="text-start"><input type="text" name="customer_name_kana" value="${customer.customer_name_kana }" size="70"></td>
						</tr>
						<tr>
							<th scope="row">住所</th>
							<td colspan="1" class="text-start">〒<input type="text" name="postal_code" value="${customer.postal_code }"></td>
							<td colspan="2" class="text-start"><input type="text" name="adress" value="${customer.adress}" size="70"></td>
						</tr>
						<tr>
							<th scope="row">担当者様</th>
							<td colspan="3" class="text-start"><input type="text" name="contact_person_name" value="${customer.contact_person_name}" size="70"></td>
						</tr>
						<tr>
							<th scope="row">カナ</th>
							<td colspan="3" class="text-start"><input type="text" name="contact_person_name_kana" value="${customer.contact_person_name_kana}" size="70"></td>
						</tr>
						<tr>
							<th scope="row">TEL</th>
							<td colspan="3" class="text-start"><input type="text" name="contact_person_tel " value="${customer.contact_person_tel }"></td>
						</tr>
						<tr>
							<th scope="row">営業担当</th>
							<td colspan="3" class="text-start">
								<select name="user">
									<c:forEach var="user" items="${user_list }">
										<c:choose>
											<c:when test="${user.user_name ==customer.user_name }">
												<option value="${user.user_id }" selected="selected">${user.user_name }</option>
											</c:when>
											<c:otherwise>
												<option value="${user.user_id }">${user.user_name }</option>
											</c:otherwise>
										</c:choose>								
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th scope="row">更新日時</th>
							<td colspan="3" class="text-start"><fmt:formatDate value="${customer.update_datetime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
						</tr>
					</tbody>
				</table>
				<button type="submit" class="btn btn-primary btn-sm px-5" name="action" value="execute_edit">編集</button>
			</form>
		</div>

	</c:param>
</c:import>