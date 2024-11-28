<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a class="btn btn-default add2cart" href="${pageContext.request.contextPath }/admin/orders/add">Add
	Category</a>

<div class="col-md-12 col-sm-12">
	<h1>Shopping cart</h1>
	<div class="goods-page">
		<div class="goods-data clearfix">
			<div class="table-wrapper-responsive">
				<table>
					<tr>
						<th class="goods-page-image">Order ID</th>
						<th class="goods-page-description">Customer ID</th>
						<th class="goods-page-ref-no">Order Date</th>
						<th class="goods-page-description">Note</th>
						
					</tr>
					<!-- controller truyền vào items -->
					<c:forEach items="${listcate}" var="cate" varStatus="STT">
						<tr>
							<!-- 	chỉ số hiện tại của vòng lặp: index bắt đầu từ 0 -->
							<td class="goods-page-description">${STT.index + 1}</td>
							<td class="goods-page-image"><c:choose>
									<c:when test="${cate.images.startsWith('https')}">
										<!-- cate.images: lấy từ lớp Category -->
										<c:set var="imgUrl" value="${cate.images}" />
									</c:when>
									<c:otherwise>
										<!-- /image: truyền đến controller image với tham số truy vấn fname yêu cầu GET -->
										<c:url var="imgUrl" value="/image?fname=${cate.images}" />
									</c:otherwise>
								</c:choose> <img height="150" width="200" src="${imgUrl}" /></td>
							<td class="goods-page-description">${cate.categoryId}</td>
							<td>${cate.categoryname}</td>
							<td><c:if test="${cate.status == 1 }">
									<span> Hoạt động </span>
								</c:if> <c:if test="${cate.status != 1 }">
									<span> Khóa </span>
								</c:if></td>
							<td><a
								href="<c:url value='/admin/category/edit?id=${cate.categoryId}'/>"
								class="center">Sửa</a> | <a
								href="<c:url value='/admin/category/delete?id=${cate.categoryId}'/>"
								class="center">Xóa</a></td>
								
						</tr>
					</c:forEach>
				</table>
			</div>
			
		</div>
	</div>
</div>