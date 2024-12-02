<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
    <h1 class="text-center mt-5">Thêm Mới Voucher Khuyến Mãi</h1>

    <!-- Form thêm voucher -->
    <form action="${pageContext.request.contextPath}/admin/promote/add" method="post">
        <div class="form-group">
            <label for="voucherCode">Mã Voucher</label>
            <input type="text" class="form-control" id="voucherCode" name="voucherCode" required>
        </div>

        <div class="form-group">
            <label for="startDate">Thời Gian Bắt Đầu</label>
            <input type="datetime-local" class="form-control" id="startDate" name="startDate" required>
        </div>

        <div class="form-group">
            <label for="endDate">Thời Gian Kết Thúc</label>
            <input type="datetime-local" class="form-control" id="endDate" name="endDate" required>
        </div>

        <div class="form-group">
            <label for="discountPercent">% Giảm Giá</label>
            <input type="number" class="form-control" id="discountPercent" name="discountPercent" required>
        </div>

        <div class="form-group">
            <label for="minOrderTotal">Đơn Tối Thiểu</label>
            <input type="number" class="form-control" id="minOrderTotal" name="minOrderTotal" required>
        </div>

        <div class="form-group">
            <label for="quantity">Số lượng voucher</label>
            <input type="number" class="form-control" id="quantity" name="quantity" required>
        </div>

        <button type="submit" class="btn btn-primary">Thêm</button>
        <a href="${pageContext.request.contextPath}/admin/promote" class="btn btn-default">Quay lại</a>
    </form>
</div>