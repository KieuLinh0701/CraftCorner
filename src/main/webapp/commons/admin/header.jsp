<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<head>
<style>
.admin-theme {
	display: none;
}

.customer-theme {
	display: block;
}
</style>
</head>

<!-- DangNhap, DangKy -->
<!-- BEGIN TOP BAR -->
<div class="pre-header">
	<div class="container">
		<!-- BEGIN TOP BAR MENU -->
		<div class="col-md-12 col-sm-12 additional-nav">
			<ul class="list-unstyled list-inline pull-right">
				<li><c:choose>
						<c:when test="${sessionScope.account == null}">
							<a href="${pageContext.request.contextPath}/login">Login</a>
								| <a href="${pageContext.request.contextPath}/register">Register</a>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/admin/myaccount">${sessionScope.account.fullname}</a>
								| <a href="${pageContext.request.contextPath}/logout">Logout</a>
						</c:otherwise>
					</c:choose></li>
			</ul>
		</div>
		<!-- END TOP BAR MENU -->
	</div>
</div>
<!-- END TOP BAR -->

<!-- BEGIN HEADER -->
<div class="header">
	<div class="container">
		<a class="site-logo" href="${pageContext.request.contextPath}/admin/home"><img
			src="${URL}assets/frontend/layout/img/logos/logo2.png"
			alt="Carft Corner"></a> <a href="javascript:void(0);"
			class="mobi-toggler"><i class="fa fa-bars"></i></a>

		<!-- BEGIN CART -->
		<div class="top-cart-block">
			<div class="top-cart-info">
				<a href="${pageContext.request.contextPath}/cart"
					class="top-cart-info-count">0 items</a>
			</div>
			<i class="fa fa-shopping-cart" style="background-color: black"></i>
		</div>
		<!--END CART -->

		<!-- BEGIN NAVIGATION -->
		<div class="header-navigation">
			<ul>
				<li class="admin-theme dropdown active"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#">
						Product</a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/admin/products">Product Management</a></li>
						<li><a href="#">Category Management</a></li>
						<li><a href="#">Inventory Management</a></li>
					</ul></li>

				<li class="admin-theme dropdown active"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#">
						Finance</a>
					<ul class="dropdown-menu">
						<li><a href="${URL}admin/payment-method"> Payment Method
						</a></li>
						<li><a href="#">Revenue Management</a></li>
						<li><a href="#">Transaction History</a></li>
					</ul></li>

				<li class="admin-theme dropdown active"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#">
						Content</a>
					<ul class="dropdown-menu">
						<li><a href="#">Blog Management</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/designs">Design Management</a></li>
					</ul></li>

				<li class="admin-theme dropdown active"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#">
						Account</a>
					<ul class="dropdown-menu">
						<li><a href="#">Customer Management</a></li>
						<li><a href="#">Employee Management</a></li>
					</ul></li>
					
				<li class="admin-theme"><a href="#"
					onclick="showCustomerTheme()"> Order</a></li>


				<li class="admin-theme"><a href="${URL}admin/promote"> Promotion</a></li>

				<li class="admin-theme"><a
					href="${URL}admin/appointment-calenda"> Appointment</a></li>

				<li class="admin-theme"><a href="javascript:void(0)"
					onclick="showCustomerTheme()"> Customer theme </a></li>

				<li class="customer-theme"><a
					href="${pageContext.request.contextPath}/admin/home"> Home </a></li>
				<li class="customer-theme"><a href="#"> Shop </a></li>
				<li class="customer-theme"><a href="#"> Blogs </a></li>
				<li class="customer-theme"><a href="#"> Design ideas </a></li>
				<li class="customer-theme"><a href="javascript:void(0)"
					onclick="showAdminTheme()"> Admin Theme </a></li>
			</ul>
		</div>
	</div>
	<!-- END NAVIGATION -->
</div>
<!-- Header END -->

<script>
function showAdminTheme() {
    // Ẩn tất cả các mục của customer-theme
    const customerItems = document.querySelectorAll('.customer-theme');
    customerItems.forEach(item => {
        item.style.display = 'none';
    });

    // Hiện tất cả các mục của admin-theme
    const adminItems = document.querySelectorAll('.admin-theme');
    adminItems.forEach(item => {
        item.style.display = 'block';
    });
}

function showCustomerTheme() {
    // Ẩn tất cả các mục của admin-theme
    const adminItems = document.querySelectorAll('.admin-theme');
    adminItems.forEach(item => {
        item.style.display = 'none';
    });

    // Hiện tất cả các mục của customer-theme
    const customerItems = document.querySelectorAll('.customer-theme');
    customerItems.forEach(item => {
        item.style.display = 'block';
    });
}
</script>