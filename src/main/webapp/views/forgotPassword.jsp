<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<c:if test="${alert !=null}">
	<h3 class="alert alertdanger">${alert}</h3>
</c:if>

<!-- BEGIN CONTENT -->
<div class="col-md-12 col-sm-12">
	<div class="content-form-page">
		<div class="row">
			<div class="col-md-6 col-sm-6">
				<form action="${pageContext.request.contextPath}/forgotpassword"
					method="post" class="form-horizontal form-without-legend"
					role="form" onsubmit="return validateForm()">
					<div class="form-group">
						<h1>Recover Password</h1>
						<label for="email" class="col-lg-4 control-label">Email <span
							class="require">*</span></label>
						<div class="col-lg-8">
							<input type="text" class="form-control" id="email" name="email"
								require> <span id="emailError" class="error-message"
								style="color: #E02222; font-size: 12px; font-style: italic;"></span>
						</div>
					</div>
					<div class="row">
						<div
							class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-20">
							<button type="submit" class="btn btn-primary"
								style="background-color: black;">Submit</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- END CONTENT -->

<script>
	function validateForm() {
		const email = document.getElementById("email").value;

		let hasError = false;

		//Kiểm tra email
		if (email === "") {
			document.getElementById("emailError").textContent = "Please enter your email address!";
			hasError = true;
		} else {
			document.getElementById("emailError").textContent = "";
		}

		return !hasError;
	}
</script>
