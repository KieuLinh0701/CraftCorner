<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<head>
<style>
.center-content {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	text-align: center;
	min-height: 100vh; /* Căn giữa theo chiều dọc */
}

.center-content img {
	margin: 20px 0; /* Thêm khoảng cách giữa ảnh và các phần khác */
}
</style>
</head>

<div class="main">
	<div class="container">
		<!-- BEGIN SIDEBAR & CONTENT -->
		<div class="row margin-bottom-40">
			<!-- BEGIN CONTENT -->
			<div class="col-md-12 col-sm-12 center-content">
				<c:if test="${fn:contains(payment.bankName, 'Cash')}">
					<h1>Thank you for your order. Your order will be shipped
						shortly!</h1>
				</c:if>
				<c:if test="${!fn:contains(payment.bankName, 'Cash')}">
					<h1>Thanks for your order! Please make a bank transfer using
						the account details below!</h1>
					<hr>

					<c:if test="${payment.image.substring(0,5) != 'https'}">
						<c:url value="/image?fname=${payment.image}" var="imgUrl"></c:url>
					</c:if>
					<c:if test="${payment.image.substring(0,5) == 'https'}">
						<c:url value="${payment.image}" var="imgUrl"></c:url>
					</c:if>
					<img class="img-responsive" alt="${product.name}" height="160"
						width="210" src="${imgUrl}">

					<p>
						<strong>Bank Name: </strong>${payment.bankName}</p>
					<p>
						<strong>Account Number: </strong>${payment.accountNumber}</p>
					<p>
						<strong>Account Owner: </strong>${payment.accountOwner}</p>
				</c:if>
			</div>
			<!-- END CONTENT -->
		</div>
		<!-- END SIDEBAR & CONTENT -->
	</div>
</div>
