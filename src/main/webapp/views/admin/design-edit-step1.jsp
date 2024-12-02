<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<div class="main">
	<div class="container">
		<div class="row margin-bottom-40">
			<!-- BEGIN CONTENT -->
			<div class="col-md-12 col-sm-12">
				<form
					action="${pageContext.request.contextPath}/admin/design/update"
					method="POST" enctype="multipart/form-data">
					<input type="hidden" name="designId" id="designId"
						value="${design.designId}">
					<div class="row">
						<div class="col-md-6 col-sm-6">
							<h1>Add New Design</h1>
						</div>
						<div class="col-md-6 col-sm-6">
							<button type="submit" class="btn btn-primary"
								style="background: black; border: 1px solid black; float: right;">Next</button>
							<a href="javascript:window.history.back();"
								class="btn btn-secondary"
								style="background: white; border: 1px solid black; color: black; float: right; margin-right: 10px;">Cancel</a>
						</div>
					</div>
					<br>
					<div class="panel-group checkout-page accordion scrollable"
						id="checkout-page">

						<!-- BEGIN DETAIL DESIGN -->
						<div id="checkout" class="panel panel-default">
							<div class="panel-heading">
								<h2 class="panel-title">
									<a data-toggle="collapse" data-parent="#checkout-page"
										href="#checkout-content" class="accordion-toggle"
										style="background: black"> Step 1: Design template
										information </a>
								</h2>
							</div>
							<div id="checkout-content" class="panel-collapse collapse in">
								<div class="panel-body row"
									style="margin-left: 50px; margin-right: 50px;">
									<div class="content-page">
										<div class="row">
											<div class="blog-item-img">
												<label for="image">Upload Image</label> <input type="file"
													class="form-control" name="image" id="image"
													accept="image/*" onchange="previewImage(event)"> <br>
												<c:if test="${design.image.substring(0,5) !='https'}">
									<c:url value="/image?fname=${design.image}" var="imgUrl"></c:url>
								</c:if>
								<c:if test="${design.image.substring(0,5) =='https'}">
									<c:url value="${design.image}" var="imgUrl"></c:url>
								</c:if>

								<img id="image-preview" name="image-preview" src="${imgUrl}"
									alt="Image Preview"
									style="max-width: 100%; max-height: 300px; border: 1px solid #ccc; padding: 5px;"> <br>
												<label> <input type="checkbox" name="status"
													value="${design.status}"> public
												</label> <br> <label for="title">Title</label> <input
													type="text" class="form-control" name="title" id="title"
													required value="${design.title}"> <br> <label
													for="content" style="font-size: 15px">Content</label>
												<textarea class="form-control" name="content" id="content"
													 rows="7">${design.content}</textarea>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- END DETAIL DESIGN -->
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script>
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
