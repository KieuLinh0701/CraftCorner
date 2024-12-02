<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
            <!-- Nút mở modal tạo danh mục -->
            <a href="#" class="btn btn-success" data-toggle="modal" data-target="#createCategoryModal">+ Tạo danh mục</a>
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
                        <td><c:out value="${productCountMap[category.category_id]}" /> sản phẩm</td>
                        <td>
                            <!-- Nút sửa, mở modal và truyền dữ liệu vào modal -->
                            <a href="javascript:void(0);" class="btn btn-warning" data-toggle="modal" data-target="#editCategoryModal"
                               onclick="setCategoryData(${category.category_id}, '${category.name}')">Sửa</a>
                            |
                            <!-- Nút xóa -->
                            <a href="<c:url value='/admin/category/delete?category_id=${category.category_id}' />" class="btn btn-danger">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Modal Tạo danh mục -->
    <div class="modal fade" id="createCategoryModal" tabindex="-1" role="dialog" aria-labelledby="createCategoryModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="createCategoryModalLabel">Tạo danh mục mới</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="<c:url value='/admin/categories/insert' />" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="categoryName">Tên danh mục</label>
                            <input type="text" class="form-control" id="categoryName" name="categoryName" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                        <button type="submit" class="btn btn-primary">Tạo danh mục</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Modal Sửa danh mục -->
    <div class="modal fade" id="editCategoryModal" tabindex="-1" role="dialog" aria-labelledby="editCategoryModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editCategoryModalLabel">Sửa danh mục</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="<c:url value='/admin/category/update' />" method="post">
                    <div class="modal-body">
                        <input type="hidden" id="editCategoryId" name="categoryId" />
                        <div class="form-group">
                            <label for="editCategoryName">Tên danh mục</label>
                            <input type="text" class="form-control" id="editCategoryName" name="categoryName" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                        <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Hàm điền dữ liệu vào modal
        function setCategoryData(categoryId, categoryName) {
            document.getElementById('editCategoryId').value = categoryId;
            document.getElementById('editCategoryName').value = categoryName;
        }
    </script>
</body>
</html>
