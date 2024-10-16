$(document).ready(function() {
	$("#loginForm").on("submit", function(event) {
		let isValid = true;

		// Clear previous error messages
		$("#usernameError").text("");
		$("#passwordError").text("");

		// Get values
		const username = $("#username").val().trim();
		const password = $("#password").val().trim();

		// Validate username
		if (username === "") {
			isValid = false;
			$("#usernameError").text("Username is required.");
			$("#username").addClass("error-border");
		} else {
			$("#username").removeClass("error-border");
		}

		// Validate password
		if (password === "") {
			isValid = false;
			$("#passwordError").text("Password is required.");
			$("#password").addClass("error-border");
		} else {
			$("#password").removeClass("error-border");
		}

		// If validation fails, prevent form submission
		if (!isValid) {
			event.preventDefault(); // Prevent form submission only if
									// validation fails
		}
	});

	// Add event listeners to input fields to clear error messages on input
	$("#username").on("input", function() {
		$("#usernameError").text(""); // Clear error message
		$(this).removeClass("error-border"); // Remove error border
	});

	$("#password").on("input", function() {
		$("#passwordError").text(""); // Clear error message
		$(this).removeClass("error-border"); // Remove error border
	});
});
