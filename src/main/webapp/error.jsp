package main.webapp;

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h1>Error</h1>
    <p>
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
                out.print(errorMessage);
            } else {
                out.print("An unknown error occurred.");
            }
        %>
    </p>
    <a href="login.jsp">Back to Login</a>
</body>
</html>