<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
    <h1 class="text-center mt-5">List of Promotional Vouchers</h1>

    <!-- Form tìm kiếm theo mã voucher hoặc % giảm giá -->
    <form action="promote" method="get" class="mb-4">
        <div class="form-group row align-items-center">
            <label for="voucherCode" class="col-sm-2 col-form-label text-right">Voucher(Code):</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="voucherCode" name="voucherCode" placeholder="Enter Voucher">
            </div>
            <label for="percent" class="col-sm-2 col-form-label text-right">Discount(%):</label>
            <div class="col-sm-3">
                <input type="number" class="form-control" id="percent" name="percent" placeholder="Enter %" min="0" max="100">
            </div>
            <div class="col-sm-2 text-end">
                <button type="submit" class="btn btn-primary">Find</button>
            </div>
        </div>
    </form>

    <!-- Nút thêm mới voucher -->
    <a href="promote/add" class="btn btn-success" style="margin-bottom: 10px">Add New</a>

    <!-- Bảng danh sách voucher -->
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Voucher(Code)</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Discount(%))</th>
            <th>Minimum Order Total</th>
            <th>Quantity</th>
            <th>Quantity Used</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <!-- Lặp qua danh sách voucher -->
        <c:forEach var="promote" items="${promotes}">
            <tr>
                <td>${promote.id}</td>
                <td>${promote.voucherCode}</td>
                <td>${promote.startDate}</td>
                <td>${promote.endDate}</td>
                <td class="text-right">${promote.discountPercent}</td>
                <td class="text-right">${promote.minOrderTotal}</td>
                <td class="text-right">${promote.quantity}</td>
                <td class="text-right">${promote.quantityUsed}</td>
                <td>
                    <a href="promote/edit?id=${promote.id}" class="btn btn-primary">Edit</a>
                    <a href="promote/delete?id=${promote.id}" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
