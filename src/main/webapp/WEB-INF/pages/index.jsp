<%@ page import="com.andersen.spring.entity.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%

    String autorizationFiled = (String) request.getAttribute("userNotFound");

/*    Object logout = request.getAttribute("logout");
    if (logout != null)
        try {
            session.removeAttribute("user");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    Object user = session.getAttribute("user");
    if (user == null) {
%>
<%--
<!-- My var.
<form action="/application/login" method="post">
    <input type="text" placeholder="Введите логин" name="login">
    <input type="text" placeholder="Введите email" name="email">
    <input type="submit" name="submit" value="Выполнить авторизацию">
</form>
--%>

<a href="/application/login">Войти в систему</a><br />
<% if (autorizationFiled != null) {%><%=autorizationFiled%> <br/><%}%>

<%
} else {
    User us = (User) user;
%>
<p> Добро пожаловать в систему: <%= us.getName() %>
</p>
<a href="/application/logout">Выйти из системы.<br/></a>

<%
    }
%>
<a href="/application/ProductList"> Просмотреть список продуктов </a> <br/>
<a href="/application/AccountList"> Просмотреть список счетов пользователей(в целях тестов, с точки зрения безопасности
    не смотрел)</a>


</body>
</html>
