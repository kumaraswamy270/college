<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/login.css">
</head>
<body class="custom-body">

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6 bg-pink p-4 rounded shadow">
                <img src="img/logo.png" alt="Logo" class="img-fluid mb-4 mx-auto d-block logo">
                <h1 class="text-center">Login</h1>
                <c:choose>
                    <c:when test="${not empty param.sessionexpired}">
                        <div class="alert alert-danger" role="alert">Your session has expired. Please Login again.</div>
                    </c:when>
                    <c:when test="${not empty param.error}">
                        <div class="alert alert-danger" role="alert">Invalid credentials</div>
                    </c:when>
                    <c:when test="${not empty param.logout}">
                        <div class="alert alert-info" role="alert">Logout successful</div>
                    </c:when>
                    <c:when test="${not empty param.loginSuccess}">
                        <div class="alert alert-success" role="alert">Login successful</div>
                    </c:when>
                </c:choose>
                <form id="loginForm" action="LoginServlet" method="POST" class="mt-4">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="username" name="username">
                        <span class="error text-danger" id="usernameError"></span>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password">
                        <span class="error text-danger" id="passwordError"></span>
                    </div>
                    <button id="submitButton" type="submit" class="btn btn-success btn-block">Login</button>
                </form>
            </div>
        </div>
    </div>

    <script src="js/login.js"></script>

</body>
</html>
