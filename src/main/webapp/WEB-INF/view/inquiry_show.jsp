<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="layout.jsp">
	<c:param name="content">
		<div class="container container-m">

			
			<c:choose>
				<c:when test="${action == 'お問合せ詳細' }">
					<h6 class="text-center"><c:out value="${action }"/></h6>
				</c:when>
				<c:when test="${action == '以下のお問合せを削除します' }">
					<h6 class="text-center"><c:out value="${action }"/></h6>
				</c:when>
				<c:when test="${action == '以下のお問合せを削除しました' }">
					<h6 class="text-center"><c:out value="${action }"/></h6>
				</c:when>
				<c:when test="${delete_error != null }">
					<div class="alert alert-danger text-center" role="alert">
						<c:out value="${delete_error }" />
					</div>
				</c:when>
				<c:when test="${delete_execute != null }">
					<div class="alert alert-danger text-center" role="alert">
						<c:out value="${delete_execute }" />
					</div>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
			
			<div class="table_inquiry d-flex justify-content-center">
				<table class="w-75 table table-striped table-bordered border-primary">
					<tbody>
						<tr>
							<th scope="row" class="table-info text-wrap">問合せID</th>
							<td><c:out value="${inquiry.id }" /></td>							
							<th class="table-info text-wrap">ステータス</th>
							<c:choose>
								<c:when test="${inquiry.status_name == '対応完了'}">
									<td><c:out value="${inquiry.status_name}" /></td>
								</c:when>								
								<c:otherwise>
									<td class="text-danger"><c:out value="${inquiry.status_name}" /></td>
								</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<th scope="row" class="table-info text-wrap">顧客名</th>
							<td><c:out value="${inquiry.customer_name}" /></td>
							<th scope="row" class="table-info text-wrap">問合せ日時</th>
							<td class="text-start"><fmt:formatDate value="${inquiry.inquiry_datetime }" pattern="yyyy年MM月dd日(EE)　HH時mm分" /></td>
						</tr>
						<tr>
							<th class="table-info text-wrap">ご担当者様</th>
							<td><c:out value="${inquiry.contact_person_name}" /></td>
							<th class="table-info text-wrap">営業担当</th>
							<td><c:out value="${inquiry.user_name}" /></td>
						</tr>
						<tr>
							<th scope="row" class="table-info">お問合内容</th>
							<td colspan="3" class="text-start" style="white-space: pre-wrap;"><c:out value="${inquiry.inquiry_contents }" /></td>
						</tr>
						<tr>
							<th scope="row" class="table-info">回答</th>
							<td colspan="3" class="text-start" style="white-space: pre-wrap;"><c:out value="${inquiry.reply_contents }" /></td>
						</tr>
						<tr>
							<th scope="row" class="table-info text-wrap">更新日時</th>
							<td colspan="3" class="text-start"><fmt:formatDate value="${inquiry.update_datetime}" pattern="yyyy年MM月dd日(EE)　HH時mm分" /></td>
						</tr>
					</tbody>
				</table>
				</div>
				<div class="d-flex justify-content-center">
					
					<c:choose>
						<c:when test="${action == 'お問合せ詳細' }">
							<form action="inquiry_edit" method="post" class="text-center" style="display: inline-block">
								<input type="hidden" name="inquiry_id" value="${inquiry.id }">
								<button type="submit" class="btn btn-primary btn-sm px-5 m-1" name="action" value="goto_inquiry_edit">編集</button>	
							</form>
							<form action="inquiry_delete" method="post" class="text-center" style="display: inline-block">
								<input type="hidden" name="inquiry_id" value="${inquiry.id }">
								<button type="submit" class="btn btn-danger btn-sm px-5 m-1" name="action" value="goto_delete">削除</button>	
							</form> 
						</c:when>
						<c:when test="${action == '以下のお問合せを削除します' || delete_error != null }">
							<form action="inquiry_delete" method="post" class="text-center" style="display: inline-block">
								ユーザーID：<input type="text" name="user_id"> 
								パスワード：<input type="password" autocomplete="new-password" name="password"><br>
								<input type="hidden" name="inquiry_id" value="${inquiry.id }">
								<button type="submit" class="btn btn-danger btn-sm px-5 m-1" name="action" value="execute_delete" onclick="return confirm('削除してもよろしいですか？')">削除</button>	
							</form>
						</c:when>
						<c:when test="${delete_execute != null }"></c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
					&nbsp;&nbsp;&nbsp;&nbsp; 
					<a href="customer_detail?customer_id=${inquiry.customer_id }">顧客詳細画面へ戻る</a>
				</div>
		</div>
	</c:param>
</c:import>