<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<head>
<style>
.disabled-link {
	pointer-events: none; /* Vô hiệu hóa sự kiện click */
	opacity: 0.6; /* Hiệu ứng làm mờ */
	cursor: not-allowed; /* Thay đổi con trỏ */
}
</style>

</head>

<c:if test="${not empty session.errorMessage}">
	<div class="alert alert-danger">${session.errorMessage}</div>
</c:if>

<div class="main">
	<div class="container">
		<div class="row margin-bottom-40">
			<!-- BEGIN CONTENT -->
			<div class="col-md-12 col-sm-12">
				<form action="${pageContext.request.contextPath}/admin/designs"
					method="GET" enctype="multipart/form-data">
					<div class="row">
						<div class="col-md-6 col-sm-6">
							<h1>Add New Design</h1>
						</div>
						<div class="col-md-6 col-sm-6">
							<button type="submit" class="btn btn-primary"
								style="background: black; border: 1px solid black; float: right;">Complete</button>
							<a
								href="${pageContext.request.contextPath}/admin/design/deletestep2"
								class="btn btn-secondary"
								style="background: white; border: 1px solid black; color: black; float: right; margin-right: 10px;">Cancel</a>
						</div>
					</div>
				</form>
				<br>
				<div class="panel-group checkout-page accordion scrollable"
					id="checkout-page">
					<!-- BEGIN DETAIL DESIGN -->
					<div id="checkout" class="panel panel-default">
						<div class="panel-heading">
							<h2 class="panel-title">
								<a data-toggle="collapse" data-parent="#checkout-page"
									href="#checkout-content" class="accordion-toggle"
									style="background: black"> Step 2: Product attached with a
									design template </a>
							</h2>
						</div>
						<div id="checkout-content" class="panel-collapse collapse in">
							<div class="panel-body row">
								<br>
								<form
									action="${pageContext.request.contextPath}/admin/designitems/search"
									method="POST">
									<input type="hidden" name="designId" id="designId"
										value="${designId }">
									<!-- Tìm kiếm chiếm 4 phần -->
									<div class="col-md-5 col-sm-5">
										<div class="input-group">
											<input type="text" class="form-control"
												placeholder="Search..." name="search" id="search"> <span
												class="input-group-btn">
												<button type="submit" class="btn btn-primary"
													style="background: black">Search</button>
											</span>
										</div>
										<c:if test="${alert != null}">
											<h3 class="alert alertdanger"
												style="color: red; font-size: 12px;">${alert}</h3>
										</c:if>
										<br>
										<h4>Selected Products</h4>
										<c:forEach var="item" items="${listItem}">
											<div class="col-md-4 col-sm-4">
												<c:if test="${item.product.image.substring(0,5) != 'https'}">
													<c:url value="/image?fname=${item.product.image}"
														var="imgUrl"></c:url>
												</c:if>
												<c:if test="${item.product.image.substring(0,5) == 'https'}">
													<c:url value="${item.product.image}" var="imgUrl"></c:url>
												</c:if>
												<img class="img-responsive" alt="${item.product.name}"
													height="60" width="80" src="${imgUrl}">
											</div>
											<div
												class="col-md-4 col-sm-4 d-flex justify-content-center align-items-center"
												style="height: 60px;">
												<p style="margin: 0; text-align: center;">${item.product.name}</p>
											</div>
											<div class="col-md-4 col-sm-4">
												<a
													href="${pageContext.request.contextPath}/admin/designitem/delete?id=${item.designItem_id}"
													class="btn btn-primary d-flex align-items-center"
													style="background: black; float: right; margin-right: 10px; color: white;">
													<i class="fa fa-trash"></i>
												</a>
											</div>
											<hr>
										</c:forEach>
									</div>
									<!-- Bảng sản phẩm chiếm 8 phần -->
									<div class="col-md-7 col-sm-7">
										<div id="product-container" style="min-height: 400px;">
											<table style="border-collapse: collapse; width: 100%;">
												<tr style="height: 30px; border-bottom: 1px solid black;">
													<th>Image</th>
													<th>Name</th>
													<th>Description</th>
													<th>Action</th>
												</tr>
												<c:forEach var="product" items="${listProduct}">
													<tr style="border-bottom: 1px solid black;">
														<td style="height: 110px; width: 160px;"><c:if
																test="${product.image.substring(0,5) != 'https'}">
																<c:url value="/image?fname=${product.image}"
																	var="imgUrl"></c:url>
															</c:if> <c:if test="${product.image.substring(0,5) == 'https'}">
																<c:url value="${product.image}" var="imgUrl"></c:url>
															</c:if>
															<div
																style="display: flex; align-items: center; height: 100%; width: 100%;">
																<img class="img-responsive" alt="${product.name}"
																	height="90" width="120" src="${imgUrl}">
															</div></td>
														<td>${product.name}</td>
														<td>
															<p>
																Material: ${product.material} <br> Color:
																${product.color}<br> Size: height:
																${product.height} - length: ${product.length} - width:
																${product.width}<br> Quantity: ${product.quantity}
															</p>
														</td>
														<td><c:set var="productExists" value="false" /> <c:forEach
																var="item" items="${listItem}">
																<c:if
																	test="${product.product_id == item.product.product_id}">
																	<c:set var="productExists" value="true" />
																</c:if>
															</c:forEach> <c:if test="${productExists}">
																<span class="btn btn-primary disabled-link"
																	style="background: black; float: right; margin-right: 10px; color: white"
																	title = "This product has already been included in the design template!">
																	<i class="fa fa-plus"></i>
																</span>
															</c:if> <c:if test="${!productExists}">
																<a
																	href="${pageContext.request.contextPath}/admin/designitem/insert?id=${product.product_id}"
																	class="btn btn-primary"
																	style="background: black; float: right; margin-right: 10px; color: white">
																	<i class="fa fa-plus"></i>
																</a>
															</c:if></td>
													</tr>
												</c:forEach>

											</table>
										</div>
										<hr class="blog-post-sep">
										<c:choose>
											<c:when test="${not empty search}">
												<ul class="pagination" style="float: right;">
													<!-- Prev button -->
													<c:if test="${page > 1}">
														<li><a
															href="${pageContext.request.contextPath}/admin/designitems/search?search=${search}&page=${page-1}">Prev</a></li>
													</c:if>

													<!-- Page Numbers -->
													<c:forEach var="i" begin="1" end="${totalPages}">
														<li class="${page == i ? 'active' : ''}"><a
															href="${pageContext.request.contextPath}/admin/designitems/search?search=${search}&page=${i}">${i}</a>
														</li>
													</c:forEach>

													<!-- Next button -->
													<c:if test="${page < totalPages}">
														<li><a
															href="${pageContext.request.contextPath}/admin/designitems/search?search=${search}&page=${page+1}">Next</a></li>
													</c:if>
												</ul>
											</c:when>
											<c:otherwise>
												<ul class="pagination" style="float: right;">
													<!-- Prev button -->
													<c:if test="${page > 1}">
														<li><a
															href="${pageContext.request.contextPath}/admin/designitems?page=${page-1}">Prev</a></li>
													</c:if>

													<!-- Page Numbers -->
													<c:forEach var="i" begin="1" end="${totalPages}">
														<li class="${page == i ? 'active' : ''}"><a
															href="${pageContext.request.contextPath}/admin/designitems?page=${i}">${i}</a>
														</li>
													</c:forEach>

													<!-- Next button -->
													<c:if test="${page < totalPages}">
														<li><a
															href="${pageContext.request.contextPath}/admin/designitems?page=${page+1}">Next</a></li>
													</c:if>
												</ul>
											</c:otherwise>
										</c:choose>
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- END DETAIL DESIGN -->
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	function updateSelectedProducts(event, productName) {
		const selectedProductsList = document
				.getElementById('selected-products');

		// Nếu checkbox được chọn, thêm vào danh sách
		if (event.target.checked) {
			const listItem = document.createElement('li');
			listItem.textContent = productName;
			selectedProductsList.appendChild(listItem);
		} else {
			// Nếu checkbox bị bỏ chọn, xóa khỏi danh sách
			const items = selectedProductsList.getElementsByTagName('li');
			for (let i = 0; i < items.length; i++) {
				if (items[i].textContent === productName) {
					selectedProductsList.removeChild(items[i]);
					break;
				}
			}
		}
	}

	function previewImage(event) {
		const file = event.target.files[0]; // Lấy tệp từ input
		const preview = document.getElementById('image-preview'); // Thẻ img để hiển thị ảnh

		if (file) {
			const reader = new FileReader();

			reader.onload = function(e) {
				preview.src = e.target.result; // Đặt src của img là nội dung file
				preview.style.display = 'block'; // Hiển thị ảnh
			};

			reader.readAsDataURL(file); // Đọc file dưới dạng Data URL
		}
	}
</script>
