<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<h1 class="text-center mt-5">Danh Sách Phương Thức Thanh Toán</h1>

	<!-- Nút thêm mới phương thức thanh toán -->
	<a href="payment-method/add" class="btn btn-success"
		style="margin-bottom: 10px">Thêm Mới</a>

	<!-- Bảng danh sách phương thức thanh toán -->
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th>QR Code</th>
				<th>Tên Phương Thức(Ngân Hàng)</th>
				<th>Số Tài Khoản</th>
				<th>Chủ Tài Khoản</th>
				<th>Trạng Thái</th>
				<th>Hành động</th>
			</tr>
		</thead>
		<tbody>
			<!-- Lặp qua danh sách phương thức thanh toán -->
			<c:forEach var="paymentMethod" items="${paymentMethods}">
				<tr>
					<td>${paymentMethod.id}</td>
					<td><c:if test="${not empty paymentMethod.image}">
							<c:choose>
								<c:when test="${paymentMethod.image.startsWith('http')}">
									<img src="${paymentMethod.image}" alt="QR Code"
										style="width: 100px; height: 100px;">
								</c:when>
								<c:otherwise>
									<img
										src="${pageContext.request.contextPath}/image?fname=${paymentMethod.image}"
										alt="QR Code" style="width: 100px; height: 100px;">
								</c:otherwise>
							</c:choose>
						</c:if></td>
					<td>${paymentMethod.bankName}</td>
					<td>${paymentMethod.accountNumber}</td>
					<td>${paymentMethod.accountOwner}</td>
					<td><c:choose>
							<c:when test="${paymentMethod.status == 1}">Hoạt động</c:when>
							<c:otherwise>Không hoạt động</c:otherwise>
						</c:choose></td>
					<td>
						<!-- Nút sửa và xóa --> <a
						href="payment-method/edit?id=${paymentMethod.id}"
						class="btn btn-primary">Sửa</a>
						  <a
						href="payment-method/detail?id=${paymentMethod.id}"
						class="btn btn-success">Xem chi tiết</a>
						 <a
						href="payment-method/delete?id=${paymentMethod.id}"
						class="btn btn-danger"
						onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>