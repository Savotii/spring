
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

    <table>
        <thead>
        <tr>
            <th>Account id</th>
            <th>User</th>
            <th>Account number</th>
            <th>Amount</th>
        </tr>
        </thead>
        <%--accountList приходит из контроллера--%>
        <c:forEach var="account" items="${accountList}">
            <tr>
                <td>${account.id}</td>
                <td>${account.user}</td>
                <td>${account.accountsNumber}</td>
                <td>${account.amount}</td>
            </tr>
        </c:forEach>
    </table>

    <p><a href="/application/index">На главную</a></p>
</head>
<body>

</body>
</html>
