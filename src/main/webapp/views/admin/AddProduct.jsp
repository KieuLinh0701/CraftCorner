<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Product</title>
    <style>
        /* Đảm bảo margin-top và margin-bottom */
        .form-group {
            margin-top: 30px;
            margin-bottom: 30px;
        }

        .form-body {
            max-width: 800px;
            margin: auto;
        }

        .control-label {
            font-weight: bold;
            display: inline-block;
            width: 20%; /* Chiều rộng cho label */
            vertical-align: top;
        }

        .input-container {
            display: inline-block;
            width: 75%; /* Chiều rộng cho input */
        }

        /* Cải thiện việc nhập liệu trong ô input */
        .form-control {
            width: 100%;
            padding: 10px;
            box-sizing: border-box; /* Đảm bảo padding không làm tăng kích thước ô */
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px; /* Đảm bảo chữ dễ đọc */
        }

        /* Đảm bảo ô textarea đủ không gian */
        textarea.form-control {
            height: 100px;
            resize: vertical; /* Cho phép người dùng thay đổi kích thước textarea theo chiều dọc */
        }

        /* Đảm bảo form không bị che khuất */
        label, .input-container {
            display: inline-block;
        }

        /* Các nút */
        .btn-group {
            margin-top: 30px;
            text-align: right;
        }
    </style>
</head>
<body>
    <div class="portlet-body">
        <div class="tabbable">
            <div class="tab-content no-space">
                <div class="tab-pane active" id="tab_general">
                    <div class="form-body">
                        <!-- Product ID -->
                        <div class="form-group">
                            <label class="control-label">Product ID:</label>
                            <div class="input-container">
                                <input type="text" class="form-control" name="product[product_id]" placeholder="">
                            </div>
                        </div>

                        <!-- Name -->
                        <div class="form-group">
                            <label class="control-label">Name:</label>
                            <div class="input-container">
                                <input type="text" class="form-control" name="product[name]" placeholder="">
                            </div>
                        </div>

                        <!-- Description -->
                        <div class="form-group">
                            <label class="control-label">Description:</label>
                            <div class="input-container">
                                <textarea class="form-control" name="product[description]"></textarea>
                            </div>
                        </div>

                        <!-- Quantity -->
                        <div class="form-group">
                            <label class="control-label">Quantity:</label>
                            <div class="input-container">
                                <input type="number" class="form-control" name="product[quantity]" placeholder="">
                            </div>
                        </div>

                        <!-- Price -->
                        <div class="form-group">
                            <label class="control-label">Price:</label>
                            <div class="input-container">
                                <input type="text" class="form-control" name="product[price]" placeholder="">
                            </div>
                        </div>

                        <!-- Image Upload -->
                        <div class="form-group">
                            <label class="control-label">Image:</label>
                            <div class="input-container">
                                <input type="file" class="form-control" name="product[image]">
                            </div>
                        </div>

                        <!-- Status -->
                        <div class="form-group">
                            <label class="control-label">Status:</label>
                            <div class="input-container">
                                <select class="form-control" name="product[status]">
                                    <option value="1">Published</option>
                                    <option value="0">Not Published</option>
                                </select>
                            </div>
                        </div>

                        <!-- Create Date -->
                        <div class="form-group">
                            <label class="control-label">Create Date:</label>
                            <div class="input-container">
                                <input type="date" class="form-control" name="product[createDate]">
                            </div>
                        </div>

                        <!-- Color -->
                        <div class="form-group">
                            <label class="control-label">Color:</label>
                            <div class="input-container">
                                <input type="text" class="form-control" name="product[color]" placeholder="">
                            </div>
                        </div>

                        <!-- Material -->
                        <div class="form-group">
                            <label class="control-label">Material:</label>
                            <div class="input-container">
                                <input type="text" class="form-control" name="product[material]" placeholder="">
                            </div>
                        </div>

                        <!-- Dimensions -->
                        <div class="form-group">
                            <label class="control-label">Height:</label>
                            <div class="input-container">
                                <input type="text" class="form-control" name="product[height]" placeholder="">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label">Length:</label>
                            <div class="input-container">
                                <input type="text" class="form-control" name="product[length]" placeholder="">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label">Width:</label>
                            <div class="input-container">
                                <input type="text" class="form-control" name="product[width]" placeholder="">
                            </div>
                        </div>

                        <!-- Category (Dropdown and Input) -->
                        <div class="form-group">
                            <label class="control-label">Category:</label>
                            <div class="input-container">
                                <input type="text" class="form-control" name="product[category]" placeholder="Enter category">
                            </div>
                        </div>

                        <!-- Buttons -->
                        <div class="form-group btn-group">
                            <button type="submit" class="btn btn-primary">Save</button>
                            <button type="button" class="btn btn-default" onclick="history.back()">Back</button>
                        </div>

                    </div> <!-- End form-body -->
                </div> <!-- End tab-pane -->
            </div> <!-- End tab-content -->
        </div> <!-- End tabbable -->
    </div> <!-- End portlet-body -->
</body>
</html>
