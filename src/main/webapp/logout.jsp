package webapp;

<!DOCTYPE html>
<html lang="en" role="document">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logout Confirmation</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container" role="main">
        <h1>You have been successfully logged out.</h1>
        <p>
            <a href="index.jsp" aria-label="Return to Home Page">Return to Home Page</a>
        </p>
        <p>
            <a href="login.jsp" aria-label="Login Again">Login Again</a>
        </p>
        <p>
            <small>If your session has timed out, please log in again to continue.</small>
        </p>
    </div>
    <script>
        sessionStorage.clear();
    </script>
</body>
</html>