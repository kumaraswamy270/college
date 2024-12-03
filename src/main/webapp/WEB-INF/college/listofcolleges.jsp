<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="Header.jsp"%>
<%@ include file="sessionCheck.jsp"%>
<title>College List</title>
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
</style>

<div class="container">
	<h2 class="text-center my-4">College List</h2>

	<!-- Success message -->
	<div class="row">
		<div class="d-flex justify-content-center align-items-center"
			style="height: 3vh;">
			<c:if test="${not empty successMessage}">
				<div class="alert alert-success">${successMessage}</div>
			</c:if>
		</div>
	</div>

	<!-- Search bar -->
	<div class="row mb-3">
		<div class="col-12">
			<form id="searchForm" role="search">
				<input id="searchInput" class="form-control me-2" type="search"
					placeholder="Search by College Name">
				<button class="btn btn-success" type="submit">Search</button>
			</form>
		</div>
	</div>

	<!-- Dynamic College Table -->
	<div class="row">
		<div class="col-12">
			<table id="collegeTable" class="table table-striped table-bordered">
				<thead>
					<tr class="table-primary">
						<th>College ID</th>
						<th>College Name</th>
						<th>Address</th>
						<th>City</th>
						<th>State</th>
						<th>Zipcode</th>
						<th>Phone Number</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="college" items="${colleges}">
						<tr>
							<td class="align-middle bg-light">${college.collegeId}</td>
							<td class="align-middle bg-warning">${college.collegeName}</td>
							<td class="align-middle">${college.address}</td>
							<td class="align-middle bg-info">${college.city}</td>
							<td class="align-middle">${college.state}</td>
							<td class="align-middle bg-light">${college.zipcode}</td>
							<td class="align-middle bg-warning">${college.phoneNumber}</td>
							<td class="align-middle"><a
								href="/college/edit?collegeId=${college.collegeId}"> <i
									class="fas fa-edit"></i>Edit
							</a> | <a
								href="/college/deleteCollege?collegeId=${college.collegeId}"
								onclick="return confirm('Are you sure you want to delete this college?');">
									<i class="fas fa-trash-alt"></i>Delete
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="<c:url value='/js/collegesearch.js' />"></script>
	<jsp:include page="footer.jsp" />