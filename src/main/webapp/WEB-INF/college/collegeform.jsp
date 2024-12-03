<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="Header.jsp"%>
<%@ include file="sessionCheck.jsp"%>

<div class="container mt-4 form-container" style="margin-left: 240px;">
	<h2>Register a New College</h2>

	<c:if test="${not empty databaseError}">
		<div class="alert alert-danger alert-dismissible fade show"
			role="alert">
			${databaseError}
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>

	<form id="collegeForm" action="/college/submit" method="POST">
		<div class="row mb-3">
			<div class="col-md-6">
				<label for="collegeId" class="form-label">College ID</label>
				<input type="number" class="form-control" id="collegeId" name="collegeId"
					placeholder="Enter college ID">
				<span id="collegeIdError" class="text-danger"></span>
			</div>

			<div class="col-md-6">
				<label for="collegeName" class="form-label">College Name</label>
				<input type="text" class="form-control" id="collegeName" name="collegeName"
					placeholder="Enter college name">
				<span id="collegeNameError" class="text-danger"></span>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-md-6">
				<label for="address" class="form-label">Address</label>
				<input type="text" class="form-control" id="address" name="address"
					placeholder="Enter address">
				<span id="addressError" class="text-danger"></span>
			</div>

			<div class="col-md-6">
				<label for="city" class="form-label">City</label>
				<input type="text" class="form-control" id="city" name="city"
					placeholder="Enter city">
				<span id="cityError" class="text-danger"></span>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-md-6">
				<label for="state" class="form-label">State</label>
				<input type="text" class="form-control" id="state" name="state"
					placeholder="Enter state">
				<span id="stateError" class="text-danger"></span>
			</div>

			<div class="col-md-6">
				<label for="zipcode" class="form-label">Zip Code</label>
				<input type="text" class="form-control" id="zipcode" name="zipcode"
					placeholder="Enter zip code">
				<span id="zipcodeError" class="text-danger"></span>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-md-6">
				<label for="phoneNumber" class="form-label">Phone Number</label>
				<input type="text" class="form-control" id="phoneNumber" name="phoneNumber"
					placeholder="Enter phone number">
				<span id="phoneNumberError" class="text-danger"></span>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-12">
				<button id="submitButton" type="submit" class="btn btn-primary">Submit</button>
			</div>
		</div>
	</form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<c:url value='/js/collegeform.js' />"></script>
<jsp:include page="footer.jsp" />
