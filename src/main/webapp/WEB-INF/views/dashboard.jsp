package com.example.webapp.views;

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h1>Welcome, ${sessionScope.username}!</h1>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>