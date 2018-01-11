<%@ page import="com.andersen.spring.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.andersen.spring.entity.UserAccount" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <% List<UserAccount> accountList = (List<UserAccount>) request.getAttribute("accountList"); %>

    <table>
        <% if (accountList != null) {
            for (UserAccount account : accountList) {
        %>
        <tr>
            <td>id: <%=account.getId()%>
            </td>
            <td>User: <%=account.getUser()%>
            </td>
            <td>Account number: <%=account.getAccountsNumber()%>
            </td>
            <td>Amount: <%=account.getAmount()%>
            </td>
        </tr>
        <%
                }
            }
        %>

    </table>

    <p><a href="/application/index">На главную</a></p>
</head>
<body>

</body>
</html>
