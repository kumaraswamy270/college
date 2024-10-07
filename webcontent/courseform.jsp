<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="Header.jsp"%>
<div class="container">
	<h2>Register a New Course</h2>
	<form id="courseForm" action="CourseServlet" method="POST">
		<label for="courseId">Course ID</label> <input type="text"
			id="courseId" name="courseId" placeholder="Enter course ID">
		<span id="courseIdError" class="error-message"></span><br> <label
			for="courseName">Course Name</label> <input type="text"
			id="courseName" name="courseName" placeholder="Enter course name">
		<span id="courseNameError" class="error-message"></span><br> <label
			for="credits">Credits</label> <input type="number" id="credits"
			name="credits" placeholder="Enter number of credits"> <span
			id="creditsError" class="error-message"></span><br> <label
			for="department">Department</label> <input type="text"
			id="department" name="department" placeholder="Enter department">
		<span id="departmentError" class="error-message"></span><br> <label
			for="duration">Duration (weeks)</label> <input type="number"
			id="duration" name="duration" placeholder="Enter duration in weeks">
		<span id="durationError" class="error-message"></span><br> <label
			for="feeStructure">Fee Structure</label> <input type="text"
			id="feeStructure" name="feeStructure"
			placeholder="Enter fee structure"> <span
			id="feeStructureError" class="error-message"></span><br> <label
			for="lengthOfStudents">Number of Students</label> <input
			type="number" id="lengthOfStudents" name="lengthOfStudents"
			placeholder="Enter number of students"> <span
			id="lengthOfStudentsError" class="error-message"></span><br> <label
			for="startDate">Start Date</label> <input type="date" id="startDate"
			name="startDate"> <span id="startDateError"
			class="error-message"></span><br> <label for="endDate">End
			Date</label> <input type="date" id="endDate" name="endDate"> <span
			id="endDateError" class="error-message"></span><br> <label
			for="courseType">Course Type</label> <select id="courseType"
			name="courseType">
			<option value="" disabled selected>Select course type</option>
			<option value="online">Online</option>
			<option value="offline">Offline</option>
			<option value="hybrid">Hybrid</option>
		</select> <span id="courseTypeError" class="error-message"></span><br> <label
			for="image">Image (optional)</label> <input type="file" id="image"
			name="image"><br>

		<div class="submit-button">
			<input type="submit" value="Submit">
		</div>
	</form>
</div>

<script src="courseform.js"></script>
<jsp:include page="footer.jsp" />
