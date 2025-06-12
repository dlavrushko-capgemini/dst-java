<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Successful</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #4CAF50;
        }
        h2 {
            color: #333;
        }
        a {
            display: inline-block;
            margin: 10px 0;
            text-decoration: none;
            color: #4CAF50;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Account Created Successfully!</h1>
        <p>Welcome, <%= request.getParameter("username") %>! Your account has been successfully created.</p>
        <h2>Next Steps:</h2>
        <p>You can now log in to your account using your credentials.</p>
        <p>If you encounter any issues, please contact our support team for assistance.</p>
        <a href="login.jsp">Log In</a>
        <br>
        <a href="registration.jsp">Return to Registration</a>
    </div>
</body>
</html>