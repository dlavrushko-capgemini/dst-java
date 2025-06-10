package webapp;

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.example.com/tags/base" prefix="base" %>

<base:layout>
    <div class="col-md-12">
        <h1 class="alert alert-success">
            <c:choose>
                <c:when test="${not empty username}">
                    Thank you <c:out value="${username}"/> for your registration!
                </c:when>
                <c:otherwise>
                    Thank you for your registration!
                </c:otherwise>
            </c:choose>
        </h1>
        <div class="row">
            <a href="userDashboard.jsp" class="btn btn-primary btn-block" role="button" aria-label="Continue to user dashboard">Continue »</a>
        </div>
    </div>
</base:layout>