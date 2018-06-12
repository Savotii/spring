
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
</head>
<body>

<table>
    <thead>
    <tr>
        <th>Product id</th>
        <th>Title</th>
        <th>Description</th>
        <th>Owner</th>
        <th>Price</th>
    </tr>
    </thead>
    <%--productList приходит из контроллера--%>
    <c:forEach var="product" items="${productList}">
        <tr>
            <td>${product.id}</td>
            <td>${product.title}</td>
            <td>${product.description}</td>
            <td>${product.user}</td>
            <td>${product.price}</td>
        </tr>
    </c:forEach>
</table>

<p><a href="/index">На главную</a></p>
</body>
</html>
