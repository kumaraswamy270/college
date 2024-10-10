<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="Header.jsp"%>
<%@ include file="sessionCheck.jsp"%>

<div class="container">
	<h2>Register a New Student</h2>

	<c:if test="${not empty databaseError}">
		<div class="error">${databaseError}</div>
	</c:if>

	<div id="successMessage" class="success-message"></div>
	<form id="studentForm" action="StudentServlet" method="POST">
		<div class="form-row">
			<!-- Student Code -->
			<div class="input-field">
				<label for="studentCode">Student Code (Auto-incremented)</label> <input
					type="number" id="studentCode" name="studentCode"
					placeholder="Enter student code"> <span
					id="studentCodeError" class="error-message"></span>
			</div>

			<!-- Roll Number -->
			<div class="input-field">
				<label for="rollNumber">Roll Number</label> <input type="number"
					id="rollNumber" name="rollNumber" placeholder="Enter roll number">
				<span id="rollNumberError" class="error-message"></span>
			</div>
		</div>

		<div class="form-row">
			<!-- Marks -->
			<div class="input-field">
				<label for="marks">Marks</label> <input type="number" id="marks"
					name="marks" placeholder="Enter marks"> <span
					id="marksError" class="error-message"></span>
			</div>

			<!-- Branch -->
			<div class="input-field">
				<label for="branch">Branch</label> <input type="text" id="branch"
					name="branch" placeholder="Enter branch"> <span
					id="branchError" class="error-message"></span>
			</div>
		</div>

		<div class="form-row">
			<!-- College -->
			<div class="input-field">
				<label for="college">College</label> <input type="text" id="college"
					name="college" placeholder="Enter college"> <span
					id="collegeError" class="error-message"></span>
			</div>

			<!-- First Name -->
			<div class="input-field">
				<label for="firstname">First Name</label> <input type="text"
					id="firstname" name="firstname" placeholder="Enter first name">
				<span id="firstnameError" class="error-message"></span>
			</div>
		</div>

		<div class="form-row">
			<!-- Last Name -->
			<div class="input-field">
				<label for="lastname">Last Name</label> <input type="text"
					id="lastname" name="lastname" placeholder="Enter last name">
				<span id="lastnameError" class="error-message"></span>
			</div>

			<!-- Father Name -->
			<div class="input-field">
				<label for="fathername">Father's Name</label> <input type="text"
					id="fathername" name="fathername" placeholder="Enter father's name">
				<span id="fathernameError" class="error-message"></span>
			</div>
		</div>

		<div class="form-row">
			<!-- Mobile Number -->
			<div class="input-field">
				<label for="mobileno">Mobile Number</label> <input type="text"
					id="mobileno" name="mobileno" placeholder="Enter mobile number">
				<span id="mobilenoError" class="error-message"></span>
			</div>

			<!-- Date of Birth -->
			<div class="input-field">
				<label for="dateOfBirth">Date of Birth</label> <input type="date"
					id="dateOfBirth" name="dateOfBirth"> <span
					id="dateOfBirthError" class="error-message"></span>
			</div>
		</div>

		<div class="form-row">
			<!-- Address -->
			<div class="input-field">
				<label for="address">Address</label> <input type="text" id="address"
					name="address" placeholder="Enter address"> <span
					id="addressError" class="error-message"></span>
			</div>

			<!-- Status (Active/Inactive) -->
			<div class="input-field">
				<label for="status">Status</label> <select id="status" name="status">
					<option value="">Select Status</option>
					<option value="true">Active</option>
					<option value="false">Inactive</option>
				</select> <span id="statusError" class="error-message"></span>
			</div>
		</div>

		<div class="form-row">
			<!-- Profile Image -->
			<div class="input-field full-width">
				<label for="profileImage">Profile Image (optional)</label> <input
					type="file" id="profileImage" name="profileImage">
			</div>
		</div>

		<button id="submitButton" type="submit">Submit</button>
	</form>
</div>
<script src="js/studentform.js"></script>
<jsp:include page="footer.jsp" />