<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="Header.jsp"%>
<%@ include file="sessionCheck.jsp"%>

<div class="container mt-4 form-container" style="margin-left: 240px;">
	<h2>Register a New Student</h2>

	<c:if test="${not empty databaseError}">
		<div class="alert alert-danger alert-dismissible fade show"
			role="alert">
			${databaseError}
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>

	<c:if test="${not empty invalidNumberError}">
		<div class="alert alert-danger alert-dismissible fade show"
			role="alert">
			${invalidNumberError}
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>

	<form id="studentForm" action="/student/submit" method="POST" enctype="multipart/form-data">
		<div class="row mb-3">
			<div class="col-md-6">
				<label for="studentCode" class="form-label">Student Code
					(Auto-incremented)</label> <input type="number" class="form-control"
					id="studentCode" name="studentCode"
					placeholder="Enter student code"> <span
					id="studentCodeError" class="text-danger"></span>
			</div>

			<div class="col-md-6">
				<label for="rollNumber" class="form-label">Roll Number</label> <input
					type="number" class="form-control" id="rollNumber"
					name="rollNumber" placeholder="Enter roll number"> <span
					id="rollNumberError" class="text-danger"></span>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-md-6">
				<label for="marks" class="form-label">Marks</label> <input
					type="number" class="form-control" id="marks" name="marks"
					placeholder="Enter marks"> <span id="marksError"
					class="text-danger"></span>
			</div>

			<div class="col-md-6">
				<label for="branch" class="form-label">Branch</label> <input
					type="text" class="form-control" id="branch" name="branch"
					placeholder="Enter branch"> <span id="branchError"
					class="text-danger"></span>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-md-6">
				<label for="college" class="form-label">College</label> <input
					type="text" class="form-control" id="college" name="college"
					placeholder="Enter college"> <span id="collegeError"
					class="text-danger"></span>
			</div>

			<div class="col-md-6">
				<label for="firstname" class="form-label">First Name</label> <input
					type="text" class="form-control" id="firstname" name="firstname"
					placeholder="Enter first name"> <span id="firstnameError"
					class="text-danger"></span>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-md-6">
				<label for="lastname" class="form-label">Last Name</label> <input
					type="text" class="form-control" id="lastname" name="lastname"
					placeholder="Enter last name"> <span id="lastnameError"
					class="text-danger"></span>
			</div>

			<div class="col-md-6">
				<label for="fathername" class="form-label">Father's Name</label> <input
					type="text" class="form-control" id="fathername" name="fathername"
					placeholder="Enter father's name"> <span
					id="fathernameError" class="text-danger"></span>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-md-6">
				<label for="mobileno" class="form-label">Mobile Number</label> <input
					type="text" class="form-control" id="mobileno" name="mobileno"
					placeholder="Enter mobile number"> <span id="mobilenoError"
					class="text-danger"></span>
			</div>

			<div class="col-md-6">
				<label for="dateOfBirth" class="form-label">Date of Birth</label> <input
					type="date" class="form-control" id="dateOfBirth"
					name="dateOfBirth"> <span id="dateOfBirthError"
					class="text-danger"></span>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-md-6">
				<label for="address" class="form-label">Address</label> <input
					type="text" class="form-control" id="address" name="address"
					placeholder="Enter address"> <span id="addressError"
					class="text-danger"></span>
			</div>

			<div class="col-md-6">
				<label for="status" class="form-label">Status</label> <select
					class="form-select" id="status" name="status">
					<option value="">Select Status</option>
					<option value="true">Active</option>
					<option value="false">Inactive</option>
				</select> <span id="statusError" class="text-danger"></span>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-12">
				<label for="profileImage" class="form-label">Profile Image
					(optional)</label> <input type="file" class="form-control"
					id="profileImage" name="profileImage">
			</div>
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
<script src="<c:url value='/js/studentform.js' />"></script>
<jsp:include page="footer.jsp" />
