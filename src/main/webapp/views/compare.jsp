<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>So sánh sản phẩm</title>
</head>
<body>
    <h1>So sánh sản phẩm</h1>

    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>

    <table border="1">
        <thead>
            <tr>
                <th>Thuộc tính</th>
                <th>${product1.name}</th>
                <th>${product2.name}</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Giá</td>
                <td>${product1.price}</td>
                <td>${product2.price}</td>
            </tr>
            <tr>
                <td>Mô tả</td>
                <td>${product1.description}</td>
                <td>${product2.description}</td>
            </tr>
            <!-- Thêm các thuộc tính khác nếu cần -->
        </tbody>
    </table>

    <form action="add" method="post">
        <input type="hidden" name="productId1" value="${product1.id}">
        <label for="productId2">ID sản phẩm so sánh:</label>
        <input type="text" name="productId2" required>
        <button type="submit">So sánh</button>
    </form>
</body>
</html>