<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a class="btn btn-default add2cart" href="${pageContext.request.contextPath }/admin/category/add">Add
	Category</a>

<div class="col-md-12 col-sm-12">
	<h1>Shopping cart</h1>
	<div class="goods-page">
		<div class="goods-data clearfix">
			<div class="table-wrapper-responsive">
				<table>
					<tr>
						<th class="goods-page-image">Order ID</th>
						<th class="goods-page-description">User Id</th>
						<th class="goods-page-ref-no">Order Date</th>
						<th class="goods-page-description">Payment ID</th>
						<th class="goods-page-description">Status</th>
						<th class="goods-page-description">Note</th>
						<th class="goods-page-description">Total</th>
					</tr>
					<!-- controller truyền vào items -->
					<c:forEach items="${listOrders}" var="ord" varStatus="STT">
					<td class="goods-page-description">${ord.order_id}</td>
					<td class="goods-page-description">${ord.user.id}</td>
					<td>${ord.order_date}</td>
					<td class="goods-page-description">${ord.payment.id}</td>
					<td>${ord.status}</td>
					<td>${ord.note}</td>
					<td>${ord.total_price}</td>
					
					
						</tr>
					</c:forEach>
				</table>
			</div>
			
		</div>
	</div>
</div>