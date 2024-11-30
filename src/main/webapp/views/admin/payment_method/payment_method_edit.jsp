<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
    <h1 class="text-center mt-5">Chỉnh sửa phương thức thanh toán</h1>
    <form action="${pageContext.request.contextPath}/admin/payment-method/edit" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${paymentMethod.id}">

        <div class="form-group">
            <label for="bankName">Tên phương thức(Ngân hàng):</label>
            <input type="text" class="form-control" id="bankName" name="bankName" value="${paymentMethod.bankName}" required>
        </div>

        <div class="form-group">
            <label for="accountNumber">Số tài khoản:</label>
            <input type="text" class="form-control" id="accountNumber" name="accountNumber" value="${paymentMethod.accountNumber}" required>
        </div>

        <div class="form-group">
            <label for="accountOwner">Chủ tài khoản:</label>
            <input type="text" class="form-control" id="accountOwner" name="accountOwner" value="${paymentMethod.accountOwner}" required>
        </div>

        <div class="form-group">
            <label for="status">Trạng thái:</label>
            <select class="form-control" id="status" name="status">
                <option value="1" ${paymentMethod.status == 1 ? "selected" : ""}>Hoạt động</option>
                <option value="0" ${paymentMethod.status == 0 ? "selected" : ""}>Không hoạt động</option>
            </select>
        </div>

        <div class="form-group">
            <label for="image">QR Code:</label>
            <input type="file" class="form-control" id="image" name="image" accept="image/*">

            <c:if test="${not empty paymentMethod.image}">
                <div class="mt-2">
                    <img src="${pageContext.request.contextPath}/image?fname=${paymentMethod.image}"
                         alt="Current QR Code" style="width: 100px; height: 100px;">
                </div>
            </c:if>
        </div>

        <button type="submit" class="btn btn-primary">Cập nhật</button>
        <a href="${pageContext.request.contextPath}/admin/payment-method" class="btn btn-secondary">Hủy</a>
    </form>
</div>