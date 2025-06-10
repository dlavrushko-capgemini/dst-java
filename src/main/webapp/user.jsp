package main.webapp;

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.model.Account" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Account account = (Account) request.getAttribute("account");
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Account Information</title>
</head>
<body>
    <h1>User Account Information</h1>
    <div>
        <p>Username: <%= account != null ? account.getUsername() : "Account not found" %></p>
        <p>Email: <%= account != null ? account.getEmail() : "Account not found" %></p>
    </div>
    <c:if test="${not empty errorMessage}">
        <p style="color:red;"><%= errorMessage != null ? errorMessage : "" %></p>
    </c:if>
    
    <h2>Register</h2>
    <form action="UserServlet?action=register" method="post" id="registrationForm">
        <input type="text" name="username" placeholder="Username" required minlength="3" maxlength="20">
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="password" placeholder="Password" required minlength="6">
        <button type="submit">Register</button>
    </form>

    <h2>Login</h2>
    <form action="UserServlet?action=login" method="post" id="loginForm">
        <input type="text" name="username" placeholder="Username" required>
        <input type="password" name="password" placeholder="Password" required>
        <button type="submit">Login</button>
    </form>
</body>
</html>