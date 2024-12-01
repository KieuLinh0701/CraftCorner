<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
<!-- Begin: life time stats -->
		<div class="portlet">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-gift"></i>Products
				</div>
				
			<div class="portlet-body">
				<div class="table-container">
					<table class="table table-striped table-bordered table-hover" id="datatable_products">
					<thead>
					<tr role="row" class="heading">
						<th width="10%">
							 ID
						</th>
						<th width="15%">
							 Product&nbsp;Name
						</th>
						<th width="10%">
							 Price
						</th>
						<th width="10%">
							 Quantity
						</th>
						<th width="10%">
							 Actions
						</th>
					</tr>
					<tr role="row" class="filter">
						<td>
							<input type="text" class="form-control form-filter input-sm" name="product_id">
						</td>
						<td>
							<input type="text" class="form-control form-filter input-sm" name="product_name">
						</td>
						<td>
							<div class="margin-bottom-5">
								<input type="text" class="form-control form-filter input-sm" name="product_price_from" placeholder="From"/>
							</div>
							<input type="text" class="form-control form-filter input-sm" name="product_price_to" placeholder="To"/>
						</td>
						<td>
							<div class="margin-bottom-5">
								<input type="text" class="form-control form-filter input-sm" name="product_quantity_from" placeholder="From"/>
							</div>
							<input type="text" class="form-control form-filter input-sm" name="product_quantity_to" placeholder="To"/>
						</td>
						<td>
							<div class="margin-bottom-5">
								<button class="btn btn-sm yellow filter-submit margin-bottom"><i class="fa fa-search"></i> Search</button>
							</div>
							<button class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i> Reset</button>
						</td>
					</tr>
					</thead>
					<tbody>
					</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- End: life time stats -->
		
	<div class="pagination">
        <span>Page</span>
        <input type="text" class="page-input" value="1" id="pageInput"/>
        <span>of</span>
        <span id="totalPages">10</span>
        
        <button class="btn" onclick="changePage(-1)">&#9668;</button>
        <button class="btn" onclick="changePage(1)">&#9658;</button>

        <span class="records-info">View</span>
        <select class="select-view">
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
                    <i class="fa fa-cogs"></i> Shopping Cart
                </div>
                <div class="actions">
                    <a href="#" class="btn btn-default btn-sm">
                        <i class="fa fa-plus"></i> Thêm sản phẩm
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
                            <tr>
                                <td>001</td>
                                <td>Product 1</td>
                                <td>345.50$</td>
                                <td><img src="image1.jpg" alt="Product 1" width="50"></td>
                                <td>2024-04-15</td>
                                <td>Electronics</td>
                                <td>
                                    <button class="btn btn-success btn-sm" onclick="changeQuantity(this, 1)">+</button>
                                    <input type="text" class="form-control input-sm quantity-input" value="2" style="width: 60px; display: inline-block;" oninput="validateQuantity(this)">
                                    <button class="btn btn-danger btn-sm" onclick="changeQuantity(this, -1)">-</button>
                                    <button class="btn btn-warning btn-sm">Delete</button>
                                </td>
                            </tr>
                            <!-- Thêm các dòng khác tương tự nếu cần -->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function changeQuantity(button, delta) {
        const input = button.parentNode.querySelector('.quantity-input');
        let currentQuantity = parseInt(input.value) || 1; // Mặc định là 1 nếu giá trị không hợp lệ
        currentQuantity += delta;
        if (currentQuantity < 1) currentQuantity = 1; // Đảm bảo số lượng không nhỏ hơn 1
        input.value = currentQuantity;
    }

    function validateQuantity(input) {
        input.value = input.value.replace(/[^0-9]/g, ''); // Chỉ cho phép nhập số
        if (input.value === '' || parseInt(input.value) < 1) {
            input.value = 1; // Ngăn người dùng nhập số nhỏ hơn 1 hoặc để trống
        }
    }
</script>



</body>
</html>