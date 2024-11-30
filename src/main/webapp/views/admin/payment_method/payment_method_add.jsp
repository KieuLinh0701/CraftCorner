<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
    <h1 class="text-center mt-5">Thêm Mới Phương Thức Thanh Toán</h1>

    <!-- Form thêm phương thức thanh toán -->
    <form action="${pageContext.request.contextPath}/admin/payment-method/add" method="post"  enctype="multipart/form-data">
        <div class="form-group">
            <label for="bankName">Tên Phương Thức(Ngân Hàng)</label>
            <input type="text" class="form-control" id="bankName" name="bankName" required>
        </div>

        <div class="form-group">
            <label for="accountNumber">Số Tài Khoản</label>
            <input type="text" class="form-control" id="accountNumber" name="accountNumber" required>
        </div>

        <div class="form-group">
            <label for="accountOwner">Chủ Tài Khoản</label>
            <input type="text" class="form-control" id="accountOwner" name="accountOwner" required>
        </div>

        <div class="form-group">
            <label for="status">Trạng Thái</label>
            <select class="form-control" id="status" name="status" required>
                <option value="1">Hoạt động</option>
                <option value="0">Không hoạt động</option>
            </select>
        </div>

        <div class="form-group">
            <label for="image">QR code</label>
            <input type="file" class="form-control" id="image" name="image" accept="image/*">
        </div>

        <button type="submit" class="btn btn-primary">Thêm</button>
        <a href="${pageContext.request.contextPath}/admin/payment-method" class="btn btn-default">Quay lại</a>
    </form>
</div>