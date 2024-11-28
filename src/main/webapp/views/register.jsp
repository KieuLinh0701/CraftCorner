<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<!-- BEGIN CONTENT -->
<div class="col-md-12 col-sm-12">
	<div class="content-form-page">
		<div class="row">
			<div class="col-md-7 col-sm-7">
				<c:if test="${alert !=null}">
					<h3 class="alert alertdanger">${alert}</h3>
				</c:if>
				<form action="${pageContext.request.contextPath}/register"
					method="post" class="form-horizontal" role="form"
					onsubmit="return validateForm()">
					<fieldset>
						<legend>Your personal details</legend>
						<div class="form-group">
							<label for="email" class="col-lg-4 control-label"> Email
								<span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="email" name="email">
								<span id="emailError" class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="fullname" class="col-lg-4 control-label">
								Full Name <span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="fullname"
									name="fullname"> <span id="fullnameError"
									class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="phone" class="col-lg-4 control-label"> Phone
								Number <span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="phone" name="phone">
								<span id="phoneError" class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
							</div>
						</div>
					</fieldset>
					<fieldset>
						<legend>Your address</legend>
						<div class="form-group">
							<label for="city" class="col-lg-4 control-label"> City/Province<span
								class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="city" name="city">
								<span id="cityError" class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="district" class="col-lg-4 control-label">
								District <span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="district"
									name="district"> <span id="districtError"
									class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="ward" class="col-lg-4 control-label"> Ward/Commune <span
								class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="ward" name="ward">
								<span id="wardError" class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="detail" class="col-lg-4 control-label">
								Detail <span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="detail"
									name="detail"> <span id="detailError"
									class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
							</div>
						</div>
					</fieldset>
					<fieldset>
						<legend>Your password</legend>
						<div class="form-group">
							<label for="password" class="col-lg-4 control-label">Password
								<span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="password" class="form-control" id="password"
									name="password"> <span id="passwordError"
									class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
							</div>
							<i class="fa fa-eye" id="togglePassword1"></i>
						</div>

						<div class="form-group">
							<label for="confirmPassword" class="col-lg-4 control-label">Confirm
								password <span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="password" class="form-control" id="confirmPassword"
									name="confirmPassword"> <span id="confirmPasswordError"
									class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
							</div>
							<i class="fa fa-eye" id="togglePassword2"></i>
						</div>
					</fieldset>

					<div class="row">
						<div
							class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-20">
							<button type="submit" class="registerbtn"
								style="background-color: black; height: 40px; width: 180px; color: white; font-size: 15px">CREATE
								AN ACCOUNT</button>
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
	const password = document.getElementById('password');
	const togglePassword1 = document.getElementById('togglePassword1');
	const confirmPassword = document.getElementById("confirmPassword");
	const togglePassword2 = document.getElementById('togglePassword2');

	togglePassword1.addEventListener('click', function() {
		if (password.type === 'password') {
			password.type = 'text';
			togglePassword1.classList.remove('fa-eye');
			togglePassword1.classList.add('fa-eye-slash');
		} else {
			password.type = 'password';
			togglePassword1.classList.remove('fa-eye-slash');
			togglePassword1.classList.add('fa-eye');

		}
	});

	togglePassword2.addEventListener('click', function() {
		if (confirmPassword.type === 'password') {
			confirmPassword.type = 'text';
			togglePassword2.classList.remove('fa-eye');
			togglePassword2.classList.add('fa-eye-slash');
		} else {
			confirmPassword.type = 'password';
			togglePassword2.classList.remove('fa-eye-slash');
			togglePassword2.classList.add('fa-eye');

		}
	});

	function validateForm() {
		const fullname = document.getElementById("fullname").value;
		const email = document.getElementById("email").value;
		const phone = document.getElementById("phone").value;
		const password = document.getElementById("password").value;
		const confirmPassword = document.getElementById("confirmPassword").value;
		const city = document.getElementById("city").value;
		const district = document.getElementById("district").value;
		const ward = document.getElementById("ward").value;
		const detail = document.getElementById("detail").value;

		let hasError = false;

		//Kiểm tra city
		if (city === "") {
			document.getElementById("cityError").textContent = "Please enter your city!";
			hasError = true;
		} else {
			document.getElementById("cityError").textContent = "";
		}

		//Kiểm tra district
		if (district === "") {
			document.getElementById("districtError").textContent = "Please enter your district!";
			hasError = true;
		} else {
			document.getElementById("districtError").textContent = "";
		}

		//Kiểm tra ward
		if (ward === "") {
			document.getElementById("wardError").textContent = "Please enter your ward!";
			hasError = true;
		} else {
			document.getElementById("wardError").textContent = "";
		}

		//Kiểm tra detail
		if (detail === "") {
			document.getElementById("detailError").textContent = "Please enter your detail!";
			hasError = true;
		} else {
			document.getElementById("detailError").textContent = "";
		}

		//Kiểm tra fullname
		if (fullname === "") {
			document.getElementById("fullnameError").textContent = "Please enter your full name!";
			hasError = true;
		} else {
			document.getElementById("fullnameError").textContent = "";
		}

		//Kiểm tra email
		if (email === "") {
			document.getElementById("emailError").textContent = "Please enter your email address!";
			hasError = true;
		} else if (!validateEmail(email)) {
			document.getElementById("emailError").textContent = "Email address is not valid!";
			hasError = true;
		} else {
			document.getElementById("emailError").textContent = "";
		}

		//Kiểm tra phone number
		if (phone === "") {
			document.getElementById("phoneError").textContent = "Please enter your phone number!";
			hasError = true;
		} else {
			document.getElementById("phoneError").textContent = "";
		}

		//Kiểm tra password
		if (password === "") {
			document.getElementById("passwordError").textContent = "Please enter your password!";
			hasError = true;
		} else if (!validatePasswordStrength(password)) {
			document.getElementById("passwordError").textContent = "The password must be a minimum of 8 characters with a combination of upper and lower case letters, numbers, and special characters (@$!%*?_&)!";
			hasError = true;
		} else {
			document.getElementById("passwordError").textContent = "";
		}

		//Kiểm tra confirmPassword
		if (confirmPassword === "") {
			document.getElementById("confirmPasswordError").textContent = "Please confirm your password!";
			hasError = true;
		} else if (confirmPassword !== password) {
			document.getElementById("confirmPasswordError").textContent = "Your password and your confirmed password do not match!";
			hasError = true;
		} else {
			document.getElementById("confirmPasswordError").textContent = "";
		}
		return !hasError;
	}

	//function về định dạng của email
	function validateEmail(email) {
		const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		return emailRegex.test(email);
	}

	//function về định dạng của password
	function validatePasswordStrength(password) {
		const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?_&])[A-Za-z\d@$!%*?_&]{8,}$/;
		return passwordRegex.test(password);
	}
</script>