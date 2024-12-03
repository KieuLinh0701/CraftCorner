<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
    <h1 class="text-center mt-5">Add New Promotion Voucher</h1>

    <!-- Voucher addition form -->
    <form action="${pageContext.request.contextPath}/admin/promote/add" method="post">
        <div class="form-group">
            <label for="voucherCode">Voucher(Code)</label>
            <input type="text" class="form-control" id="voucherCode" name="voucherCode" required>
        </div>

        <div class="form-group">
            <label for="startDate">Start Date</label>
            <input type="datetime-local" class="form-control" id="startDate" name="startDate" required>
        </div>

        <div class="form-group">
            <label for="endDate">End Date</label>
            <input type="datetime-local" class="form-control" id="endDate" name="endDate" required>
        </div>

        <div class="form-group">
            <label for="discountPercent">Discount(%)</label>
            <input type="number" class="form-control" id="discountPercent" name="discountPercent" required>
        </div>

        <div class="form-group">
            <label for="minOrderTotal">Minimum Order Total</label>
            <input type="number" class="form-control" id="minOrderTotal" name="minOrderTotal" required>
        </div>

        <div class="form-group">
            <label for="quantity">Voucher Quantity</label>
            <input type="number" class="form-control" id="quantity" name="quantity" required>
        </div>

        <button type="submit" class="btn btn-primary">Add</button>
        <a href="${pageContext.request.contextPath}/admin/promote" class="btn btn-default">Back</a>
    </form>
</div>
