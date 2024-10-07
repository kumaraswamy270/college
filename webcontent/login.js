document.getElementById("loginForm").addEventListener("submit", function(event) {
    // Optional: Remove clearErrors() since you are already clearing errors manually
    // clearErrors(); 

    let isValid = true;
    

    // Clear previous error messages
    document.getElementById("usernameError").innerText = "";
    document.getElementById("passwordError").innerText = "";

    // Get values
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    // Validate username
    if (username === "") {
        isValid = false;
        document.getElementById("usernameError").innerText = "Username is required.";
        document.getElementById("username").classList.add("error-border");
    } else {
        document.getElementById("username").classList.remove("error-border");
    }

    // Validate password
    if (password === "") {
        isValid = false;
        document.getElementById("passwordError").innerText = "Password is required.";
        document.getElementById("password").classList.add("error-border");
    } else {
        document.getElementById("password").classList.remove("error-border");
    }

    // If validation fails, prevent form submission
    if (!isValid) {
        event.preventDefault(); // Prevent form submission only if validation fails
    }
});

// Add event listeners to input fields to clear error messages on input
document.getElementById("username").addEventListener("input", function() {
    document.getElementById("usernameError").innerText = ""; // Clear error message
    this.classList.remove("error-border"); // Remove error border
});

document.getElementById("password").addEventListener("input", function() {
    document.getElementById("passwordError").innerText = ""; // Clear error message
    this.classList.remove("error-border"); // Remove error border
});
