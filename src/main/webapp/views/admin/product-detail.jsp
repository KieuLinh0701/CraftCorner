<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<div class="body">
	<div class="container">
		<!-- BEGIN SIDEBAR & CONTENT -->
		<div class="row margin-bottom-40">

			<!-- BEGIN CONTENT -->
			<div class="col-md-12 col-sm-12">
				<div class="product-page">
					<div class="row">
						<a
							href="${pageContext.request.contextPath}/admin/product/delete?id=${product.product_id }"
							class="btn btn-primary" style="background: black; float: right;">
							Delete <i class="fa fa-trash"></i>
						</a> <a
							href="${pageContext.request.contextPath}/admin/product/edit?id=${product.product_id }"
							class="btn btn-primary"
							style="background: black; float: right; margin-right: 10px;">
							Modify <i class="fa fa-gear"></i>
						</a> <br>
						<div class="col-md-6 col-sm-6">
							<div class="product-main-image">
								<c:if test="${product.image.substring(0,5) != 'https'}">
									<c:url value="/image?fname=${product.image}" var="imgUrl"></c:url>
								</c:if>
								<c:if test="${product.image.substring(0,5) == 'https'}">
									<c:url value="${product.image}" var="imgUrl"></c:url>
								</c:if>
								<img src="${imgUrl}" alt="Cool green dress with red bell"
									class="img-responsive" data-BigImgsrc="${imgUrl}">
							</div>
						</div>
						<div class="col-md-6 col-sm-6">
							<h1>${product.name}</h1>
							<div class="price-availability-block clearfix">
								<div class="price">
									<strong>${product.price} VND</strong>
								</div>

							</div>
							<div class="availability">
								<p>
									<strong>Quantity: </strong>${product.quantity}</p>
							</div>
							<div class="availability">
								<p>
									<strong>Status: </strong>
									<c:if test="${product.status == 0 }">private</c:if>
									<c:if test="${product.status == 1 }">public</c:if>
								</p>
							</div>
							<div class="description">
								<p>
									<strong>Review: </strong>${product.reviews.size()}</p>
							</div>
							<hr>
							<div class="description">
								<p>
									<strong>Description: </strong>${product.description}</p>
							</div>
						</div>

						<div class="product-page-content">
							<ul id="myTab" class="nav nav-tabs">
								<li><a href="#Description" data-toggle="tab">Description</a></li>
								<li><a href="#Information" data-toggle="tab">Information</a></li>
								<li class="active"><a href="#Reviews" data-toggle="tab">Reviews
										(${product.reviews.size()})</a></li>
							</ul>
							<div id="myTabContent" class="tab-content">
								<div class="tab-pane fade" id="Description">
									<p>${product.description}</p>
								</div>
								<div class="tab-pane fade" id="Information">
									<table class="datasheet">
										<tr>
											<th colspan="2">Additional features</th>
										</tr>
										<tr>
											<td class="datasheet-features-type">Category</td>
											<td>${product.category.name}</td>
										</tr>
										<tr>
											<td class="datasheet-features-type">Material</td>
											<td>${product.material}</td>
										</tr>
										<tr>
											<td class="datasheet-features-type">Color</td>
											<td>${product.color}</td>
										</tr>
										<tr>
											<td class="datasheet-features-type">Height</td>
											<td>${product.height}</td>
										</tr>
										<tr>
											<td class="datasheet-features-type">Width</td>
											<td>${product.width}</td>
										</tr>
										<tr>
											<td class="datasheet-features-type">Length</td>
											<td>${product.length}</td>
										</tr>
									</table>
								</div>
								<div class="tab-pane fade in active" id="Reviews">
									<c:if test="${!empty product.reviews}">
										<c:forEach var="review" items="${product.reviews}">
											<div class="review-item clearfix">
												<div class="review-item-submitted">
													<strong>${review.user.fullname}</strong> <em>${review.created_date}</em>
													<div class="rateit" data-rateit-value="5"
														data-rateit-ispreset="true" data-rateit-readonly="true"></div>
												</div>
												<div class="review-item-content">
													<p>${review.content}</p>
												</div>
											</div>
										</c:forEach>
									</c:if>
									<c:if test="${empty product.reviews}">
										<div class="review-item-content">
											<p>There are currently no reviews available for this
												product!</p>
										</div>
									</c:if>

								</div>
							</div>
						</div>

						<div class="sticker sticker-sale"></div>
					</div>
				</div>
			</div>
			<!-- END CONTENT -->
		</div>
		<!-- END SIDEBAR & CONTENT -->
	</div>
</div>