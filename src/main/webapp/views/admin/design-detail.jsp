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
				<div class="col-md-6 col-sm-6">
						<h1>${design.title}</h1>
					</div>
				<div class="col-md-6 col-sm-6">
						<a href="${pageContext.request.contextPath}/admin/design/delete?id=${design.designId }" class="btn btn-primary"
							style="background: black; float: right;"> Delete <i class="fa fa-trash"></i> </a>
						<a href="${pageContext.request.contextPath}/admin/design/edit?id=${design.designId }" class="btn btn-primary"
							style="background: black; float: right; margin-right: 10px;"> Modify <i class="fa fa-gear"></i> </a>
					</div>
				<div class="content-page">
					<div class="row">
						<div class="col-md-8 col-sm-8 blog-item">
							<div class="blog-item-img">
								<div class="front-carousel">
									<c:if test="${design.image.substring(0,5) != 'https'}">
										<c:url value="/image?fname=${design.image}" var="imgUrl"></c:url>
									</c:if>
									<c:if test="${design.image.substring(0,5) == 'https'}">
										<c:url value="${design.image}" var="imgUrl"></c:url>
									</c:if>
									<img class="img-responsive" alt="${design.title}"
										src="${imgUrl}">
									<hr class="blog-post-sep">
									<p style="font-size:15px">${design.content }</p>
									<ul class="blog-info">
										<li><i class="fa fa-user"></i> ${design.user.fullname }</li>
										<li><i class="fa fa-calendar"></i> ${design.createDate }</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="col-md-4 col-sm-4 blog-item">
							<h2>Product</h2>
							<c:forEach var="item" items="${design.designItems}">
								<div class="row">
									<div class="col-md-4 col-sm-4">
										<c:if test="${item.product.image.substring(0,5) != 'https'}">
											<c:url value="/image?fname=${item.product.image}"
												var="imgUrl"></c:url>
										</c:if>
										<c:if test="${item.product.image.substring(0,5) == 'https'}">
											<c:url value="${item.product.image}" var="imgUrl"></c:url>
										</c:if>
										<img class="img-responsive" alt="${item.product.name}"
											src="${imgUrl}">
									</div>
									<div class="col-md-8 col-sm-8 Shopping cart">
										<h4>
											<a href="#">${item.product.name}</a>
										</h4>
										<p>Material: ${item.product.material} <br>
										Color: ${item.product.color}<br>
										Size: height: ${item.product.height} - length:
											${item.product.length} - width: ${item.product.width}<br>
										Price: ${item.product.price}</p>
									</div>
								</div>
								<hr style="margin: 5px 0;">
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>