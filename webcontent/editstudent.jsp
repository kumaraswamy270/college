<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="Header.jsp"%>


<div class="container">
	<h2>Edit Student Information</h2>

	<!-- Display error message if exists -->
	<div id="errorMessage" class="error-message">
		<%=request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : ""%>
	</div>

	<form id="editStudentForm" action="UpdateServlet" method="post">
		<!-- Hidden Field for Student ID -->
		<input type="hidden" name="id" value="${student.rollnumber}">

		<div class="form-row">
			<!-- Student Code -->
			<div class="input-field">
				<label for="studentCode">Student Code</label> <input type="number"
					id="studentCode" name="studentCode"
					value="${not empty errorMessage ? studentcode : (student.studentcode != null ? student.studentcode : '')}"
					placeholder="Enter student code"> <span
					id="studentCodeError" class="error-message"> <%=request.getAttribute("studentCodeError") != null ? request.getAttribute("studentCodeError") : ""%>
				</span>
			</div>

			<!-- Roll Number -->
			<div class="input-field">
				<label for="rollNumber">Roll Number</label> <input type="number"
					id="rollNumber" name="rollNumber"
					value="${not empty errorMessage ? rollnumber : (student.rollnumber != null ? student.rollnumber : '')}"
					placeholder="Enter roll number"> <span id="rollNumberError"
					class="error-message"> <%=request.getAttribute("rollNumberError") != null ? request.getAttribute("rollNumberError") : ""%>
				</span>
			</div>
		</div>

		<div class="form-row">
			<!-- Marks -->
			<div class="input-field">
				<label for="marks">Marks</label> <input type="number" id="marks"
					name="marks"
					value="${not empty errorMessage ? marks : (student.marks != null ? student.marks : '')}"
					placeholder="Enter marks"> <span id="marksError"
					class="error-message"> <%=request.getAttribute("marksError") != null ? request.getAttribute("marksError") : ""%>
				</span>
			</div>

			<!-- Branch -->
			<div class="input-field">
				<label for="branch">Branch</label> <input type="text" id="branch"
					name="branch"
					value="${not empty errorMessage ? branch : (student.branch != null ? student.branch : '')}"
					placeholder="Enter branch"> <span id="branchError"
					class="error-message"> <%=request.getAttribute("branchError") != null ? request.getAttribute("branchError") : ""%>
				</span>
			</div>
		</div>

		<div class="form-row">
			<!-- College -->
			<div class="input-field">
				<label for="college">College</label> <input type="text" id="college"
					name="college"
					value="${not empty errorMessage ? college : (student.college != null ? student.college : '')}"
					placeholder="Enter college"> <span id="collegeError"
					class="error-message"> <%=request.getAttribute("collegeError") != null ? request.getAttribute("collegeError") : ""%>
				</span>
			</div>

			<!-- First Name -->
			<div class="input-field">
				<label for="firstname">First Name</label> <input type="text"
					id="firstname" name="firstname"
					value="${not empty errorMessage ? firstname : (student.firstname != null ? student.firstname : '')}"
					placeholder="Enter first name"> <span id="firstnameError"
					class="error-message"> <%=request.getAttribute("firstnameError") != null ? request.getAttribute("firstnameError") : ""%>
				</span>
			</div>
		</div>

		<div class="form-row">
			<!-- Last Name -->
			<div class="input-field">
				<label for="lastname">Last Name</label> <input type="text"
					id="lastname" name="lastname"
					value="${not empty errorMessage ? lastname : (student.lastname != null ? student.lastname : '')}"
					placeholder="Enter last name"> <span id="lastnameError"
					class="error-message"> <%=request.getAttribute("lastnameError") != null ? request.getAttribute("lastnameError") : ""%>
				</span>
			</div>

			<!-- Father's Name -->
			<div class="input-field">
				<label for="fathername">Father's Name</label> <input type="text"
					id="fathername" name="fathername"
					value="${not empty errorMessage ? fathername : (student.fathername != null ? student.fathername : '')}"
					placeholder="Enter father's name"> <span
					id="fathernameError" class="error-message"> <%=request.getAttribute("fathernameError") != null ? request.getAttribute("fathernameError") : ""%>
				</span>
			</div>
		</div>

		<div class="form-row">
			<!-- Mobile Number -->
			<div class="input-field">
				<label for="mobileno">Mobile Number</label> <input type="text"
					id="mobileno" name="mobileno"
					value="${not empty errorMessage ? mobileno : (student.mobileno != null ? student.mobileno : '')}"
					placeholder="Enter mobile number"> <span id="mobilenoError"
					class="error-message"> <%=request.getAttribute("mobilenoError") != null ? request.getAttribute("mobilenoError") : ""%>
				</span>
			</div>

			<!-- Date of Birth -->
			<div class="input-field">
				<label for="dateOfBirth">Date of Birth</label> <input type="date"
					id="dateOfBirth" name="dateOfBirth"
					value="${not empty errorMessage ? dateofbirth : (student.dateofbirth != null ? student.dateofbirth : '')}">
				<span id="dateOfBirthError" class="error-message"> <%=request.getAttribute("dateOfBirthError") != null ? request.getAttribute("dateOfBirthError") : ""%>
				</span>
			</div>
		</div>

		<div class="form-row">
			<!-- Address -->
			<div class="input-field">
				<label for="address">Address</label> <input type="text" id="address"
					name="address"
					value="${not empty errorMessage ? address : (student.address != null ? student.address : '')}"
					placeholder="Enter address"> <span id="addressError"
					class="error-message"> <%=request.getAttribute("addressError") != null ? request.getAttribute("addressError") : ""%>
				</span>
			</div>

			<!-- Status (Active/Inactive) -->
			<div class="input-field">
				<label for="status">Status</label> <select id="status" name="status">
					<option value="">Select Status</option>
					<option value="true"
						${not empty errorMessage ? (status == 'true' ? 'selected' : '') : (student.status == 'true' ? 'selected' : '')}>Active</option>
					<option value="false"
						${not empty errorMessage ? (status == 'false' ? 'selected' : '') : (student.status == 'false' ? 'selected' : '')}>Inactive</option>
				</select> <span id="statusError" class="error-message"> <%=request.getAttribute("statusError") != null ? request.getAttribute("statusError") : ""%>
				</span>
			</div>
		</div>

		<!-- Save and Cancel Buttons -->
		<button type="submit" id="submitButton">Save</button>
		<button type="button" id="cancelButton"
			onclick="window.location.href='GetAllStudentsServlet'">Cancel</button>
	</form>
</div>

<script src="edit.js"></script>
<jsp:include page="footer.jsp" />
