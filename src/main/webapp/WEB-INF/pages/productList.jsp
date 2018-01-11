<%@ page import="com.andersen.spring.entity.Product" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <% List<Product> productList = (List<Product>) request.getAttribute("productList"); %>

    <table>
        <% for (Product product : productList) {
        %>
        <tr>
            <td>id: <%=product.getId()%>
            </td>
            <td>Title: <%=product.getTitle()%>
            </td>
            <td>Description: <%=product.getDescription()%>
            </td>
            <td>Owner: <%=product.getUser()%>
            </td>
            <td>Price: <%=product.getPrice()%>
            </td>
        </tr>
        <%
            } %>

    </table>

    <p><a href="/application/index">На главную</a></p>
</head>
<body>

</body>
</html>
