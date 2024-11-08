<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="Header.jsp"%>
<%@ include file="sessionCheck.jsp"%>

<div class="container mt-4 form-container" style="margin-left: 240px;">
	<h2>Register a New Course</h2>

	<c:if test="${not empty databaseError}">
		<div class="alert alert-danger alert-dismissible fade show"
			role="alert">
			${databaseError}
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>

	<form id="courseForm" action="CourseServlet" method="POST">
		<div class="row mb-3">
			<div class="col-md-6">
				<label for="courseId" class="form-label">Course ID</label> <input
					type="text" class="form-control" id="courseId" name="courseId"
					placeholder="Enter course ID"> <span id="courseIdError"
					class="text-danger"></span>
			</div>

			<div class="col-md-6">
				<label for="courseName" class="form-label">Course Name</label> <input
					type="text" class="form-control" id="courseName" name="courseName"
					placeholder="Enter course name"> <span id="courseNameError"
					class="text-danger"></span>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-md-6">
				<label for="credits" class="form-label">Credits</label> <input
					type="number" class="form-control" id="credits" name="credits"
					placeholder="Enter number of credits"> <span
					id="creditsError" class="text-danger"></span>
			</div>

			<div class="col-md-6">
				<label for="department" class="form-label">Department</label> <input
					type="text" class="form-control" id="department" name="department"
					placeholder="Enter department"> <span id="departmentError"
					class="text-danger"></span>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-md-6">
				<label for="duration" class="form-label">Duration (weeks)</label> <input
					type="number" class="form-control" id="duration" name="duration"
					placeholder="Enter duration in weeks"> <span
					id="durationError" class="text-danger"></span>
			</div>

			<div class="col-md-6">
				<label for="feeStructure" class="form-label">Fee Structure</label> <input
					type="text" class="form-control" id="feeStructure"
					name="feeStructure" placeholder="Enter fee structure"> <span
					id="feeStructureError" class="text-danger"></span>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-md-6">
				<label for="lengthOfStudents" class="form-label">Number of
					Students</label> <input type="number" class="form-control"
					id="lengthOfStudents" name="lengthOfStudents"
					placeholder="Enter number of students"> <span
					id="lengthOfStudentsError" class="text-danger"></span>
			</div>

			<div class="col-md-6">
				<label for="startDate" class="form-label">Start Date</label> <input
					type="date" class="form-control" id="startDate" name="startDate">
				<span id="startDateError" class="text-danger"></span>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-md-6">
				<label for="endDate" class="form-label">End Date</label> <input
					type="date" class="form-control" id="endDate" name="endDate">
				<span id="endDateError" class="text-danger"></span>
			</div>

			<div class="row mb-3">
				<div class="col-12">
					<button id="submitButton" type="submit" class="btn btn-primary">Submit</button>
				</div>
			</div>
	</form>
</div>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<c:url value='/js/courseform.js' />"></script>
<jsp:include page="footer.jsp" />
