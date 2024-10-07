document.getElementById('studentForm').addEventListener('submit', function(event) {
    clearErrors();
    let isValid = true;
    let submitSuccess = document.getElementById('successMessage');
    submitSuccess.textContent = '';

    // Existing fields validation
    let firstname = document.getElementById('firstname').value.trim();
    if (firstname === '') {
        document.getElementById('firstnameError').textContent = 'First Name is required';
        document.getElementById('firstname').classList.add('error-border');
        isValid = false;
    }

    let lastname = document.getElementById('lastname').value.trim();
    if (lastname === '') {
        document.getElementById('lastnameError').textContent = 'Last Name is required';
        document.getElementById('lastname').classList.add('error-border');
        isValid = false;
    }

    let fathername = document.getElementById('fathername').value.trim();
    if (fathername === '') {
        document.getElementById('fathernameError').textContent = 'Father\'s Name is required';
        document.getElementById('fathername').classList.add('error-border');
        isValid = false;
    }

    let rollNumber = document.getElementById('rollNumber').value.trim();
    if (rollNumber === '') {
        document.getElementById('rollNumberError').textContent = 'Roll Number is required';
        document.getElementById('rollNumber').classList.add('error-border');
        isValid = false;
    }

    let marks = document.getElementById('marks').value.trim();
    if (marks === '') {
        document.getElementById('marksError').textContent = 'Marks are required';
        document.getElementById('marks').classList.add('error-border');
        isValid = false;
    }

    let college = document.getElementById('college').value.trim();
    if (college === '') {
        document.getElementById('collegeError').textContent = 'College is required';
        document.getElementById('college').classList.add('error-border');
        isValid = false;
    }

    let branch = document.getElementById('branch').value.trim();
    if (branch === '') {
        document.getElementById('branchError').textContent = 'Branch is required';
        document.getElementById('branch').classList.add('error-border');
        isValid = false;
    }

    let dateOfBirth = document.getElementById('dateOfBirth').value.trim();
    if (dateOfBirth === '') {
        document.getElementById('dateOfBirthError').textContent = 'Date of Birth is required';
        document.getElementById('dateOfBirth').classList.add('error-border');
        isValid = false;
    }

    let mobileno = document.getElementById('mobileno').value.trim();
    if (mobileno === '') {
        document.getElementById('mobilenoError').textContent = 'Mobile Number is required';
        document.getElementById('mobileno').classList.add('error-border');
        isValid = false;
    }

    // New fields validation
    let studentCode = document.getElementById('studentCode').value.trim();
    if (studentCode === '') {
        document.getElementById('studentCodeError').textContent = 'Student Code is required';
        document.getElementById('studentCode').classList.add('error-border');
        isValid = false;
    }

    let address = document.getElementById('address').value.trim();
    if (address === '') {
        document.getElementById('addressError').textContent = 'Address is required';
        document.getElementById('address').classList.add('error-border');
        isValid = false;
    }

    let status = document.getElementById('status').value.trim();
    if (status === '') {
        document.getElementById('statusError').textContent = 'Status is required';
        document.getElementById('status').classList.add('error-border');
        isValid = false;
    }

    // Prevent form submission if validation fails
    if (!isValid) {
        event.preventDefault(); // Prevent form submission only if validation fails
    } else {
        // Form is valid, allow submission and show success message
        submitSuccess.textContent = 'Form submitted successfully!';
        document.getElementById('successMessage').style.display = 'block';

        // Optionally hide the message after 3 seconds
        setTimeout(function() {
            document.getElementById('successMessage').style.display = 'none';
        }, 10000);

        document.getElementById('submitButton').disabled = true;  
    }
});

// Clear error messages function
function clearErrors() {
    document.getElementById('firstnameError').textContent = '';
    document.getElementById('lastnameError').textContent = '';
    document.getElementById('fathernameError').textContent = '';
    document.getElementById('rollNumberError').textContent = '';
    document.getElementById('marksError').textContent = '';
    document.getElementById('collegeError').textContent = '';
    document.getElementById('branchError').textContent = '';
    document.getElementById('dateOfBirthError').textContent = '';
    document.getElementById('mobilenoError').textContent = '';
    document.getElementById('studentCodeError').textContent = '';
    document.getElementById('addressError').textContent = '';
    document.getElementById('statusError').textContent = '';

    // Remove error border classes
    const errorFields = ['firstname', 'lastname', 'fathername', 'rollNumber', 'marks', 'college', 'branch', 'dateOfBirth', 'mobileno', 'studentCode', 'address', 'status'];
    errorFields.forEach(field => {
        document.getElementById(field).classList.remove('error-border');
    });
}

// Remove error border and message when user starts typing
const fields = ['firstname', 'lastname', 'fathername', 'rollNumber', 'marks', 'college', 'branch', 'dateOfBirth', 'mobileno', 'studentCode', 'address', 'status'];

fields.forEach(field => {
    document.getElementById(field).addEventListener('input', function() {
        document.getElementById(field + 'Error').textContent = '';
        this.classList.remove('error-border');
    });
});
