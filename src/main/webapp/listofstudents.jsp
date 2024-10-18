<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="Header.jsp"%>
<%@ include file="sessionCheck.jsp"%>
<title>List of Students</title>

<div class="container">
	<!-- Display success message from request scope -->
	<div class="row">
		<div class="col-12">
			<c:if test="${not empty successMessage}">
				<div class="alert alert-success">${successMessage}</div>
			</c:if>
		</div>
	</div>

	<!-- Display session message from session scope -->
	<div class="row">
		<div class="col-12">
			<c:if test="${not empty sessionScope.sessionMessage}">
				<div class="alert alert-success">${sessionScope.sessionMessage}</div>
				<c:remove var="sessionMessage" scope="session" />
			</c:if>
		</div>
	</div>

	<div class="container mt-4">
		<div class="row">
			<div class="col-12">
				<h1>List of Students</h1>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-8">
				<form class="d-flex ms-auto" role="search" id="searchForm">
					<input id="searchInput" class="form-control me-2" type="search"
						placeholder="Search by Roll Number or Name" aria-label="Search">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
			</div>
		</div>


		<!-- Table Section with Grid Layout -->
		<div class="row">
			<div class="col-12">
				<div class="table-responsive">
					<!-- Wrap the table in this div -->
					<table id="studentTable" class="table table-striped table-bordered">
						<thead>
							<tr class="table-primary">
								<th>Student Code</th>
								<th>Roll Number</th>
								<th>Marks</th>
								<th>Branch</th>
								<th>College</th>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Father Name</th>
								<th>Mobile Number</th>
								<th>Date of Birth</th>
								<th>Address</th>
								<th>Status</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="student" items="${studentlist}">
								<tr class="table-light">
									<td class="align-middle">${student.studentcode}</td>
									<td class="align-middle">${student.rollnumber}</td>
									<td class="align-middle">${student.marks}</td>
									<td class="align-middle">${student.branch}</td>
									<td class="align-middle">${student.college}</td>
									<td class="align-middle">${student.firstname}</td>
									<td class="align-middle">${student.lastname}</td>
									<td class="align-middle">${student.fathername}</td>
									<td class="align-middle">${student.mobileno}</td>
									<td class="align-middle">${student.dateofbirth}</td>
									<td class="align-middle">${student.address}</td>
									<td class="align-middle">${student.status}</td>
									<td><a href="EditServlet?rollnumber=${student.rollnumber}">Edit</a>
										| <a
										href="DeleteStudentServlet?rollnumber=${student.rollnumber}"
										onclick="return confirm('Are you sure you want to delete this student?');">Delete</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<script src="js/search.js"></script>
	<jsp:include page="footer.jsp" />