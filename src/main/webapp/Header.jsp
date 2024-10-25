<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>College Management System</title>
<!-- Bootstrap 5 CDN -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<style>
/* Custom styles for left-side layout */
.left-side-menu {
	position: fixed;
	top: 0;
	left: 0;
	width: 220px;
	height: 100%;
	background-color: #343a40;
	padding: 0.5rem;
	color: white;
	overflow-y: auto;
}

.left-side-menu .nav-link, .left-side-menu .btn {
	color: white;
	padding: 0.25rem 0.5rem;
	margin-bottom: 8px;
	width: calc(100% - 10px);
	font-size: 0.85rem;
	margin-left: 5px;
}

.content-area {
	margin-left: 240px;
	padding: 1rem;
}

.dropdown-menu {
	background-color: #343a40;
	border: none;
}

.dropdown-item {
	color: white;
	font-size: 0.85rem;
}

.dropdown-item:hover {
	background-color: #495057;
}

.dropdown-toggle {
	width: 100%;
	text-align: left;
	padding: 0.25rem;
	font-size: 0.85rem;
}

header {
	padding-top: 20px;
	padding-bottom: 20px;
	margin-left: 22px;
}

.form-container {
	margin-left: 250px;
}
</style>
</head>

<body class="light-mode">
	<div class="container-fluid">
		<!-- Header using Bootstrap 5 grid system -->
		<header class="row bg-dark py-3">
			<div class="col text-center">
				<h1 class="text-white">College Management System</h1>
			</div>
			<div class="col text-end">
				<jsp:include page="loginuser.jsp" />
			</div>
		</header>

		<!-- Body layout using Bootstrap 5 Grid -->
		<div class="row">
			<!-- Sidebar using Bootstrap grid -->
			<nav class="col-md-3 col-lg-2 left-side-menu">
				<div class="navbar navbar-dark flex-column">
					<img src="img/logo.png" alt="Logo" style="width: 80px;"
						class="rounded-pill mb-3">
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
						aria-controls="navbarNavDropdown" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>

					<!-- Collapsible menu items -->
					<div class="collapse navbar-collapse" id="navbarNavDropdown">
						<button id="modeToggle" class="btn btn-outline-secondary mb-2">Switch
							to Night Mode</button>
						<a class="nav-link" href="index.jsp">Home</a>

						<!-- Dropdown for Students -->
						<div class="dropdown mb-2">
							<button class="btn btn-danger dropdown-toggle" type="button"
								id="dropdownMenuButton1" data-bs-toggle="dropdown"
								aria-expanded="false">Students</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
								<li><a class="dropdown-item" href="studentform.jsp">Add
										Student</a></li>
								<li><a class="dropdown-item" href="GetAllStudentsServlet">View
										All Students</a></li>
								<li><a class="dropdown-item" href="EditServlet">Edit
										Student</a></li>
								<li><a class="dropdown-item" href="DeleteStudentServlet">Delete
										Student</a></li>
							</ul>
						</div>

						<!-- Dropdown for Courses -->
						<div class="dropdown mb-2">
							<button class="btn btn-danger dropdown-toggle" type="button"
								id="dropdownMenuButton2" data-bs-toggle="dropdown"
								aria-expanded="false">Courses</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton2">
								<li><a class="dropdown-item" href="courseform.jsp">Course
										Form</a></li>
								<li><a class="dropdown-item" href="courselist.jsp">Course
										List</a></li>
							</ul>
						</div>

						<!-- Dropdown for Colleges -->
						<div class="dropdown mb-2">
							<button class="btn btn-danger dropdown-toggle" type="button"
								id="dropdownMenuButton3" data-bs-toggle="dropdown"
								aria-expanded="false">Colleges</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton3">
								<li><a class="dropdown-item" href="collegeform.jsp">College
										Form</a></li>
								<li><a class="dropdown-item" href="listofcolleges.jsp">College
										List</a></li>
							</ul>
						</div>
					</div>
				</div>
			</nav>

			<!-- Main content area using Bootstrap grid -->
			<main class="col-md-9 col-lg-10 content-area"> <!-- Content will go here -->
			</main>
		</div>
	</div>

	<!-- Bootstrap 5 JS Bundle -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
