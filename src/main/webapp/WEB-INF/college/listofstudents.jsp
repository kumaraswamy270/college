<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="Header.jsp"%>
<%@ include file="sessionCheck.jsp"%>
<title>List of Students</title>
<style>
/* Adjust the margin of the heading to bring it closer to the search bar */
h2.text-center.my-4 {
	font-size: 26px;
	font-weight: bold;
	color: #4a4a4a;
	margin-top: 60px; /* Add space for the fixed header */
	margin-bottom: 10px;
	text-transform: uppercase;
	letter-spacing: 1px;
}

/* Row styling for the search bar */
.row.mb-3 {
	margin-top: 0; /* Reduce space above the search bar */
	margin-bottom: 10px; /* Reduce space below the search bar */
	padding: 0;
}

/* Search bar form styling */
#searchForm {
	display: flex;
	justify-content: start; /* Align the items to the left */
	align-items: center;
	gap: 1px; /* Add some space between input and button */
}

#searchInput {
	width: 250px;
}

button {
	width: 100px;
	padding: 10px;
	border: none;
	border-radius: 5px;
	font-size: 16px;
	background-color: #28a745;
	color: white;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

/* Table styling */
table {
	width: 100%;
	margin-top: 20px; /* Ensure space between table and other elements */
	border-collapse: collapse;
}

table th, table td {
	padding: 12px;
	text-align: left;
}

table th {
	background-color: #007bff;
	color: white;
}

table tr:nth-child(even) {
	background-color: #f2f2f2;
}

table .align-middle {
	vertical-align: middle;
}

table .table-success {
	background-color: #d4edda;
}

table .table-danger {
	background-color: #f8d7da;
}

table .bg-light {
	background-color: #f8f9fa;
}

table .bg-warning {
	background-color: #ffeeba;
}

.pagination-container {
	display: flex;
	justify-content: center;
	align-items: center;
	margin: 20px 0;
}

.pagination {
	display: flex;
	list-style: none;
	padding: 0;
	margin: 0;
	gap: 10px;
}

.pagination .page-item {
	display: flex;
}

.pagination .page-link {
	padding: 10px 15px;
	color: #007bff;
	text-decoration: none;
	border: 1px solid #dee2e6;
	border-radius: 4px;
	transition: background-color 0.3s, color 0.3s;
	font-size: 14px;
	text-align: center;
}

.pagination .page-item.active .page-link {
	background-color: #007bff;
	color: white;
	border-color: #007bff;
}

.pagination .prev-button, .pagination .next-button {
	background-color: #f8f9fa;
	color: #007bff;
	border: 1px solid #dee2e6;
	border-radius: 4px;
	padding: 10px 15px;
	text-align: center;
	font-size: 14px;
	text-decoration: none;
	transition: background-color 0.3s, color 0.3s;
}

.pagination .prev-button:hover, .pagination .next-button:hover {
	background-color: #e9ecef;
	color: #0056b3;
	border-color: #ced4da;
}

.pagination .prev-button.disabled, .pagination .next-button.disabled {
	background-color: #f8f9fa;
	color: #6c757d;
	cursor: not-allowed;
	border-color: #dee2e6;
}
</style>

<div class="container">
	<h2 class="text-center my-4">List of Students</h2>

	<!-- Display success message from request scope -->
	<div class="row">
		<div class="d-flex justify-content-center align-items-center"
			style="height: 3vh;">
			<c:if test="${not empty successMessage}">
				<div class="alert alert-success">${successMessage}</div>
			</c:if>
		</div>
	</div>

	<!-- Display session message from session scope -->
	<div class="row">
		<div class="d-flex justify-content-center align-items-center"
			style="height: 3vh;">
			<c:if test="${not empty sessionScope.sessionMessage}">
				<div class="alert alert-success">${sessionScope.sessionMessage}</div>
				<c:remove var="sessionMessage" scope="session" />
			</c:if>
		</div>
	</div>

	<!-- Search bar row -->
	<div class="row mb-3">
		<div class="col-12">
			<form class="d-flex justify-content-center" role="search"
				id="searchForm">
				<!-- Search input field -->
				<input id="searchInput" class="form-control me-2" type="search"
					placeholder="Search by Roll Number or Name" aria-label="Search">
				<!-- Search button -->
				<button class="btn btn-success" type="submit">Search</button>
			</form>
		</div>
	</div>

	<!-- Student Table -->
	<div class="row">
		<div class="col-12">
			<table id="studentTable"
				class="table table-striped table-bordered w-100">
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
						<th>Image</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="student" items="${students}">
						<tr
							class="<c:choose><c:when test='${student.status == "pass"}'>table-success</c:when><c:otherwise>table-danger</c:otherwise></c:choose>">
							<td class="align-middle bg-light">${student.studentcode}</td>
							<td class="align-middle bg-warning">${student.rollnumber}</td>
							<td class="align-middle">${student.marks}</td>
							<td class="align-middle bg-info">${student.branch}</td>
							<td class="align-middle bg-light">${student.college}</td>
							<td class="align-middle bg-secondary text-white">${student.firstname}</td>
							<td class="align-middle bg-light">${student.lastname}</td>
							<td class="align-middle bg-info text-white">${student.fathername}</td>
							<td class="align-middle bg-warning">${student.mobileno}</td>
							<td class="align-middle bg-light">${student.dateofbirth}</td>
							<td class="align-middle bg-secondary text-white">${student.address}</td>
							<td class="align-middle"><c:choose>
									<c:when test='${student.status == "pass"}'>Pass</c:when>
									<c:otherwise>Fail</c:otherwise>
								</c:choose></td>
							<td><c:if
									test="${not empty studentImages[student.rollnumber]}">
									<!-- Display the image using Base64 from the map -->
									<img
										src="data:image/jpeg;base64,${studentImages[student.rollnumber]}"
										alt="Student Image" width="100" height="100" />
								</c:if></td>
							<td class="align-middle"><a
								href="/student/edit?rollnumber=<c:out value='${student.rollnumber}' />">
									<i class="fas fa-edit"></i> Edit
							</a> | <a
								href="/student/deleteStudent?rollnumber=<c:out value='${student.rollnumber}' />"
								onclick="return confirm('Are you sure you want to delete this student?');">
									<i class="fas fa-trash-alt"></i> Delete
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>
				<div class="pagination-container">
					<ul class="pagination">

						<!-- Previous Button Logic -->
						<c:if test="${currentPage > 1}">
							<li class="page-item"><a class="prev-button"
								href="/student/page${currentPage - 1}">Previous</a></li>
						</c:if>

						<!-- Page Number Links -->
						<c:forEach var="i" begin="1" end="3">
							<li class="page-item ${i == currentPage ? 'active' : ''}"><a
								class="page-link" href="/student/page${i}">Page ${i}</a></li>
						</c:forEach>

						<!-- Next Button Logic -->
						<c:if test="${currentPage < 3}">
							<li class="page-item"><a class="next-button"
								href="/student/page${currentPage + 1}">Next</a></li>
						</c:if>
					</ul>
				</div>
			</div>

		</div>
	</div>
</div>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<c:url value='/js/search.js' />"></script>
<script src="<c:url value='/js/paginated.js' />"></script>
<jsp:include page="footer.jsp" />