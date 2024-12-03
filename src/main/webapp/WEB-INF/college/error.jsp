<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Error</title>
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
</head>
<body>
	<div class="container">
		<h1>Error</h1>
		<p>
			<strong>Status Code:</strong> ${status}
		</p>
		<p>
			<strong>Message:</strong> ${message}
		</p>
		<p>
			<strong>Exception:</strong> ${exception}
		</p>

		<hr>

		<h3>Something went wrong. Please try again later.</h3>
		<!-- Redirect to login or home page -->
		<a href="<c:url value='/login'/>">Back to Login</a>
	</div>
</body>
</html>
