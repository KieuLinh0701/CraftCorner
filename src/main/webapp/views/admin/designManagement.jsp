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
				<h1>Sample interior design</h1>
				<table>
					<c:forEach var="design" items="${listDesign}">
						<div class="content-page">
							<div class="row">
								<!-- BEGIN LEFT SIDEBAR -->
								<div class="col-md-12 col-sm-12 blog-posts">
									<div class="row">
										<div class="col-md-4 col-sm-4">
											<c:if test="${design.image.substring(0,5) != 'https'}">
												<c:url value="/image?fname=${design.image}"
													var="imgUrl"></c:url>
											</c:if>
											<c:if test="${design.image.substring(0,5) == 'https'}">
												<c:url value="${design.image}" var="imgUrl"></c:url>
											</c:if>
											<img class="img-responsive" alt="${design.title}" src="${imgUrl}">
										</div>
										<div class="col-md-8 col-sm-8">
											<h2>
												<a href="#">${design.title}</a>
											</h2>
											<ul class="blog-info">
												<li><i class="fa fa-calendar"></i> [[#dates.format(${design.createDate}, 'dd/MM/yyyy')]] </li>
												<li><i class="fa fa-tags"></i> ${design.user.fullname} </li>
											</ul>
											<p>${design.content}</p>
											<a href="#" class="more">Read more <i
												class="icon-angle-right"></i></a>
										</div>
									</div>
									<hr class="blog-post-sep">
									<ul class="pagination">
										<li><a href="#">Prev</a></li>
										<li><a href="#">1</a></li>
										<li><a href="#">2</a></li>
										<li class="active"><a href="#">3</a></li>
										<li><a href="#">4</a></li>
										<li><a href="#">5</a></li>
										<li><a href="#">Next</a></li>
									</ul>
								</div>
								<!-- END LEFT SIDEBAR -->
							</div>
							<!-- END RIGHT SIDEBAR -->
						</div>
					</c:forEach>
				</table>
			</form>
		</div>
	</div>
	<!-- END CONTENT -->
</div>
<!-- END SIDEBAR & CONTENT -->
