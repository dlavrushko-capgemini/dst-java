<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error Page</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <div class="error-container">
        <h1>Error</h1>
        <p>
            <%
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null) {
                    if (errorMessage.contains("SecurityException")) {
                        out.print("A security issue has occurred. Please contact support.");
                    } else {
                        out.print("An error has occurred. Please try again later.");
                    }
                } else {
                    out.print("An unexpected error has occurred. Please try again later.");
                }
            %>
        </p>
        <a href="login.jsp" class="btn">Return to Login</a>
    </div>
</body>
</html>