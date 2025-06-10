package views;

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <h2>Registration Form</h2>
    <form action="/registration" method="post">
        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div>
            <input type="submit" value="Register">
        </div>
    </form>
    <c:if test="${not empty errorMessages}">
        <ul>
            <c:forEach var="error" items="${errorMessages}">
                <li>${error}</li>
            </c:forEach>
        </ul>
    </c:if>
</body>
</html>