package WEB-INF.views;

<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="/login" method="POST">
        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
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