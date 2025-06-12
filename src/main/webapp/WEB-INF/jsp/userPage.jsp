<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Page</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css'/>">
</head>
<body>
    <%
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
    %>
            <h1>Welcome, <c:out value='${user.name}'/>!</h1>
            <p>Your email: <c:out value='${user.email}'/></p>
            <p>Your role: <c:out value='${user.role}'/></p>
    <%
        } else {
    %>
            <h1>Please log in to access this page.</h1>
    <%
        }
    %>
    
    <h2>Actions</h2>
    <ul>
        <li><a href="<c:url value='/profile'/>">View Profile</a></li>
        <li><a href="<c:url value='/logout'/>">Logout</a></li>
    </ul>
</body>
</html>