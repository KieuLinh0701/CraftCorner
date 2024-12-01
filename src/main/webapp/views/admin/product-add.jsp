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
				<form action="${pageContext.request.contextPath}/admin/product/insert" method="POST"
					enctype="multipart/form-data">
					<div class="row">
						<div class="col-md-6 col-sm-6">
							<h1>Add New Product</h1>
						</div>
						<div class="col-md-6 col-sm-6">
							<button type="submit" class="btn btn-primary"
								style="background: black; border: 1px solid black; float: right; margin-right: 30px;">Save</button>
							<a href="javascript:window.history.back();" class="btn btn-secondary"
								style="background: white; border: 1px solid black; color: black; float: right; margin-right: 10px;">Cancel</a>
						</div>
						<div class="row col-md-12 col-sm-12">
							<div class="col-md-4 col-sm-4">
								<label for="image">Image</label> 
								<input type="file" class="form-control" name="image" id="image" accept="image/*" onchange="previewImage(event)">
								<br> <img id="image-preview" name="image-preview" src="" alt="Image Preview"
								style="max-width: 100%; max-height: 300px; display: none; border: 1px solid #ccc; padding: 5px;">
								<label> <input type="checkbox" name="status">
									public
								</label>
							</div>
							<div class="col-md-8 col-sm-8 Shopping cart">
								<label for="name">Name</label> <input type="text"
									class="form-control" name="name" id="name"> <label
									for="category">Category</label> <select id="category"
									name="category" class="form-control">
									<c:forEach var="category" items="${listCategory}">
										<option value="${category.category_id}">${category.name}</option>
									</c:forEach>
								</select> <label for="material">Material</label> <input type="text"
									class="form-control" name="material" id="material"> <label
									for="color">Color</label> <input type="text"
									class="form-control" name="color" id="color"> <label
									for="size">Size (unit: cm)</label>
								<div class="row">
									<div class="col-md-4">
										<input type="number" class="form-control" name="height" value="0"
											placeholder="Height">
									</div>
									<div class="col-md-4">
										<input type="number" class="form-control" name="length" value="0"
											placeholder="Length">
									</div>
									<div class="col-md-4">
										<input type="number" class="form-control" name="width" value="0"
											placeholder="Width">
									</div>
								</div>

								<label for="price">Price (unit: VND)</label> <input
									type="number" class="form-control" name="price" id="price" value="0"
									> 
									<label for="quantity">Quantity</label> <input
									type="number" class="form-control" name="quantity" value="0"
									id="quantity"> 
									<label for="description">Description</label>
								<textarea class="form-control" name="description"
									id="description" rows="5"></textarea>
							</div>
						</div>
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