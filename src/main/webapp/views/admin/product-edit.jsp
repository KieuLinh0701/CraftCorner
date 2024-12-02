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
					action="${pageContext.request.contextPath}/admin/product/update"
					method="POST" enctype="multipart/form-data">
					<input type="hidden" id="product_id" name="product_id" value="${product.product_id}"><br>
					<div class="row">
						<div class="col-md-6 col-sm-6">
							<h1>Modify Product</h1>
						</div>
						<div class="col-md-6 col-sm-6">
							<button type="submit" class="btn btn-primary"
								style="background: black; border: 1px solid black; float: right; margin-right: 30px;">Save</button>
							<a href="javascript:window.history.back();" class="btn btn-secondary"
								style="background: white; border: 1px solid black; color: black; float: right; margin-right: 10px;">Cancel</a>
						</div>
						<div class="row col-md-12 col-sm-12">
							<div class="col-md-4 col-sm-4">
								<label for="image">Image</label> <input type="file"
									class="form-control" name="image" id="image" accept="image/*"
									onchange="previewImage(event)"
									value="${product.image}"> <br>

								<c:if test="${product.image.substring(0,5) !='https'}">
									<c:url value="/image?fname=${product.image}" var="imgUrl"></c:url>
								</c:if>
								<c:if test="${product.image.substring(0,5) =='https'}">
									<c:url value="${product.image}" var="imgUrl"></c:url>
								</c:if>

								<img id="image-preview" name="image-preview" src="${imgUrl}"
									alt="Image Preview"
									style="max-width: 100%; max-height: 300px; border: 1px solid #ccc; padding: 5px;">

								<label> <input type="checkbox" name="status"
									<c:if test="${product.status == 1}">
										checked
									</c:if>>
									public
								</label>
							</div>
							<div class="col-md-8 col-sm-8 Shopping cart">
								<label for="name">Name</label> <input type="text"
									class="form-control" name="name" id="name"
									value="${product.name}"> <label for="category">Category</label>
								<select id="category" name="category" class="form-control">
									<c:forEach var="category" items="${listCategory}">
										<option value="${category.category_id}"
											${category.category_id == product.category.category_id ? 'selected' : ''}>${category.name}</option>
									</c:forEach>
								</select> <label for="material">Material</label> <input type="text"
									class="form-control" name="material" id="material"
									value="${product.material}"> <label for="color">Color</label>
								<input type="text" class="form-control" name="color" id="color"
									value="${product.color}"> <label for="size">Size
									(unit: cm)</label>
								<div class="row">
									<div class="col-md-4">
										<input type="number" class="form-control" name="height"
											value="${product.height}" placeholder="Height">
									</div>
									<div class="col-md-4">
										<input type="number" class="form-control" name="length"
											value="${product.length}" placeholder="Length">
									</div>
									<div class="col-md-4">
										<input type="number" class="form-control" name="width"
											value="${product.width}" placeholder="Width">
									</div>
								</div>

								<label for="price">Price (unit: VND)</label> <input
									type="number" class="form-control" name="price" id="price"
									value="${product.price}"> <label for="quantity">Quantity</label>
								<input type="number" class="form-control" name="quantity"
									id="quantity" value="${product.quantity}"> <label
									for="description">Description</label>
								<textarea class="form-control" name="description"
									id="description" rows="5">${product.description}</textarea>
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