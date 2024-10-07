
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>List of Colleges</title>
<!-- Link to external stylesheet -->
<link rel="stylesheet" href="stylesheet.css">
</head>
<body class="light-mode">
	<!-- Start with light mode by default -->

	<header>
		<img src="img/logo.png" alt="Logo" class="logo">
		<h1>College Management System</h1>

		<a href="login.html" class="logout-button">Logout</a>

	</header>



	<nav>
		<button id="modeToggle" class="mode-toggle-button">Switch to
			Night Mode</button>
		<a href="index.html">Home</a> <a href="courseform.html">CourseForm</a>
		<a href="courselist.html">CourseList</a> <a href="studentform.html">StudentForm</a>
		<a href="listofstudents.html">StudentList</a>
	</nav>

	<!-- Main Content Section -->
	<div class="container">
		<h2>List of Colleges</h2>
		<ol>
			<li>College of Engineering</li>
			<li>Institute of Technology</li>
			<li>School of Business</li>
			<li>National College of Arts</li>
			<li>Global Institute of Science</li>
			<li>International College of Medicine</li>
			<li>City University</li>
			<li>Green Valley Polytechnic</li>
		</ol>
	</div>

	<!-- Footer Section -->
	<footer>
		<p>&copy; 2024 College Management System. All rights reserved.</p>
	</footer>
	<script src="nightandlightmode.js"></script>

</body>
</html>
