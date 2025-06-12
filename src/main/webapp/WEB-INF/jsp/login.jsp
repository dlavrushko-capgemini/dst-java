<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="${pageContext.request.contextPath}/soteria" method="post">
        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <input type="checkbox" id="rememberMe" name="rememberMe">
            <label for="rememberMe">Remember me</label>
        </div>
        <div>
            <input type="submit" value="Login">
        </div>
        <c:if test="${not empty errorMessage}">
            <div style="color:red;">${errorMessage}</div>
        </c:if>
    </form>
</body>
</html>