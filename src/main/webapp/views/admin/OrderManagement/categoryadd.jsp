<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- pageContext: cung cấp quyền truy cập request, response
    request.contextPath: thuộc tính của đối tượng HttpRequest trả về đường dẫn ngữ cảnh của máy chủ -->
    <!-- Trường hợp này trả về /forgetPW_logout -->
    <!-- Thêm category - đưa đến insert ở controller -->
<div class="col-md-12 col-sm-12">
	<h1>ADD </h1>
	<div class="content-page">
		<div class="content-page">
				<form
					action="${pageContext.request.contextPath }/admin/category/insert"
					method="post" enctype="multipart/form-data">
					<div class="input-group">
					<label for="fname">Category Name</label><br> <input class="form-control"
						type="text" id="categoryname" name="categoryname"><br>
					</div>
					<div class="input-group">
					<label for="images">Images</label><br>
					<!-- Image element to display the selected file -->
					
					<input type="file"
						onchange="chooseFile(this)" id="images" name="images"><br></div>
						<img id="imagess" height="150" width="200" src="${imgUrl}" /> 
					<div class="input-group">
					<label for="lname">Status</label><br> <input class="form-control" type="text"
						id="status" name="status"><br> <br></div>
					<div class="form-actions">
						<button type="submit" class="btn blue">Submit</button>
					</div>
				</form>
		</div>
	</div>
</div>

