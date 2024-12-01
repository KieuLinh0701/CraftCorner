<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý danh mục</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .categories-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .table th, .table td {
            text-align: center;
            vertical-align: middle;
        }
        .btn {
            margin: 0 5px;
        }
    </style>
</head>
<body>
    <div class="container mt-4">
        <h2 class="text-center text-primary">Quản lý danh mục</h2>
        <div class="categories-header">
            <h4 class="text-secondary">Danh sách danh mục</h4>
            <a href="<c:url value='/admin/categories/create' />" class="btn btn-success">+ Tạo danh mục</a>
        </div>
        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>#</th>
                    <th>Tên danh mục</th>
                    <th>Số sản phẩm</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="category" items="${categories}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${category.name}</td>
                        <td>0 sản phẩm</td>
                        <td>
                            <a href="<c:url value='/admin/categories/edit?categoryId=${category.category_id}' />" class="btn btn-warning btn-sm">Sửa</a>
                            <a href="<c:url value='/admin/categories/delete?categoryId=${category.category_id}' />" 
                               class="btn btn-danger btn-sm" 
                               onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này?');">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
