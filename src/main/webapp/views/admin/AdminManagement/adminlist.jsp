<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a class="btn btn-default add2cart" href="${pageContext.request.contextPath }/views/admin/AdminManagement/adminadd.jsp">Add
	Category</a>

<div class="col-md-12 col-sm-12">
	<h1>Shopping cart</h1>
	<div class="goods-page">
		<div class="goods-data clearfix">
			<div class="table-wrapper-responsive">
				<table>
					<tr>
						<th class="goods-page-image">ID</th>
						<th class="goods-page-description">Name</th>
						<th class="goods-page-ref-no">Image</th>
						<th class="goods-page-description">Status</th>
						<th class="goods-page-description">Create Date</th>
						<th class="goods-page-description">Phone</th>
						<th class="goods-page-description">Action</th>
					</tr>
					<!-- controller truyền vào items -->
					<c:forEach items="${listAdmins}" var="ad" varStatus="STT">
					<td class="goods-page-description">${ad.id}</td>
					<td>${ad.fullname}</td>
					<td class="goods-page-image"><c:choose>
									<c:when test="${ad.image.startsWith('https')}">
										<!-- cate.images: lấy từ lớp Category -->
										<c:set var="imgUrl" value="${ad.image}" />
									</c:when>
									<c:otherwise>
										<!-- /image: truyền đến controller image với tham số truy vấn fname yêu cầu GET -->
										<c:url var="imgUrl" value="/image?fname=${ad.image}" />
									</c:otherwise>
								</c:choose> <img height="150" width="200" src="${imgUrl}" /></td>
								
					<td class="goods-page-description">${ad.status}</td>
					<td class="goods-page-description">${ad.createDate}</td>
					<td class="goods-page-description">${ad.phone}</td>
					<td><a
								href="<c:url value='/admin/adminsmanage/edit?id=${ad.id}'/>"
								class="center">Sửa</a> | <a
								href="<c:url value='/admin/adminsmanage/delete?id=${ad.id}'/>"
								class="center">Xóa</a></td>
					
							
							<%-- <td><c:if test="${cate.roleId == 2 }">
									<span> Hoạt động </span>
								</c:if> <c:if test="${cate.roleId != 2 }">
									<span> Khóa </span>
								</c:if></td> --%>
							
								
						</tr>
					</c:forEach>
				</table>
			</div>
			
		</div>
	</div>
</div>