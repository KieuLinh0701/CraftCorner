<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>


<%
String alertMessage = (String) session.getAttribute("alertMessage");
if (alertMessage != null) {
	out.println("<script>alert('" + alertMessage + "');</script>");
	session.removeAttribute("alertMessage");
}
%>

<!-- BEGIN CONTENT -->
<div class="col-md-12 col-sm-12">
	<div class="content-form-page">
		<div class="row">
			<div class="col-md-7 col-sm-7">
				<c:if test="${alert !=null}">
					<h3 class="alert alertdanger">${alert}</h3>
				</c:if>
				<form action="${pageContext.request.contextPath}/login"
					method="post" class="form-horizontal form-without-legend"
					role="form" onsubmit="return validateForm()">
					<div class="form-group">
						<label for="email" class="col-lg-4 control-label">Email <span
							class="require">*</span></label>
						<div class="col-lg-8">
							<input type="text" class="form-control" id="email" name="email"
								require> <span id="emailError" class="error-message"
								style="color: #E02222; font-size: 12px; font-style: italic;"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-lg-4 control-label">Password
							<span class="require">*</span>
						</label>
						<div class="col-lg-8">
							<input type="password" class="form-control" id="password"
								name="password"> <span id="passwordError"
								class="password-message"
								style="color: #E02222; font-size: 12px; font-style: italic;"></span>
						</div>
						<i class="fa fa-eye" id="togglePassword"></i>
					</div>


					<div class="row">

						<div class="col-lg-8 col-md-offset-4 padding-left-0">
							<input type="checkbox" checked="checked" name="remember">
							Remember me <br> <a
								href="${pageContext.request.contextPath}/forgotpassword">Forget
								Password?</a>
						</div>
					</div>
					<div class="row">
						<div
							class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-20">
							<button type="submit" class="btn btn-primary"
								style="background-color: black">Login</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col-md-5 col-sm-5 pull-right">
				<div class="form-info">
					<h2>
						<em>Important</em> Information
					</h2>
					<p>All fields marked with an asterisk (*) are required.</p>
					<p>Please ensure that all information is accurate and complete.</p>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- END CONTENT -->

<script>
	const passwordInput = document.getElementById('password');
	const togglePassword = document.getElementById('togglePassword');

	togglePassword.addEventListener('click', function() {
		if (passwordInput.type === 'password') {
			passwordInput.type = 'text';
			togglePassword.classList.remove('fa-eye');
			togglePassword.classList.add('fa-eye-slash');
		} else {
			passwordInput.type = 'password';
			togglePassword.classList.remove('fa-eye-slash');
			togglePassword.classList.add('fa-eye');

		}
	});

	function validateForm() {
		const email = document.getElementById("email").value;
		const password = document.getElementById("password").value;

		let hasError = false;

		//Kiểm tra email
		if (email === "") {
			document.getElementById("emailError").textContent = "Please enter your email address!";
			hasError = true;
		} else {
			document.getElementById("emailError").textContent = "";
		}

		//Kiểm tra password
		if (password === "") {
			document.getElementById("passwordError").textContent = "Please enter your password!";
			hasError = true;
		} else {
			document.getElementById("passwordError").textContent = "";
		}

		return !hasError;
	}
</script>