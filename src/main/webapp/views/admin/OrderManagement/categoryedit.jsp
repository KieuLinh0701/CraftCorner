<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<form action="${pageContext.request.contextPath }/admin/category/update" method = "post" enctype="multipart/form-data" >
 
  <input type="text" id="categoryid" name="categoryid" value="${cate.categoryid }" hidden="hidden"><br>
  
  <label for="fname">Category Name</label><br>
  <input type="text" id="categoryname" name="categoryname" value="${cate.categoryname }"><br>
  <label for="images">Images</label><br> 
	
		<c:if test="${cate.images.substring(0,5) !='htttps'}">
			<c:url value="/image?fname=${cate.images}" var="imgUrl" />
		</c:if>
		<c:if test="${cate.images.substring(0,5) == 'https' }">
			<c:url value="${cate.images}" var="imgUrl"/>
		</c:if>
	 <!-- Image element to display the selected file -->
   <img id="imagess" height="150" width="200" src="${imgUrl}" /> 
 
   <!-- File input element -->
   <input type="file" onchange="chooseFile(this)" id="images" name="images" value="${cate.images }"><br>
  <label for="lname">Status</label><br>
  <input type="text" id="status" name="status" value="${cate.status }"><br>
  <br>
  <input type="submit" value="Submit">
</form>