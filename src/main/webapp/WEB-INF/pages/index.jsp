<%@ page import="com.andersen.spring.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: savotii
  Date: 10.01.2018
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
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

    Object logout = request.getAttribute("logout");
    if (logout != null)
        try {
            session.removeAttribute("user");
            request.removeAttribute("user");
        } catch (Exception e) {
            e.printStackTrace();
        }

    Object user = request.getAttribute("user");
    if (user == null) {
%>

<form action="/application/login" method="post">
    <input type="text" placeholder="Введите логин" name="login">
    <input type="text" placeholder="Введите email" name="email">
    <input type="submit" name="submit" value="Выполнить авторизацию">
</form>

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
