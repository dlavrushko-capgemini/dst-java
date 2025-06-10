package webapp;

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.Registration" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <h2>Registration Form</h2>
    <form action="${pageContext.request.contextPath}/registration" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="${registration.username}" required><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${registration.email}" required><br>
        
        <input type="submit" value="Register">
    </form>
    
    <c:if test="${not empty errorMessage}">
        <div style="color:red;">${errorMessage}</div>
    </c:if>
</body>
</html>