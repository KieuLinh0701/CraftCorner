<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<div class="container">
	<h1 class="text-center mt-5">Add New Payment Method</h1>

	<!-- Form to add a payment method -->
	<form
		action="${pageContext.request.contextPath}/admin/payment-method/add"
		method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="bankName">Method Name(Bank)</label> 
			<input
				type="text" class="form-control" id="bankName" name="bankName"
				required>
		</div>

		<div class="form-group">
			<label for="accountNumber">Account Number</label> 
			<input type="text"
				class="form-control" id="accountNumber" name="accountNumber"
				required>
		</div>

		<div class="form-group">
			<label for="accountOwner">Account Owner</label> 
			<input type="text"
				class="form-control" id="accountOwner" name="accountOwner" required>
		</div>

		<div class="form-group">
			<label for="status">Status</label> 
			<select class="form-control"
				id="status" name="status" required>
				<option value="1">Active</option>
				<option value="0">Inactive</option>
			</select>
		</div>

		<div class="form-group">
			<label for="image">QR Code</label> 
			<input type="file"
				class="form-control" id="image" name="image" accept="image/*">
		</div>

		<button type="submit" class="btn btn-primary">Add</button>
		<a href="${pageContext.request.contextPath}/admin/payment-method"
			class="btn btn-default">Back</a>
	</form>
</div>
