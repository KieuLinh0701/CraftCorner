<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
    String alertMessage = (String) session.getAttribute("alertMessage");
    if (alertMessage != null) {
        out.println("<script>alert('" + alertMessage + "');</script>");
        session.removeAttribute("alertMessage");
    }
%>

<c:if test="${alert !=null}">
	<h3 class="alert alertdanger">${alert}</h3>
</c:if>

<!-- BEGIN CONTENT -->
<div class="col-md-12 col-sm-12">
	<div class="content-form-page">
		<div class="row">
			<div class="col-md-7 col-sm-7">
				<form action="${pageContext.request.contextPath}/verifycodeaccount"
					method="post" class="form-horizontal form-without-legend"
					role="form" onsubmit="return validateForm()">
					<div class="form-group">
						<input type="hidden" class="form-control" id="email" name="email"
							value="${sessionScope.account.email}">
						<div class="form-group">
							<label for="code" class="col-lg-4 control-label"> Code <span
								class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="code" name="code">
								<span id="codeError" class="error-message"
									style="color: #E02222; font-size: 12px; font-style: italic;"></span>
								<br>
							</div>
						</div>
						<div class="row">
							<div
								class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-20">
								<button type="submit" class="btn btn-primary"
									style="background-color: black">Activate account</button>
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
		const code = document.getElementById("code").value;

		let hasError = false;

		//Kiểm tra code
		if (code === "") {
			document.getElementById("codeError").textContent = "Please enter your code!";
			hasError = true;
		} else {
			document.getElementById("codeError").textContent = "";
		}

		return !hasError;
	}
</script>
