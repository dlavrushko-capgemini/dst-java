package your.package.name;

<%@ page import="jakarta.enterprise.inject.spi.CDI" %>
<%@ page import="jakarta.mvc.MvcContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    var mvc = CDI.current().select(MvcContext.class).get();
    response.sendRedirect(mvc.getContextPath() + mvc.getApplicationPath() + "/user");
%>