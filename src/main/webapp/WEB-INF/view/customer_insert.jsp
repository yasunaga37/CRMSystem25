<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="layout.jsp">
	<c:param name="content">
		<div class="container container-m">
			<h3>新規顧客登録</h3>

			<form action="customer_insert" method="post" class="text-center">
				<table class="table table-striped">
					<tbody>
						<tr>
							<th scope="row">顧客ID</th>
							<td>${customer_id }</td>
							<th>地区</th>
							<td class="text-start">
								<select name="area">
									<c:forEach var="area" items="${area_list }">
										<option value="${area.area_code}"><c:out value="${ area.area_name}" /></option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th scope="row">顧客名</th>
							<td class="text-start"><input type="text" name="customer_name" size="40" required="required"></td>
							<th scope="row">カナ</th>
							<td class="text-start"><input type="text" name="customer_name_kana" size="40"></td>
						</tr>
						<tr>
							<th scope="row">住所</th>
							<td colspan="3" class="text-start">〒<input type="text" name="postal_code" size="10"> 
																				  <input type="text" name="adress" size="80"></td>
						</tr>
						<tr>
							<th scope="row">担当者様</th>
							<td class="text-start"><input type="text" name="contact_person_lname" size="8" placeholder="姓" required="required"> 
															 <input type="text" name="contact_person_fname" size="8" placeholder="名" required="required">
							<th scope="row">カナ</th>
							<td class="text-start"><input type="text" name="contact_person_lname_kana" size="20" placeholder="姓" required="required"> 
															 <input type="text" name="contact_person_fname_kana" size="20" placeholder="姓" required="required">
						</tr>
						<tr>
							<th scope="row">TEL</th>
							<td colspan="3" class="text-start"><input type="text" name="contact_person_tel"  required="required"></td>
						</tr>
						<tr>
							<th scope="row">営業担当</th>
							<td colspan="3" class="text-start">
								<select name="user">
									<c:forEach var="user" items="${user_list }">
										<option value="${user.user_id }">${user.user_name }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="customer_id" value="${customer_id }">
				<button type="submit" class="btn btn-primary btn-sm px-5" name="action" value="execute_insert" onclick="return confirm('この内容で登録してもよろしいですか？')">登録</button>
			</form>

		</div>
	</c:param>
</c:import>