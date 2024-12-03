<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagination Example</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .pagination {
            display: flex;
            align-items: center;
        }
        .page-input {
            width: 30px;  /* Thu nhỏ ô nhập */
            height: 30px;
            text-align: center;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-right: 10px;
        }
        .btn {
            border: none;
            background: none;
            cursor: pointer;
            padding: 5px 10px;
            margin: 0 5px;
            font-size: 18px;
        }
        .select-view {
            margin-left: 10px;
        }
        .records-info {
            margin-left: 10px;
        }
    </style>
</head>
<body>	
<div class="pagination">
    <span>Page</span>
    <input type="text" class="page-input" value="1" id="pageInput"/>
    <span>of</span>
    <span id="totalPages">1</span>
    
    <button class="btn" onclick="changePage(-1)">&#9668;</button>
    <button class="btn" onclick="changePage(1)">&#9658;</button>

    <span class="records-info">View</span>
    <select class="select-view" onchange="changePageSize(this)">
        <option value="10">10</option>
        <option value="20">20</option>
        <option value="50">50</option>
    </select>
    <span class="records-info">records</span>
</div>
    <script>
        function changePage(step) {
            const pageInput = document.getElementById('pageInput');
            const totalPages = parseInt(document.getElementById('totalPages').innerText);
            let currentPage = parseInt(pageInput.value);
            
            // Tính toán trang mới
            let newPage = currentPage + step;
            if (newPage < 1) newPage = 1;
            if (newPage > totalPages) newPage = totalPages;
            
            // Cập nhật giá trị mới vào ô input
            pageInput.value = newPage;
        }
    </script>

<div class="row">
    <div class="col-md-12 col-sm-12">
        <div class="portlet grey-cascade box">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-cogs"></i> List Product
                </div>
                <div class="actions">
                    <a href="<%= request.getContextPath() %>/views/admin/AddProduct.jsp" class="btn btn-default btn-sm">
					    <i class="fa fa-plus"></i> Add New Product
					</a>
                </div>
            </div>
            <div class="portlet-body">
                <div class="table-responsive">
                    <table class="table table-hover table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>product_id</th>
                                <th>name</th>
                                <th>price</th>
                                <th>image</th>
                                <th>createDate</th>
                                <th>category</th>
                                <th>action</th> <!-- Cột mới -->
                            </tr>
                        </thead>
		                <tbody>
		                <!-- Dữ liệu sản phẩm sẽ được lặp qua và hiển thị ở đây -->
		                <c:forEach var="product" items="${products}">
		                    <tr>
		                        <td>${product.product_id}</td>
		                        <td>${product.name}</td>
		                        <td>${product.price}</td>
		                        <td>${product.image}</td>
		                        <td>${product.createDate}</td>
		                        <td>${product.category}</td>
		                        <td>
		                            <button class="btn btn-success btn-sm" onclick="changeQuantity(this, 1)">+</button>
		                            <input type="text" class="form-control input-sm quantity-input" value="${product.quantity}" style="width: 60px; display: inline-block;" oninput="validateQuantity(this)">
		                            <button class="btn btn-danger btn-sm" onclick="changeQuantity(this, -1)">-</button>
		                            <button class="btn btn-danger btn-sm" onclick="deleteProduct(${product.product_id})">Delete</button>
		                        </td>
		                    </tr>
		                </c:forEach>
		                </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
	let currentPage = 1;
	let pageSize = 10; // Default page size
	
	function changePage(step) {
	    const pageInput = document.getElementById('pageInput');
	    const totalPages = parseInt(document.getElementById('totalPages').innerText);
	    let newPage = currentPage + step;
	    if (newPage < 1) newPage = 1;
	    if (newPage > totalPages) newPage = totalPages;
	    pageInput.value = newPage;
	    currentPage = newPage;
	
	    loadProducts(); // Gọi lại để cập nhật sản phẩm theo trang mới
	}
	
	function changePageSize(select) {
	    pageSize = parseInt(select.value);
	    currentPage = 1; // Khi thay đổi số sản phẩm trên trang, quay lại trang đầu tiên
	    loadProducts(); // Gọi lại để cập nhật sản phẩm theo số lượng trang mới
	}
	
	function loadProducts() {
	    const offset = (currentPage - 1) * pageSize;
	    // Giả sử gọi API hoặc backend để lấy dữ liệu sản phẩm
	    fetch(`/api/products?page=${currentPage}&size=${pageSize}`)
	        .then(response => response.json())
	        .then(data => {
	            // Hiển thị các sản phẩm
	            displayProducts(data.products);
	
	            // Tính toán và cập nhật tổng số trang
	            const totalCount = data.totalCount;
	            const totalPages = Math.floor(totalCount / pageSize);
	            if (totalCount % pageSize !== 0) {
	                totalPages += 1;
	            }
	            document.getElementById('totalPages').textContent = totalPages;
	        })
	        .catch(error => console.error('Error fetching products:', error));
	}
	
	function displayProducts(products) {
	    const productList = document.querySelector('tbody');
	    productList.innerHTML = ''; // Xóa danh sách cũ
	
	    products.forEach(product => {
	        const productRow = document.createElement('tr');
	        productRow.innerHTML = `
	            <td>${product.product_id}</td>
	            <td>${product.name}</td>
	            <td>${product.price}</td>
	            <td>${product.image}</td>
	            <td>${product.createDate}</td>
	            <td>${product.category}</td>
	            <td>
	                <button class="btn btn-success btn-sm" onclick="changeQuantity(this, 1)">+</button>
	                <input type="text" class="form-control input-sm quantity-input" value="${product.quantity}" style="width: 60px; display: inline-block;" oninput="validateQuantity(this)">
	                <button class="btn btn-danger btn-sm" onclick="changeQuantity(this, -1)">-</button>
	                <button class="btn btn-danger btn-sm" onclick="deleteProduct(${product.product_id})">Delete</button>
	            </td>
	        `;
	        productList.appendChild(productRow);
	    });
	}
    function changeQuantity(button, delta) {
        const input = button.parentNode.querySelector('.quantity-input');
        let currentQuantity = parseInt(input.value) || 1; // Mặc định là 1 nếu giá trị không hợp lệ
        currentQuantity += delta;
        if (currentQuantity < 1) currentQuantity = 1; // Đảm bảo số lượng không nhỏ hơn 1
        input.value = currentQuantity;

        const productId = button.closest('tr').querySelector('td:first-child').innerText; // Lấy ID sản phẩm từ cột đầu tiên của dòng
        updateProductQuantity(productId, currentQuantity); // Gọi hàm cập nhật số lượng
    }

    function updateProductQuantity(productId, currentQuantity) {
        // Gửi yêu cầu AJAX để cập nhật số lượng
        fetch(`/admin/warehouse?action=updatequantity&id=${productId}&quantity=${currentQuantity}`, {
            method: 'POST',
        }).then(response => {
            if (response.ok) {
                alert('Quantity updated successfully');
            } else {
                alert('Failed to update quantity' + response.status);
            }
        }).catch(error => {
            console.error('Error:', error);
            alert('An error occurred while updating the quantity');
        });
    }
    function deleteProduct(productId) {
        // Gửi yêu cầu AJAX để xóa sản phẩm
        fetch(`/admin/warehouse?action=delete&id=${productId}`, {
            method: 'POST',
        }).then(response => {
            if (response.ok) {
                alert('Product deleted successfully');
                location.reload();  // Tải lại trang để cập nhật danh sách sản phẩm
            } else {
                alert('Failed to delete product: ' + response.status);
            }
        }).catch(error => {
            console.error('Error:', error);
            alert('An error occurred while deleting the product');
        });
    }
    function validateQuantity(input) {
        input.value = input.value.replace(/[^0-9]/g, ''); // Chỉ cho phép nhập số
        if (input.value === '' || parseInt(input.value) < 1) {
            input.value = 1; // Ngăn người dùng nhập số nhỏ hơn 1 hoặc để trống
        }
    }
    loadProducts();
</script>




</body>
</html>