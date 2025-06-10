package main.webapp;

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    HttpSession session = request.getSession(false);
    String username = null;
    if (session != null) {
        username = (String) session.getAttribute("username");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h1>Welcome, <c:if test="${not empty username}"><c:out value="${username}"/></c:if><c:if test="${empty username}">Guest</c:if>!</h1>
    <a href="<c:url value='/logout'/>" onclick="event.preventDefault(); document.getElementById('logout-form').submit();">Log out</a>
    <form id="logout-form" action="<c:url value='/logout'/>" method="post" style="display: none;"></form>
</body>
</html>