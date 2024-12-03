<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<h1 class="text-center mt-5">Payment Methods List</h1>

	<!-- Button to add new payment method -->
	<a href="payment-method/add" class="btn btn-success"
		style="margin-bottom: 10px">Add New</a>

	<!-- Payment methods table -->
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th>QR Code</th>
				<th>Method Name(Bank)</th>
				<th>Account Number</th>
				<th>Account Owner</th>
				<th>Status</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<!-- Iterate through the list of payment methods -->
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
							<c:when test="${paymentMethod.status == 1}">Active</c:when>
							<c:otherwise>Inactive</c:otherwise>
						</c:choose></td>
					<td>
						<!-- Edit and delete buttons --> <a
						href="payment-method/edit?id=${paymentMethod.id}"
						class="btn btn-primary">Edit</a>
						<a
						href="payment-method/detail?id=${paymentMethod.id}"
						class="btn btn-success">View Details</a>
						<a
						href="payment-method/delete?id=${paymentMethod.id}"
						class="btn btn-danger"
						onclick="return confirm('Are you sure you want to delete?')">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
