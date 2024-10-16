$(document).ready(function() {
    $('#studentForm').on('submit', function(event) {
        clearErrors();
        let isValid = true;

        // Existing fields validation
        let firstname = $('#firstname').val().trim();
        if (firstname === '') {
            $('#firstnameError').text('First Name is required');
            $('#firstname').addClass('error-border');
            isValid = false;
        }

        let lastname = $('#lastname').val().trim();
        if (lastname === '') {
            $('#lastnameError').text('Last Name is required');
            $('#lastname').addClass('error-border');
            isValid = false;
        }

        let fathername = $('#fathername').val().trim();
        if (fathername === '') {
            $('#fathernameError').text('Father\'s Name is required');
            $('#fathername').addClass('error-border');
            isValid = false;
        }

        let rollNumber = $('#rollNumber').val().trim();
        if (rollNumber === '') {
            $('#rollNumberError').text('Roll Number is required');
            $('#rollNumber').addClass('error-border');
            isValid = false;
        }

        let marks = $('#marks').val().trim();
        if (marks === '') {
            $('#marksError').text('Marks are required');
            $('#marks').addClass('error-border');
            isValid = false;
        }

        let college = $('#college').val().trim();
        if (college === '') {
            $('#collegeError').text('College is required');
            $('#college').addClass('error-border');
            isValid = false;
        }

        let branch = $('#branch').val().trim();
        if (branch === '') {
            $('#branchError').text('Branch is required');
            $('#branch').addClass('error-border');
            isValid = false;
        }

        let dateOfBirth = $('#dateOfBirth').val().trim();
        if (dateOfBirth === '') {
            $('#dateOfBirthError').text('Date of Birth is required');
            $('#dateOfBirth').addClass('error-border');
            isValid = false;
        }

        let mobileno = $('#mobileno').val().trim();
        if (mobileno === '') {
            $('#mobilenoError').text('Mobile Number is required');
            $('#mobileno').addClass('error-border');
            isValid = false;
        }

        // New fields validation
        let studentCode = $('#studentCode').val().trim();
        if (studentCode === '') {
            $('#studentCodeError').text('Student Code is required');
            $('#studentCode').addClass('error-border');
            isValid = false;
        }

        let address = $('#address').val().trim();
        if (address === '') {
            $('#addressError').text('Address is required');
            $('#address').addClass('error-border');
            isValid = false;
        }

        let status = $('#status').val().trim();
        if (status === '') {
            $('#statusError').text('Status is required');
            $('#status').addClass('error-border');
            isValid = false;
        }

        // Prevent form submission if validation fails
        if (!isValid) {
            event.preventDefault(); // Prevent form submission only if
									// validation fails
        }
    });

    // Clear error messages function
    function clearErrors() {
        $('#firstnameError, #lastnameError, #fathernameError, #rollNumberError, #marksError, #collegeError, #branchError, #dateOfBirthError, #mobilenoError, #studentCodeError, #addressError, #statusError').text('');

        // Remove error border classes
        const errorFields = ['firstname', 'lastname', 'fathername', 'rollNumber', 'marks', 'college', 'branch', 'dateOfBirth', 'mobileno', 'studentCode', 'address', 'status'];
        errorFields.forEach(field => {
            $('#' + field).removeClass('error-border');
        });
    }

    // Remove error border and message when user starts typing
    const fields = ['firstname', 'lastname', 'fathername', 'rollNumber', 'marks', 'college', 'branch', 'dateOfBirth', 'mobileno', 'studentCode', 'address', 'status'];

    fields.forEach(field => {
        $('#' + field).on('input', function() {
            $('#' + field + 'Error').text('');
            $(this).removeClass('error-border');
        });
    });
});
