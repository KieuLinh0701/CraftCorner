<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Chi tiết phương thức thanh toán</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f0f2f5;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.container {
	width: 100%;
	max-width: 800px;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 10px;
	background-color: #ffffff;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

h1 {
	text-align: center;
	color: #333;
	margin-bottom: 20px;
	font-size: 28px;
}

.details-table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

.details-table th, .details-table td {
	border: 1px solid #ccc;
	padding: 10px;
	text-align: left;
	font-size: 16px;
}

.details-table th {
	background-color: #f4f4f4;
}

.details-table img {
	width: 150px;
	height: 150px;
	border: 1px solid #ccc;
	border-radius: 5px;
	display: block;
	margin-bottom: 20px;
}

.btn-back {
	display: inline-block;
	margin: 20px auto 0;
	text-align: center;
	text-decoration: none;
	padding: 10px 20px;
	background-color: #007bff;
	color: white;
	border-radius: 5px;
	transition: background-color 0.3s ease;
	display: block;
	font-size: 16px;
	text-align: center;
}

.btn-back:hover {
	background-color: #0056b3;
}

.details-container {
	display: flex;
	justify-content: center;
	margin-top: 20px;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Chi tiết phương thức thanh toán</h1>

		<c:if test="${paymentMethod != null}">
			<table class="details-table">
				<tr>
					<td><c:if test="${not empty paymentMethod.image}">
							<c:choose>
								<c:when test="${paymentMethod.image.startsWith('http')}">
									<img src="${paymentMethod.image}" alt="QR Code">
								</c:when>
								<c:otherwise>
									<img
										src="${pageContext.request.contextPath}/image?fname=${paymentMethod.image}"
										alt="QR Code">
								</c:otherwise>
							</c:choose>
						</c:if></td>
					<td>
						<p>
							<strong>Tên Ngân Hàng:</strong> ${paymentMethod.bankName}
						</p>
						<p>
							<strong>Số Tài Khoản:</strong> ${paymentMethod.accountNumber}
						</p>
						<p>
							<strong>Chủ Tài Khoản:</strong> ${paymentMethod.accountOwner}
						</p>
						<p>
							<strong>Trạng thái:</strong>
							<c:choose>
								<c:when test="${paymentMethod.status == 1}">Hoạt động</c:when>
								<c:otherwise>Ngừng hoạt động</c:otherwise>
							</c:choose>
						</p>
					</td>
				</tr>
			</table>
		</c:if>

		<div class="details-container">
			<a href="<%=request.getContextPath()%>/admin/payment-method"
				class="btn btn-primary">Quay lại</a>
		</div>
</body>
</html>
