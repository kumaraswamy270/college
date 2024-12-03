<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="Header.jsp"%>

<div class="container mt-4">
	<h2>Edit Course Information</h2>

	<!-- Display error message if exists -->
	<div id="errorMessage" class="alert alert-danger" role="alert">
		<c:out value="${requestScope.errorMessage}" />
	</div>

	<form id="editCourseForm" action="/course/update" method="post">
		<div class="col-md-6">
			<label for="courseId" class="form-label">Course ID</label> <input
				type="number" class="form-control" id="courseId" name="courseId"
				value="${course.courseId != null ? course.courseId : ''}"
				placeholder="Enter Course ID">
			<div id="courseIdError" class="text-danger">
				<c:out value="${requestScope.courseIdError}" />
			</div>
		</div>

		<div class="row mb-3">
			<!-- Course Name -->
			<div class="col-md-6">
				<label for="courseName" class="form-label">Course Name</label> <input
					type="text" class="form-control" id="courseName" name="courseName"
					value="${not empty errorMessage ? courseName : (course.courseName != null ? course.courseName : '')}"
					placeholder="Enter course name">
				<div id="courseNameError" class="text-danger">
					<c:out value="${requestScope.courseNameError}" />
				</div>
			</div>

			<!-- Credits -->
			<div class="col-md-6">
				<label for="credits" class="form-label">Credits</label> <input
					type="number" class="form-control" id="credits" name="credits"
					value="${not empty errorMessage ? credits : (course.credits != null ? course.credits : '')}"
					placeholder="Enter credits">
				<div id="creditsError" class="text-danger">
					<c:out value="${requestScope.creditsError}" />
				</div>
			</div>
		</div>

		<div class="row mb-3">
			<!-- Department -->
			<div class="col-md-6">
				<label for="department" class="form-label">Department</label> <input
					type="text" class="form-control" id="department" name="department"
					value="${not empty errorMessage ? department : (course.department != null ? course.department : '')}"
					placeholder="Enter department">
				<div id="departmentError" class="text-danger">
					<c:out value="${requestScope.departmentError}" />
				</div>
			</div>

			<!-- Duration -->
			<div class="col-md-6">
				<label for="duration" class="form-label">Duration</label> <input
					type="text" class="form-control" id="duration" name="duration"
					value="${not empty errorMessage ? duration : (course.duration != null ? course.duration : '')}"
					placeholder="Enter duration">
				<div id="durationError" class="text-danger">
					<c:out value="${requestScope.durationError}" />
				</div>
			</div>
		</div>

		<div class="row mb-3">
			<!-- Fee Structure -->
			<div class="col-md-6">
				<label for="feeStructure" class="form-label">Fee Structure</label> <input
					type="text" class="form-control" id="feeStructure"
					name="feeStructure"
					value="${not empty errorMessage ? feeStructure : (course.feeStructure != null ? course.feeStructure : '')}"
					placeholder="Enter fee structure">
				<div id="feeStructureError" class="text-danger">
					<c:out value="${requestScope.feeStructureError}" />
				</div>
			</div>

			<!-- Length of Students -->
			<div class="col-md-6">
				<label for="lengthOfStudents" class="form-label">Length of
					Students</label> <input type="number" class="form-control"
					id="lengthOfStudents" name="lengthOfStudents"
					value="${not empty errorMessage ? lengthOfStudents : (course.lengthOfStudents != null ? course.lengthOfStudents : '')}"
					placeholder="Enter length of students">
				<div id="lengthOfStudentsError" class="text-danger">
					<c:out value="${requestScope.lengthOfStudentsError}" />
				</div>
			</div>
		</div>

		<div class="row mb-3">
			<!-- Start Date -->
			<div class="col-md-6">
				<label for="startDate" class="form-label">Start Date</label> <input
					type="date" class="form-control" id="startDate" name="startDate"
					value="${not empty errorMessage ? startDate : (course.startDate != null ? course.startDate : '')}">
				<div id="startDateError" class="text-danger">
					<c:out value="${requestScope.startDateError}" />
				</div>
			</div>

			<!-- End Date -->
			<div class="col-md-6">
				<label for="endDate" class="form-label">End Date</label> <input
					type="date" class="form-control" id="endDate" name="endDate"
					value="${not empty errorMessage ? endDate : (course.endDate != null ? course.endDate : '')}">
				<div id="endDateError" class="text-danger">
					<c:out value="${requestScope.endDateError}" />
				</div>
			</div>
		</div>

		<div class="d-flex justify-content-between">
			<!-- Save and Cancel Buttons -->
			<button id="submitButton" type="submit" class="btn btn-primary">Save</button>
			<button type="button" id="cancelButton" class="btn btn-secondary"
				onclick="window.location.href='/course/list'">Cancel</button>
		</div>
	</form>
</div>

<jsp:include page="footer.jsp" />
