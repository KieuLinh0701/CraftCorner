<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ page import="java.util.List" %>
<%@ page import="vn.iotstar.entity.Product" %>

<html>
<head>
    <title>All Products</title>
</head>
<body>
    <h1>All Products</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Image</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.price}</td>
                    <td><img src="${product.image}" alt="${product.name}" width="100" height="100" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
 