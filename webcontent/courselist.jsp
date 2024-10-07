
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Course List</title>
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
		<a href="index.html">Home</a> <a href="courseform.html">Course
			Form</a> <a href="courselist.html">Course List</a> <a
			href="studentform.html">Student Form</a> <a
			href="listofstudents.html">Student List</a>
	</nav>

	<div class="container">
		<h2>Course List</h2>
		<table>
			<thead>
				<tr>
					<th>Course Code</th>
					<th>Course Name</th>
					<th>Instructor</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>CS101</td>
					<td>Introduction to Computer Science</td>
					<td>Dr. Smith</td>
					<td>2024-01-15</td>
					<td>2024-05-15</td>
					<td class="action-buttons"><a href="listofstudents.html"
						class="view-button">View</a></td>
				</tr>
				<tr>
					<td>CS102</td>
					<td>Data Structures</td>
					<td>Prof. Johnson</td>
					<td>2024-02-01</td>
					<td>2024-06-01</td>
					<td class="action-buttons"><a href="listofstudents.html"
						class="view-button">View</a></td>
				</tr>
				<tr>
					<td>CS103</td>
					<td>Algorithms</td>
					<td>Dr. Lee</td>
					<td>2024-03-15</td>
					<td>2024-07-15</td>
					<td class="action-buttons"><a href="listofstudents.html"
						class="view-button">View</a></td>
				</tr>
				<tr>
					<td>CS104</td>
					<td>Database Systems</td>
					<td>Dr. Patel</td>
					<td>2024-04-10</td>
					<td>2024-08-10</td>
					<td class="action-buttons"><a href="listofstudents.html"
						class="view-button">View</a></td>
				</tr>
				<tr>
					<td>CS105</td>
					<td>Operating Systems</td>
					<td>Dr. Wilson</td>
					<td>2024-05-20</td>
					<td>2024-09-20</td>
					<td class="action-buttons"><a href="listofstudents.html"
						class="view-button">View</a></td>
				</tr>
			</tbody>
		</table>
	</div>

	<footer>
		<p>&copy; 2024 College Management System</p>
	</footer>
	<script src="nightandlightmode.js"></script>

</body>
</html>
