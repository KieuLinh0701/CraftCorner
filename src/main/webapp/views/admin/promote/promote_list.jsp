<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
    <h1 class="text-center mt-5">Danh Sách Voucher Khuyến Mãi</h1>

    <!-- Form tìm kiếm theo phần trăm giảm giá -->
    <form action="promote" method="get" class="mb-4">
        <div class="form-group row">
            <label for="percent" class="col-sm-2 col-form-label">Tìm kiếm:</label>
            <div class="col-sm-7">
                <input type="number" class="form-control" id="percent" name="percent" placeholder="Nhập % giảm giá" min="0" max="100">
            </div>
            <div class="col-sm-3 text-end">
                <button type="submit" class="btn btn-primary">Tìm kiếm</button>
            </div>
        </div>
    </form>

    <!-- Nút thêm mới voucher -->
    <a href="promote/add" class="btn btn-success" style="margin-bottom: 10px">Thêm Mới</a>

    <!-- Bảng danh sách voucher -->
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Mã Voucher</th>
            <th>Thời Gian Bắt Đầu</th>
            <th>Thời Gian Kết Thúc</th>
            <th>% Giảm Giá</th>
            <th>Đơn Tối Thiểu</th>
            <th>SL phát hành</th>
            <th>SL đã sử dụng</th>
            <th>Hành động</th>
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
                    <a href="promote/edit?id=${promote.id}" class="btn btn-primary">Sửa</a>
                    <a href="promote/delete?id=${promote.id}" class="btn btn-danger" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
