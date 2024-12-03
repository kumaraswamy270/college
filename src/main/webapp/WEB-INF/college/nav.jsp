<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Sidebar Menu -->
<nav class="col-md-3 col-lg-2 left-side-menu collapsed" id="sidebarMenu">
	<div class="navbar navbar-dark flex-column">
		<!-- Navigation Links with Icons -->
		<a class="nav-link" href="<c:url value='/index' />"><i
			class="fas fa-home"></i><span>Home</span></a> <a class="nav-link"
			href="<c:url value='/student/form' />"><i
			class="fas fa-user-plus"></i><span>Add Student</span></a> <a
			class="nav-link" href="<c:url value='/student/list' />"><i
			class="fas fa-users"></i><span>View All Students</span></a> <a
			class="nav-link" href="<c:url value='/course/form' />"><i
			class="fas fa-book-medical"></i><span>Add Course</span></a> <a
			class="nav-link" href="<c:url value='/course/list' />"><i
			class="fas fa-book-open"></i><span>View All Courses</span></a> <a
			class="nav-link" href="<c:url value='/college/form' />"><i
			class="fas fa-school"></i><span>Add College</span></a> <a
			class="nav-link" href="<c:url value='/college/list' />"><i
			class="fas fa-university"></i><span>View All Colleges</span></a>
	</div>
</nav>

