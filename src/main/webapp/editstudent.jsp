<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="Header.jsp"%>

<div class="container mt-4">
	<h2>Edit Student Information</h2>

	<!-- Display error message if exists -->
	<!-- Display error message if exists -->
	<div id="errorMessage" class="alert alert-danger" role="alert">
		<c:out value="${requestScope.errorMessage}" />
	</div>

	<form id="editStudentForm" action="UpdateServlet" method="post">
		<!-- Hidden Field for Student ID -->
		<input type="hidden" name="id" value="${student.rollnumber}">

		<div class="row mb-3">
			<!-- Student Code -->
			<div class="col-md-6">
				<label for="studentCode" class="form-label">Student Code</label> <input
					type="number" class="form-control" id="studentCode"
					name="studentCode"
					value="${not empty errorMessage ? studentcode : (student.studentcode != null ? student.studentcode : '')}"
					placeholder="Enter student code">
				<div id="studentCodeError" class="text-danger">
					<c:out value="${requestScope.studentCodeError}" />
				</div>
			</div>

			<!-- Roll Number -->
			<div class="col-md-6">
				<label for="rollNumber" class="form-label">Roll Number</label> <input
					type="number" class="form-control" id="rollNumber"
					name="rollNumber"
					value="${not empty errorMessage ? rollnumber : (student.rollnumber != null ? student.rollnumber : '')}"
					placeholder="Enter roll number">
				<div id="rollNumberError" class="text-danger">
					<c:out value="${requestScope.rollNumberError}" />
				</div>
			</div>
		</div>

		<div class="row mb-3">
			<!-- Marks -->
			<div class="col-md-6">
				<label for="marks" class="form-label">Marks</label> <input
					type="number" class="form-control" id="marks" name="marks"
					value="${not empty errorMessage ? marks : (student.marks != null ? student.marks : '')}"
					placeholder="Enter marks">
				<div id="marksError" class="text-danger">
					<c:out value="${requestScope.marksError}" />
				</div>
			</div>

			<!-- Branch -->
			<div class="col-md-6">
				<label for="branch" class="form-label">Branch</label> <input
					type="text" class="form-control" id="branch" name="branch"
					value="${not empty errorMessage ? branch : (student.branch != null ? student.branch : '')}"
					placeholder="Enter branch">
				<div id="branchError" class="text-danger">
					<c:out value="${requestScope.branchError}" />
				</div>
			</div>
		</div>

		<div class="row mb-3">
			<!-- College -->
			<div class="col-md-6">
				<label for="college" class="form-label">College</label> <input
					type="text" class="form-control" id="college" name="college"
					value="${not empty errorMessage ? college : (student.college != null ? student.college : '')}"
					placeholder="Enter college">
				<div id="collegeError" class="text-danger">
					<c:out value="${requestScope.collegeError}" />
				</div>
			</div>

			<!-- First Name -->
			<div class="col-md-6">
				<label for="firstname" class="form-label">First Name</label> <input
					type="text" class="form-control" id="firstname" name="firstname"
					value="${not empty errorMessage ? firstname : (student.firstname != null ? student.firstname : '')}"
					placeholder="Enter first name">
				<div id="firstnameError" class="text-danger">
					<c:out value="${requestScope.firstnameError}" />
				</div>
			</div>
		</div>

		<div class="row mb-3">
			<!-- Last Name -->
			<div class="col-md-6">
				<label for="lastname" class="form-label">Last Name</label> <input
					type="text" class="form-control" id="lastname" name="lastname"
					value="${not empty errorMessage ? lastname : (student.lastname != null ? student.lastname : '')}"
					placeholder="Enter last name">
				<div id="lastnameError" class="text-danger">
					<c:out value="${requestScope.lastnameError}" />
				</div>
			</div>

			<!-- Father's Name -->
			<div class="col-md-6">
				<label for="fathername" class="form-label">Father's Name</label> <input
					type="text" class="form-control" id="fathername" name="fathername"
					value="${not empty errorMessage ? fathername : (student.fathername != null ? student.fathername : '')}"
					placeholder="Enter father's name">
				<div id="fathernameError" class="text-danger">
					<c:out value="${requestScope.fathernameError}" />
				</div>
			</div>
		</div>

		<div class="row mb-3">
			<!-- Mobile Number -->
			<div class="col-md-6">
				<label for="mobileno" class="form-label">Mobile Number</label> <input
					type="text" class="form-control" id="mobileno" name="mobileno"
					value="${not empty errorMessage ? mobileno : (student.mobileno != null ? student.mobileno : '')}"
					placeholder="Enter mobile number">
				<div id="mobilenoError" class="text-danger">
					<c:out value="${requestScope.mobilenoError}" />
				</div>
			</div>

			<!-- Date of Birth -->
			<div class="col-md-6">
				<label for="dateOfBirth" class="form-label">Date of Birth</label> <input
					type="date" class="form-control" id="dateOfBirth"
					name="dateOfBirth"
					value="${not empty errorMessage ? dateofbirth : (student.dateofbirth != null ? student.dateofbirth : '')}">
				<div id="dateOfBirthError" class="text-danger">
					<c:out value="${requestScope.dateOfBirthError}" />
				</div>
			</div>
		</div>

		<div class="row mb-3">
			<!-- Address -->
			<div class="col-md-6">
				<label for="address" class="form-label">Address</label> <input
					type="text" class="form-control" id="address" name="address"
					value="${not empty errorMessage ? address : (student.address != null ? student.address : '')}"
					placeholder="Enter address">
				<div id="addressError" class="text-danger">
					<c:out value="${requestScope.addressError}" />
				</div>
			</div>

			<!-- Status (Active/Inactive) -->
			<div class="col-md-6">
				<label for="status" class="form-label">Status</label> <select
					class="form-select" id="status" name="status">
					<option value="">Select Status</option>
					<option value="true"
						${not empty errorMessage ? (status == 'true' ? 'selected' : '') : (student.status == 'true' ? 'selected' : '')}>Active</option>
					<option value="false"
						${not empty errorMessage ? (status == 'false' ? 'selected' : '') : (student.status == 'false' ? 'selected' : '')}>Inactive</option>
				</select>
				<div id="statusError" class="text-danger">
					<c:out value="${requestScope.statusError}" />
				</div>
			</div>
		</div>

		<div class="d-flex justify-content-between">
			<!-- Save and Cancel Buttons -->
			<button id="submitButton" type="submit" class="btn btn-primary">Save</button>
			<button type="button" id="cancelButton" class="btn btn-secondary"
				onclick="window.location.href='GetAllStudentsServlet'">Cancel</button>
		</div>
	</form>
</div>

<script src="<c:url value='/js/edit.js' />"></script>
<jsp:include page="footer.jsp" />
