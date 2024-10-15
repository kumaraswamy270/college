<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>College Management System</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
</head>

<body class="light-mode">
	<!-- Start with light mode by default -->

	<header>
		<img src="img/logo.png" alt="Logo" class="logo">
		<h1>College Management System</h1>
		<a href="LogOutServlet" class="logout-button">Logout</a>
		<jsp:include page="loginuser.jsp" />
	</header>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<button id="modeToggle" class="btn btn-outline-secondary me-2">Switch
				to Night Mode</button>
			<a class="nav-link" href="index.jsp">Home</a>

			<!-- Dropdown for Students -->
			<div class="btn-group">
				<button type="button" class="btn btn-danger dropdown-toggle"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Students</button>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="studentform.jsp">Add Student</a> <a
						class="dropdown-item" href="GetAllStudentsServlet">View All
						Students</a> <a class="dropdown-item" href="EditServlet">Edit
						Student</a> <a class="dropdown-item" href="DeleteStudentServlet">Delete
						Student</a>
				</div>
			</div>

			<!-- Dropdown for Courses -->
			<div class="btn-group">
				<button type="button" class="btn btn-danger dropdown-toggle"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Courses</button>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="courseform.jsp">Course Form</a> <a
						class="dropdown-item" href="courselist.jsp">Course List</a>
				</div>
			</div>

			<!-- Dropdown for Colleges -->
			<div class="btn-group">
				<button type="button" class="btn btn-danger dropdown-toggle"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Colleges</button>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="collegeform.jsp">College Form</a> <a
						class="dropdown-item" href="listofcolleges.jsp">College List</a>
				</div>
			</div>
		</div>
	</nav>

	<!-- Include Bootstrap JS and Popper.js -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
