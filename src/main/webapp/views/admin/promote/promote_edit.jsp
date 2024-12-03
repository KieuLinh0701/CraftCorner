<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
    <h1 class="text-center mt-5">Edit Promotion Voucher</h1>

    <!-- Edit voucher form -->
    <form action="${pageContext.request.contextPath}/admin/promote/edit" method="post">
        <input type="hidden" name="id" value="${promote.id}">
        <div class="form-group">
            <label for="voucherCode">Voucher(Code)</label>
            <input type="text" class="form-control" id="voucherCode" name="voucherCode" value="${promote.voucherCode}" required>
        </div>

        <div class="form-group">
            <label for="startDate">Start Date</label>
            <input type="datetime-local" class="form-control" id="startDate" name="startDate" value="${promote.startDate}" required>
        </div>

        <div class="form-group">
            <label for="endDate">End Date</label>
            <input type="datetime-local" class="form-control" id="endDate" value="${promote.endDate}" name="endDate" required>
        </div>

        <div class="form-group">
            <label for="discountPercent">Discount(%)</label>
            <input type="number" class="form-control" id="discountPercent" value="${promote.discountPercent}" name="discountPercent" required>
        </div>

        <div class="form-group">
            <label for="minOrderTotal">Minimum Order Total</label>
            <input type="number" class="form-control" id="minOrderTotal" value="${promote.minOrderTotal}" name="minOrderTotal" required>
        </div>

        <div class="form-group">
            <label for="quantity">Voucher Quantity</label>
            <input type="number" class="form-control" id="quantity" value="${promote.quantity}" name="quantity" required>
        </div>

        <button type="submit" class="btn btn-primary">Update</button>
        <a href="${pageContext.request.contextPath}/admin/promote" class="btn btn-default">Back</a>
    </form>
</div>
