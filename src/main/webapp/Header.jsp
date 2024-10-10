<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>College Management System</title>
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


	<nav>
		<button id="modeToggle" class="mode-toggle-button">Switch to
			Night Mode</button>
		<a href="index.jsp">Home</a>
		<div class="dropdown">
			<button class="dropbtn">Students</button>
			<div class="dropdown-content">
				<a href="studentform.jsp">Add Student</a> <a
					href="GetAllStudentsServlet">View All Students</a> <a
					href="EditServlet">Edit Student</a> <a href="DeleteStudentServlet">Delete
					Student</a>
			</div>
		</div>
		<div class="dropdown">
			<button class="dropbtn">Courses</button>
			<div class="dropdown-content">
				<a href="courseform.jsp">Course Form</a> <a href="courselist.jsp">Course
					List</a>
			</div>
		</div>
		<div class="dropdown">
			<button class="dropbtn">Colleges</button>
			<div class="dropdown-content">
				<a href="collegeform.jsp">College Form</a> <a
					href="listofcolleges.jsp">College List</a>
			</div>
		</div>
	</nav>