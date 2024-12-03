<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
    <h1>Product List</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Product ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Status</th>
                <th>Image</th> <!-- Add an Image column -->
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.product_id}</td>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                    <td>${product.status == 1 ? 'Available' : 'Unavailable'}</td>
                    
                    <!-- Display product image -->
                    <td>
                        <c:choose>
                            <c:when test="${not empty product.image}">
                                <!-- Show image if it exists -->
                                <img src="${product.image}" alt="${product.name}" width="200" height="300" />
                            </c:when>
                            <c:otherwise>
                                <!-- Fallback image if no image is available -->
                                <img src="/images/default-product.jpg" alt="Default Product Image" width="100" height="100" />
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
