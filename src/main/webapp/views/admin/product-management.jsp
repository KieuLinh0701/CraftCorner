<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<div class="main">
	<!-- BEGIN SIDEBAR & CONTENT -->
	<div class="row margin-bottom-40">
		<!-- BEGIN CONTENT -->
		<div class="col-md-12 col-sm-12">
			<form
				action="${pageContext.request.contextPath}/admin/products/search"
				method="POST">
				<div class="input-group content-search-view2">
					<input type="text" class="form-control" placeholder="Search..."
						name="search" id="search" value="${search}"> <span
						class="input-group-btn">
						<button type="submit" class="btn btn-primary"
							style="background: black">Search</button>
					</span>
				</div>
				<hr class="blog-post-sep">
				<input type="hidden" id="alertMessage" name="alert" value="${alert}">
				<c:if test="${alert != null}">
					<h3 class="alert alertdanger" style="color: red">${alert}</h3>
				</c:if>
				<div class="col-md-6 col-sm-6">
					<h1>Product</h1>
				</div>
				<div class="col-md-6 col-sm-6">
					<a href="${pageContext.request.contextPath}/admin/product/add"
						class="btn btn-primary"
						style="background: black; float: right; margin-right: 10px;">
						New product <i class="fa fa-plus"></i>
					</a>
				</div>
				<div class="content-page">
					<div class="row">
						<br> <br>
						<!-- BEGIN LEFT SIDEBAR -->
						<div class="col-md-12 col-sm-12 blog-posts">
							<div class="row">
								<table style="border-collapse: collapse; width: calc(100% - 20px);  margin-left: 10px; margin-right: 10px;">
									<tr style="height: 30px; border-bottom: 1px solid black;">
										<th style="width: 170px;">Image</th>
										<th style="width: auto;">Name</th>
										<th style="width: 200px;">Category</th>
										<th style="width: 150px;">Price</th>
										<th style="width: 100px;">Quantity</th>
										<th style="width: 100px;">Status</th>
										<th style="width: 100px;">Action</th>
									</tr>
									<c:forEach var="product" items="${listProduct}">
										<tr style="border-bottom: 1px solid black;">
											<td style="height: 110px; width: 160px;"><c:if
													test="${product.image.substring(0,5) != 'https'}">
													<c:url value="/image?fname=${product.image}" var="imgUrl"></c:url>
												</c:if> <c:if test="${product.image.substring(0,5) == 'https'}">
													<c:url value="${product.image}" var="imgUrl"></c:url>
												</c:if>
												<div
													style="display: flex; align-items: center; height: 100%; width: 100%;">
													<img class="img-responsive" alt="${product.name}"
														height="90" width="120" src="${imgUrl}">
												</div></td>
											<td>${product.name}</td>
											<td>${product.category.name}</td>
											<td>${product.price}</td>
											<td>${product.quantity}</td>
											<td><c:if test="${product.status == 0}">
													private
												</c:if> <c:if test="${product.status == 1}">
													public
												</c:if></td>
											<td><a
												href="${pageContext.request.contextPath}/admin/product/edit?id=${product.product_id }"
												class="btn btn-primary"
												style="background: white; border: 1px solid black;"><i
													class="fa fa-gear"></i> </a> <a
												href="${pageContext.request.contextPath}/admin/product/delete?id=${product.product_id }"
												class="btn btn-primary"
												style="background: white; border: 1px solid black;"><i
													class="fa fa-trash"></i> </a></td>

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
												href="${pageContext.request.contextPath}/admin/products/search?search=${search}&page=${page-1}">Prev</a></li>
										</c:if>

										<!-- Page Numbers -->
										<c:forEach var="i" begin="1" end="${totalPages}">
											<li class="${page == i ? 'active' : ''}"><a
												href="${pageContext.request.contextPath}/admin/products/search?search=${search}&page=${i}">${i}</a>
											</li>
										</c:forEach>

										<!-- Next button -->
										<c:if test="${page < totalPages}">
											<li><a
												href="${pageContext.request.contextPath}/admin/products/search?search=${search}&page=${page+1}">Next</a></li>
										</c:if>
									</ul>
								</c:when>
								<c:otherwise>
									<ul class="pagination" style="float: right;">
										<!-- Prev button -->
										<c:if test="${page > 1}">
											<li><a
												href="${pageContext.request.contextPath}/admin/products?page=${page-1}">Prev</a></li>
										</c:if>

										<!-- Page Numbers -->
										<c:forEach var="i" begin="1" end="${totalPages}">
											<li class="${page == i ? 'active' : ''}"><a
												href="${pageContext.request.contextPath}/admin/products?page=${i}">${i}</a>
											</li>
										</c:forEach>

										<!-- Next button -->
										<c:if test="${page < totalPages}">
											<li><a
												href="${pageContext.request.contextPath}/admin/products?page=${page+1}">Next</a></li>
										</c:if>
									</ul>
								</c:otherwise>
							</c:choose>
						</div>
						<!-- END LEFT SIDEBAR -->
					</div>
					<!-- END RIGHT SIDEBAR -->
				</div>
			</form>
		</div>
	</div>
	<!-- END CONTENT -->
</div>
<!-- END SIDEBAR & CONTENT -->