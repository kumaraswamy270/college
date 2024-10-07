<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="Header.jsp"%>
<div class="container">
	<h2>Register a New College</h2>
	<form id="collegeForm" action="CollegeServlet" method="POST">
		<!-- College ID -->
		<label for="collegeId">College ID</label> <input type="number"
			id="collegeId" name="collegeId" placeholder="Enter college ID">
		<span id="collegeIdError" class="error-message"></span><br>

		<!-- College Name -->
		<label for="collegeName">College Name</label> <input type="text"
			id="collegeName" name="collegeName" placeholder="Enter college name">
		<span id="collegeNameError" class="error-message"></span><br>

		<!-- Address -->
		<label for="address">Address</label> <input type="text" id="address"
			name="address" placeholder="Enter address"> <span
			id="addressError" class="error-message"></span><br>

		<!-- City -->
		<label for="city">City</label> <input type="text" id="city"
			name="city" placeholder="Enter city"> <span id="cityError"
			class="error-message"></span><br>

		<!-- State -->
		<label for="state">State</label> <input type="text" id="state"
			name="state" placeholder="Enter state"> <span id="stateError"
			class="error-message"></span><br>

		<!-- Zip Code -->
		<label for="zipcode">Zip Code</label> <input type="text" id="zipcode"
			name="zipcode" placeholder="Enter zip code"> <span
			id="zipcodeError" class="error-message"></span><br>

		<!-- Phone Number -->
		<label for="phoneNumber">Phone Number</label> <input type="text"
			id="phoneNumber" name="phoneNumber" placeholder="Enter phone number">
		<span id="phoneNumberError" class="error-message"></span><br>

		<div class="submit-button">
			<input type="submit" value="Submit">
		</div>
	</form>
</div>
<script src="collegeform.js"></script>
<jsp:include page="footer.jsp" />
