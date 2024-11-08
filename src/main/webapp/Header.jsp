<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>College Management System</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/stylesheet.css' />">

<!-- Font Awesome for icons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<style>
/* Sidebar default styling */
.left-side-menu {
	position: fixed;
	top: 60px; /* Adjusted for new header height */
	left: 0;
	width: 70px;
	height: calc(100% - 60px); /* Adjusted for new header height */
	background-color: #343a40;
	color: white;
	overflow-y: auto;
	transition: width 0.3s;
	padding-top: 1rem;
}

.left-side-menu.expanded {
	width: 220px;
}

/* Sidebar items */
.left-side-menu .nav-link {
	color: white;
	display: flex;
	align-items: center;
	padding: 0.75rem;
	font-size: 0.85rem;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

.left-side-menu .nav-link i {
	margin-right: 10px;
	font-size: 1.2rem;
}

.left-side-menu.collapsed .nav-link span {
	display: none; /* Hide text by default */
}

.content-area {
	margin-left: 70px; /* Keep the left margin for sidebar */
	margin-top: 60px; /* Add margin to avoid overlap with header */
	padding: 1rem;
	transition: margin-left 0.3s;
	height: calc(100% - 60px); /* Adjust content area height */
	overflow-y: auto; /* Allow scrolling if content overflows */
}

/* Align the three-line menu button and heading within the header */
header h1 {
	margin: 0;
	font-size: 1.5rem; /* Adjusted font size for better fit */
}

.navbar-toggler {
	margin-right: 10px; /* Adjusts space between button and heading */
}

/* Logo Styling */
.logo {
	height: 40px; /* Adjust logo height as needed */
	margin-right: 1290px; /* Space between the logo and the menu button */
}
</style>
</head>

<body class="light-mode">
	<div class="container-fluid">
		<!-- Header using Bootstrap 5 grid system -->
		<header>
			<!-- Sidebar Toggle Button in Header -->
			<button class="fas fa-bars" type="button" onclick="toggleSidebar()">
				<span class="navbar-toggler-icon"></span>
			</button>
			<!-- Logo on the left side -->
			<img src="<c:url value='/img/logo.png'/>" alt="Logo" class="logo">
			<h1 class="text-white text-center">College Management System</h1>

			<div>
				<jsp:include page="loginuser.jsp" />
			</div>
		</header>

		<div class="row m-1 p-0">
			<!-- Sidebar Menu - Include Sidebar JSP -->
			<jsp:include page="nav.jsp" />

			<!-- Content Area -->
			<main class="col-md-9 col-lg-10 content-area"></main>
		</div>
	</div>

	<!-- Bootstrap 5 JS Bundle -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		function toggleSidebar() {
			const sidebar = document.getElementById("sidebarMenu");
			sidebar.classList.toggle("expanded");
			sidebar.classList.toggle("collapsed");
		}
	</script>
</body>
</html>
