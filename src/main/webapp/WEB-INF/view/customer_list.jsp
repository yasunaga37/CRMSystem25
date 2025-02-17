<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="layout.jsp">
	<c:param name="content">
		<div class="container container-m">
			<h3>顧客一覧</h3>
			<c:choose>
				<c:when test="${fn:length(customer_list) != 0 }">
					<div class="table_box">
						<table class="table table-striped table-light">
							<thead>
								<tr class="table-info">
									<th scope="col" class="sticky">顧客ID</th>
									<th scope="col" class="sticky">顧客名</th>
									<th scope="col" class="sticky">地域</th>
									<th scope="col" class="sticky">顧客担当者</th>
									<th scope="col" class="sticky">営業担当者</th>
									<th scope="col" class="sticky"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="cstm" items="${ customer_list }">
									<c:if test="${ cstm.delete_flg == 0 }">
										<tr>
											<td scope="row"><c:out value="${ cstm.customer_id }" /></td>
											<td><c:out value="${ cstm.customer_name }" /></td>
											<td><c:out value="${ cstm.area_name }" /></td>
											<td><c:out value="${ cstm.contact_person_name }" /></td>
											<td><c:out value="${ cstm.user_name }" /></td>
											<td><a href="customer_detail?customer_id=${ cstm.customer_id }">詳細</a></td>
										</tr>
									</c:if>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:when>
				<c:otherwise>
					<h4>顧客は登録されていません。</h4>
				</c:otherwise>

			</c:choose>
		</div>
	</c:param>
</c:import>