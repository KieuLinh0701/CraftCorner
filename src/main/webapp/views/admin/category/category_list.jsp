<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Category Management</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
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
		<h2 class="text-center text-primary">Category Management</h2>
		<div class="categories-header">
			<h4 class="text-secondary">Category List</h4>
			<!-- Button to open modal for creating a category -->
			<a href="#" class="btn btn-success" data-toggle="modal"
				data-target="#createCategoryModal">+ Add Category</a>
		</div>
		<table class="table table-striped table-hover">
			<thead class="table-dark">
				<tr>
					<th>#</th>
					<th>Category Name</th>
					<th>Number of Products</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="category" items="${categories}" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td>${category.name}</td>
						<td><c:out value="${productCountMap[category.category_id]}" />
							products</td>
						<td>
							<!-- Button to edit, opens modal and passes data to modal --> <a
							href="javascript:void(0);" class="btn btn-warning"
							data-toggle="modal" data-target="#editCategoryModal"
							onclick="setCategoryData(${category.category_id}, '${category.name}')">Edit</a>

							<!-- Button to delete --> <a
							href="<c:url value='/admin/category/delete?category_id=${category.category_id}' />"
							class="btn btn-danger">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<!-- Modal to Create Category -->
	<div class="modal fade" id="createCategoryModal" tabindex="-1"
		role="dialog" aria-labelledby="createCategoryModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="createCategoryModalLabel">Create
						New Category</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="<c:url value='/admin/categories/insert' />"
					method="post">
					<div class="modal-body">
						<div class="form-group">
							<label for="categoryName">Category Name</label> <input
								type="text" class="form-control" id="categoryName"
								name="categoryName" required>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancel</button>
						<button type="submit" class="btn btn-primary">Create</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Modal to Edit Category -->
	<div class="modal fade" id="editCategoryModal" tabindex="-1"
		role="dialog" aria-labelledby="editCategoryModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="editCategoryModalLabel">Edit
						Category</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="<c:url value='/admin/category/update' />"
					method="post">
					<div class="modal-body">
						<input type="hidden" id="editCategoryId" name="categoryId" />
						<div class="form-group">
							<label for="editCategoryName">Category Name</label> <input
								type="text" class="form-control" id="editCategoryName"
								name="categoryName" required>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancel</button>
						<button type="submit" class="btn btn-primary">Save
							Changes</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script>
        // Function to populate modal with data
        function setCategoryData(categoryId, categoryName) {
            document.getElementById('editCategoryId').value = categoryId;
            document.getElementById('editCategoryName').value = categoryName;
        }
    </script>
</body>
</html>