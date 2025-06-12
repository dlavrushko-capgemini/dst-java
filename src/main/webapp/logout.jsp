<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Logout Confirmation</title>
</head>
<body>
    <h1>Logout Confirmation</h1>
    <p>
        <%= request.getAttribute("message") != null ? request.getAttribute("message") : "You have been logged out successfully." %>
    </p>
    <a href="login.jsp">Return to Login</a>
    <a href="home.jsp">Return to Home</a>
</body>
</html>