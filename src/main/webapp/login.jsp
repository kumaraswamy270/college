<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Page</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body class="custom-body">

	<div class="container">
		<img src="img/logo.png" alt="Logo" class="logo">
		<h1>Login</h1>
		<c:choose>

			<c:when test="${not empty param.sessionexpired}">
				<div id="sessionexpired" class="error">Your session has
					expired. Please Login again.</div>
			</c:when>

			<c:when test="${not empty param.error}">
				<div id="serverError" class="error">Invalid credentials</div>
			</c:when>

			<c:when test="${not empty param.logout}">
				<div id="serverinfo" class="error">Logout successful</div>
			</c:when>

			<c:when test="${not empty param.loginSuccess}">
				<div id="logininfo" class="success-message">Login successful</div>
			</c:when>
		</c:choose>
		<form id="loginForm" action="LoginServlet" method="POST">
			<label for="username">Username</label> <input type="text"
				id="username" name="username"> <span class="error"
				id="usernameError"></span> <label for="password">Password</label> <input
				type="password" id="password" name="password"> <span
				class="error" id="passwordError"></span>
			<button id="submitButton" type="submit">Login</button>
		</form>
	</div>

	<script src="js/login.js"></script>

</body>
</html>
