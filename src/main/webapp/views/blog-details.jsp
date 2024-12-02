<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
// Lấy tham số id từ URL
String idParam = request.getParameter("id");
StringBuilder htmlContent = new StringBuilder();

try {
	int blogId = Integer.parseInt(idParam);

	// Xác định đường dẫn tới tệp HTML dựa trên blogId
	String htmlFilePath = "/views/blogs/blog" + blogId + ".html";

	// Kiểm tra nếu tệp HTML tồn tại
	java.io.File file = new java.io.File(application.getRealPath(htmlFilePath));
	if (file.exists()) {
		// Đọc nội dung tệp HTML
		try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file))) {
	String line;
	while ((line = reader.readLine()) != null) {
		htmlContent.append(line);
	}
		}
	} else {
		// Nếu tệp không tồn tại, tạo nội dung fallback
		htmlContent.append("<h1>Blog không tồn tại</h1>");
		htmlContent.append("<p>Không tìm thấy bài viết phù hợp.</p>");
	}
} catch (NumberFormatException e) {
	htmlContent.append("<h1>ID không hợp lệ</h1>");
	htmlContent.append("<p>ID bài viết không phải là số hợp lệ.</p>");
} catch (Exception e) {
	htmlContent.append("<h1>Lỗi không xác định</h1>");
	htmlContent.append("<p>Đã xảy ra lỗi khi tải nội dung bài viết.</p>");
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


</head>
<body>

	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
		rel="stylesheet">
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
		rel="stylesheet">

	<!-- Nội dung blog -->
	<div class="container mt-5">

		<%
		// Hiển thị nội dung đã đọc từ tệp HTML hoặc fallback
		out.println(htmlContent.toString());
		%>
		<p class="text-muted">
			<strong>Người tạo:</strong> ${blog.user.fullname} <strong>Ngày
				đăng:</strong> ${blog.createdAt}
		</p>

	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/javascript/search-handler.js"></script>
</body>
</html>
