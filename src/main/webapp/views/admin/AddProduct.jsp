<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Product</title>
    <style>
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
            width: 20%;
            vertical-align: top;
        }
        .input-container {
            display: inline-block;
            width: 75%;
        }
        .form-control {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }
        textarea.form-control {
            height: 100px;
            resize: vertical;
        }
        label, .input-container {
            display: inline-block;
        }
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
                        <form action="${pageContext.request.contextPath}/admin/warehouse" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="action" value="insert">

                            <!-- Name -->
                            <div class="form-group">
                                <label class="control-label">Name:</label>
                                <div class="input-container">
                                    <input type="text" class="form-control" name="product[name]" placeholder="Enter product name" required>
                                </div>
                            </div>

                            <!-- Description -->
                            <div class="form-group">
                                <label class="control-label">Description:</label>
                                <div class="input-container">
                                    <textarea class="form-control" name="product[description]" placeholder="Enter product description" required></textarea>
                                </div>
                            </div>

                            <!-- Quantity -->
                            <div class="form-group">
                                <label class="control-label">Quantity:</label>
                                <div class="input-container">
                                    <input type="number" class="form-control" name="product[quantity]" placeholder="Enter quantity" required>
                                </div>
                            </div>

                            <!-- Price -->
                            <div class="form-group">
                                <label class="control-label">Price:</label>
                                <div class="input-container">
                                    <input type="number" class="form-control" name="product[price]" placeholder="Enter price" required>
                                </div>
                            </div>

                            <!-- Image Upload -->
                            <div class="form-group">
                                <label class="control-label">Image:</label>
                                <div class="input-container">
                                    <input type="file" class="form-control" name="product[image]" required>
                                </div>
                            </div>

                            <!-- Color -->
                            <div class="form-group">
                                <label class="control-label">Color:</label>
                                <div class="input-container">
                                    <input type="text" class="form-control" name="product[color]" placeholder="Enter color">
                                </div>
                            </div>

                            <!-- Material -->
                            <div class="form-group">
                                <label class="control-label">Material:</label>
                                <div class="input-container">
                                    <input type="text" class="form-control" name="product[material]" placeholder="Enter material">
                                </div>
                            </div>

                            <!-- Height -->
                            <div class="form-group">
                                <label class="control-label">Height:</label>
                                <div class="input-container">
                                    <input type="number" class="form-control" name="product[height]" placeholder="Enter height" required>
                                </div>
                            </div>

                            <!-- Length -->
                            <div class="form-group">
                                <label class="control-label">Length:</label>
                                <div class="input-container">
                                    <input type="number" class="form-control" name="product[length]" placeholder="Enter length" required>
                                </div>
                            </div>

                            <!-- Width -->
                            <div class="form-group">
                                <label class="control-label">Width:</label>
                                <div class="input-container">
                                    <input type="number" class="form-control" name="product[width]" placeholder="Enter width" required>
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
                                    <input type="date" class="form-control" name="product[createDate]" required>
                                </div>
                            </div>

                            <!-- Category -->
                            <div class="form-group">
                                <label class="control-label">Category:</label>
                                <div class="input-container">
                                    <input type="text" class="form-control" name="product[category]" placeholder="Enter category" required>
                                </div>
                            </div>

                            <!-- Buttons -->
                            <div class="form-group btn-group">
                                <button type="submit" class="btn btn-primary">Save</button>
                                <button type="button" class="btn btn-default" onclick="history.back()">Back</button>
                            </div>
                        </form>
                    </div> <!-- End form-body -->
                </div> <!-- End tab-pane -->
            </div> <!-- End tab-content -->
        </div> <!-- End tabbable -->
    </div> <!-- End portlet-body -->
</body>
</html>
