<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="Header.jsp"%>

<div class="container mt-4">
	<h2>Edit College Information</h2>

	<!-- Display error message if exists -->
	<div id="errorMessage" class="alert alert-danger" role="alert">
		<c:out value="${requestScope.errorMessage}" />
	</div>

	<form id="editCollegeForm" action="/college/update" method="post">
		<div class="col-md-6">
			<label for="collegeId" class="form-label">College ID</label> <input
				type="number" class="form-control" id="collegeId" name="collegeId"
				value="${college.collegeId != null ? college.collegeId : ''}"
				placeholder="Enter College ID">
			<div id="collegeIdError" class="text-danger">
				<c:out value="${requestScope.collegeIdError}" />
			</div>
		</div>

		<div class="row mb-3">
			<!-- College Name -->
			<div class="col-md-6">
				<label for="collegeName" class="form-label">College Name</label> <input
					type="text" class="form-control" id="collegeName"
					name="collegeName"
					value="${not empty errorMessage ? collegeName : (college.collegeName != null ? college.collegeName : '')}"
					placeholder="Enter College Name">
				<div id="collegeNameError" class="text-danger">
					<c:out value="${requestScope.collegeNameError}" />
				</div>
			</div>

			<!-- Address -->
			<div class="col-md-6">
				<label for="address" class="form-label">Address</label> <input
					type="text" class="form-control" id="address" name="address"
					value="${not empty errorMessage ? address : (college.address != null ? college.address : '')}"
					placeholder="Enter Address">
				<div id="addressError" class="text-danger">
					<c:out value="${requestScope.addressError}" />
				</div>
			</div>
		</div>

		<div class="row mb-3">
			<!-- City -->
			<div class="col-md-6">
				<label for="city" class="form-label">City</label> <input type="text"
					class="form-control" id="city" name="city"
					value="${not empty errorMessage ? city : (college.city != null ? college.city : '')}"
					placeholder="Enter City">
				<div id="cityError" class="text-danger">
					<c:out value="${requestScope.cityError}" />
				</div>
			</div>

			<!-- State -->
			<div class="col-md-6">
				<label for="state" class="form-label">State</label> <input
					type="text" class="form-control" id="state" name="state"
					value="${not empty errorMessage ? state : (college.state != null ? college.state : '')}"
					placeholder="Enter State">
				<div id="stateError" class="text-danger">
					<c:out value="${requestScope.stateError}" />
				</div>
			</div>
		</div>

		<div class="row mb-3">
			<!-- Zipcode -->
			<div class="col-md-6">
				<label for="zipcode" class="form-label">Zipcode</label> <input
					type="text" class="form-control" id="zipcode" name="zipcode"
					value="${not empty errorMessage ? zipcode : (college.zipcode != null ? college.zipcode : '')}"
					placeholder="Enter Zipcode">
				<div id="zipcodeError" class="text-danger">
					<c:out value="${requestScope.zipcodeError}" />
				</div>
			</div>

			<!-- Phone Number -->
			<div class="col-md-6">
				<label for="phoneNumber" class="form-label">Phone Number</label> <input
					type="text" class="form-control" id="phoneNumber"
					name="phoneNumber"
					value="${not empty errorMessage ? phoneNumber : (college.phoneNumber != null ? college.phoneNumber : '')}"
					placeholder="Enter Phone Number">
				<div id="phoneNumberError" class="text-danger">
					<c:out value="${requestScope.phoneNumberError}" />
				</div>
			</div>
		</div>

		<div class="d-flex justify-content-between">
			<!-- Save and Cancel Buttons -->
			<button id="submitButton" type="submit" class="btn btn-primary">Save</button>
			<button type="button" id="cancelButton" class="btn btn-secondary"
				onclick="window.location.href='/college/list'">Cancel</button>
		</div>
	</form>
</div>

<jsp:include page="footer.jsp" />
