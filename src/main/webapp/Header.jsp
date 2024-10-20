<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>College Management System</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<style>
/* Custom styles for left-side layout */
.left-side-menu {
	position: fixed; /* Fix the menu to the left side */
	top: 0;
	left: 0;
	width: 220px; /* Adjusted width */
	height: 100%; /* Full height */
	background-color: #343a40; /* Same color as the navbar */
	padding: 0.5rem; /* Add padding for spacing */
	color: white; /* Text color for contrast */
	overflow-y: auto; /* Allow scrolling if content overflows */
}

.left-side-menu .nav-link, .left-side-menu .btn {
	color: white; /* Make text color white for visibility */
	padding: 0.25rem 0.5rem; /* Decrease padding for smaller buttons */
	margin-bottom: 8px; /* Space between buttons */
	width: calc(100% - 10px);
	/* Ensure buttons take full width with a slight margin */
	font-size: 0.85rem; /* Decrease font size */
	margin-left: 5px;
	/* Added margin to slightly move buttons to the right */
}

.content-area {
	margin-left: 240px; /* Adjusted space for the left-side menu */
	padding: 1rem; /* Add padding for the content area */
}

.dropdown-menu {
	background-color: #343a40; /* Match dropdown background with sidebar */
	border: none; /* Remove border for dropdown */
}

.dropdown-item {
	color: white; /* Make dropdown item text color white */
	font-size: 0.85rem; /* Decrease font size for dropdown items */
}

.dropdown-item:hover {
	background-color: #495057; /* Add a hover effect for dropdown items */
}

/* Ensure buttons take the full width of the sidebar */
.dropdown-toggle {
	width: 100%; /* Full width for dropdown toggle */
	text-align: left; /* Align text to the left */
	padding: 0.25rem; /* Adjust padding */
	font-size: 0.85rem; /* Decrease font size for dropdown button */
}

/* Reduce dropdown button height */
.dropdown-toggle {
	height: auto; /* Allow height to adjust based on content */
}

.carousel {
	width: calc(100% - 240px); /* Adjust width based on left-side menu */
	margin-left: 240px; /* Align with the content area */
	height: 300px; /* Adjust height as needed */
}

.carousel-item img {
	width: 100%; /* Make images responsive */
	height: 100%; /* Ensure images cover the carousel height */
	object-fit: cover; /* Cover the carousel area */
}

.logo {
	width: 90px; /* Adjust logo width as needed */
	height: auto;
	display: block;
	margin: 10px auto; /* Center the logo with margin */
}

.form-container {
	margin-left: 250px;
	/* Adjust this value based on your navigation width */
}

header {
	padding-top: 20px; /* Increase top padding to add height */
	padding-bottom: 20px;
	/* Optional: Increase bottom padding for balance */
}
</style>
</head>

<body class="light-mode">
	<header>
		<h1>College Management System</h1>
		<a href="LogOutServlet" class="logout-button">Logout</a>
		<jsp:include page="loginuser.jsp" />
	</header>

	<div class="left-side-menu">
		<nav class="navbar navbar-dark bg-dark flex-column">
			<img src="img/logo.png" alt="Logo" class="logo">
			<!-- Hamburger button for collapsing the menu -->
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<!-- Collapsible menu items -->
			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<button id="modeToggle" class="btn btn-outline-secondary mb-2">Switch
					to Night Mode</button>
				<a class="nav-link" href="index.jsp">Home</a>

				<!-- Dropdown for Students -->
				<button type="button" class="btn btn-danger dropdown-toggle mb-2"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Students</button>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="studentform.jsp">Add Student</a> <a
						class="dropdown-item" href="GetAllStudentsServlet">View All
						Students</a> <a class="dropdown-item" href="EditServlet">Edit
						Student</a> <a class="dropdown-item" href="DeleteStudentServlet">Delete
						Student</a>
				</div>

				<!-- Dropdown for Courses -->
				<button type="button" class="btn btn-danger dropdown-toggle mb-2"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Courses</button>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="courseform.jsp">Course Form</a> <a
						class="dropdown-item" href="courselist.jsp">Course List</a>
				</div>

				<!-- Dropdown for Colleges -->
				<button type="button" class="btn btn-danger dropdown-toggle mb-2"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Colleges</button>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="collegeform.jsp">College Form</a> <a
						class="dropdown-item" href="listofcolleges.jsp">College List</a>
				</div>
			</div>
		</nav>
	</div>

	<!-- Include Bootstrap JS and Popper.js -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
