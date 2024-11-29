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
			<form action="#">
				<div class="input-group content-search-view2">
					<input type="text" class="form-control" placeholder="Search...">
					<span class="input-group-btn">
						<button type="submit" class="btn btn-primary"
							style="background: black">Search</button>
					</span>
				</div>
				<c:if test="${alert != null}">
					<h3 class="alert alertdanger">${alert}</h3>
				</c:if>
				<c:if test="${alert == null}">
					<div class="col-md-6 col-sm-6">
						<h1>Sample interior design</h1>
					</div>
					<div class="col-md-6 col-sm-6">
						<a href="#" class="btn btn-primary"
							style="background: black; float: right;"> New design <i class="fa fa-plus"></i> </a>
					</div>
				</c:if>
				<div class="content-page">
					<div class="row">
						<hr class="blog-post-sep">
						<!-- BEGIN LEFT SIDEBAR -->
						<div class="col-md-12 col-sm-12 blog-posts">
							<c:forEach var="design" items="${listDesign}">
								<div class="row">
									<div class="col-md-4 col-sm-4">
										<c:if test="${design.image.substring(0,5) != 'https'}">
											<c:url value="/image?fname=${design.image}" var="imgUrl"></c:url>
										</c:if>
										<c:if test="${design.image.substring(0,5) == 'https'}">
											<c:url value="${design.image}" var="imgUrl"></c:url>
										</c:if>
										<img class="img-responsive" alt="${design.title}"
											src="${imgUrl}">
									</div>
									<div class="col-md-8 col-sm-8">
										<h2>
											<a href="${pageContext.request.contextPath}/admin/designdetail?id=${design.designId}">${design.title}</a>
										</h2>
										<ul class="blog-info">
											<li><i class="fa fa-calendar"></i> ${design.createDate}</li>
											<li><i class="fa fa-user"></i> ${design.user.fullname}</li>
											<li><i class="fa fa-power-off"></i> 
												<c:if test="${design.status == 0}">
													private
												</c:if>
												<c:if test="${design.status == 1}">
													public
												</c:if>	
											</li>
										</ul>
										<p>${design.content}</p>
										<a href="#" class="more">Read more <i
											class="icon-angle-right"></i></a>
									</div>
								</div>
								<hr class="blog-post-sep">
							</c:forEach>
							<c:if test="${alert == null}">
								<ul class="pagination">
									<li><a href="#">Prev</a></li>
									<li><a href="#">1</a></li>
									<li><a href="#">2</a></li>
									<li class="active"><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">5</a></li>
									<li><a href="#">Next</a></li>
								</ul>
							</c:if>
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
