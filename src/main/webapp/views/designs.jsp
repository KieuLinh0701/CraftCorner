<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">

<style>
/* Hiệu ứng hover cho card */
.card {
	transition: transform 0.3s ease, box-shadow 0.3s ease;
	/* Tạo hiệu ứng chuyển đổi mượt */
}

.card:hover {
	transform: translateY(-10px); /* Di chuyển card lên trên khi hover */
	box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); /* Tăng độ bóng khi hover */
}

.card a {
	text-decoration: none;
	color: inherit;
}

/* Đặt chữ lên trên ảnh */
.text-overlay {
	top: 20%; /* Vị trí chữ ở 20% từ phía trên */
	left: 0%; /* Canh giữa theo chiều ngang */
	transform: translate(0%, -50%); /* Canh giữa hoàn toàn */
	font-size: 2.5rem;
	font-weight: bold;
	z-index: 2; /* Đảm bảo chữ nằm trên ảnh */
	text-align: left; /* Căn chữ sang trái */
	padding-left: 10px; /* Thêm khoảng cách nếu cần */
}

.text-overlay-desc {
	top: 40%; /* Đặt mô tả thấp hơn tiêu đề */
	left: 50%; /* Canh giữa theo chiều ngang */
	transform: translate(-50%, -50%);
	font-size: 1.25rem;
	font-weight: bold;
	z-index: 2;
}

/* Đảm bảo ảnh nằm dưới chữ */
.position-relative img {
	z-index: 1;
}
</style>

	


	<!-- Header -->
	<header class="bg-dark text-white py-5 text-center position-relative"
		style="background-image: url('../images/blog2.png'); background-size: cover;">
		<div class="container position-relative">
			<h1 class="position-absolute text-overlay">Các thiết kế mẫu</h1>
			<p class="lead position-absolute text-overlay-desc">Ở đây các câu
				chuyện xoay quanh thiết kế nội thất, chia sẻ những ý tưởng sáng
				tạo...</p>
		</div>
	</header>

	<!-- Tìm kiếm theo điều kiện -->
	<form action="designs" method="get" class="container mt-4">
		<div class="row g-3">
			<!-- Tìm kiếm từ khóa -->
			<div class="col-md-3">
				<label for="query" class="form-label">Tìm kiếm từ khóa</label> <input
					type="text" id="query" name="query" value="${query}"
					class="form-control" placeholder="Nhập từ khóa...">
			</div>
			
			<!-- Tìm kiếm theo sản phẩm -->
			<div class="col-md-3">
				<label for="productName" class="form-label">Tìm theo sản
					phẩm</label> <input type="text" id="productName" name="productName"
					value="${productName}" class="form-control"
					placeholder="Nhập tên sản phẩm...">
			</div>

			<!-- Tìm kiếm theo ngày bắt đầu -->
			<div class="col-md-3">
				<label for="minDate" class="form-label">Từ ngày</label> <input
					type="date" id="minDate" name="minDate" value="${minDate}"
					class="form-control" max="${maxDateLimit}">
			</div>

			<!-- Tìm kiếm theo ngày kết thúc -->
			<div class="col-md-3">
				<label for="maxDate" class="form-label">Đến ngày</label> <input
					type="date" id="maxDate" name="maxDate" value="${maxDate}"
					class="form-control" max="${maxDateLimit}">
			</div>

			
		</div>
		

		<div class="row g-3 mt-3">
			<!-- Nút tìm kiếm -->
			<div class="col-md-3">
				<button type="submit" class="btn btn-primary w-100">Tìm
					kiếm</button>
			</div>

			<!-- Nút hiển thị tất cả danh sách -->
			<div class="col-md-3">
				<a href="designs" class="btn btn-secondary w-100">Hiển thị tất
					cả</a>
			</div>
		</div>
	</form>




	<!-- Designs Section -->
	<section class="container my-5">
		<div class="row">
			<c:forEach var="design" items="${designs}">
				<div class="col-md-6 mb-4">
					<div class="card">
						<a href="designs-details?id=${design.designId}"> <img
							src="${pageContext.request.contextPath}${design.image}"
							class="card-img-top" alt="Hình ảnh thiết kế">
							<div class="card-body">
								<h5 class="card-title">${design.title}</h5>
								<p class="card-text text-muted">${fn:substring(design.content, 0, 100)}...</p>
								<p class="card-text text-end text-muted">
									<small>Ngày tạo: ${design.createDate}</small>
								</p>
							</div>
						</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>



	<!-- Pagination -->
	<nav class="mt-4">
		<ul class="pagination justify-content-center">
			<c:forEach begin="1" end="${totalPages}" var="pageNum">
				<li class="page-item ${pageNum == currentPage ? 'active' : ''}">
					<a class="page-link"
					href="designs?page=${pageNum}&query=${query}&minDate=${minDate}&maxDate=${maxDate}">
						${pageNum} </a>
				</li>
			</c:forEach>
		</ul>
	</nav>


</body>
</html>
