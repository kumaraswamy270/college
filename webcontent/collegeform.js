document.getElementById('collegeForm').addEventListener('submit', function(event) {
    let isValid = true;

    // College ID
    let collegeId = document.getElementById('collegeId').value.trim();
    if (collegeId === '') {
        document.getElementById('collegeIdError').textContent = 'College ID is required';
        document.getElementById('collegeId').classList.add('error-border');
        isValid = false;
    }

    // College Name
    let collegeName = document.getElementById('collegeName').value.trim();
    if (collegeName === '') {
        document.getElementById('collegeNameError').textContent = 'College Name is required';
        document.getElementById('collegeName').classList.add('error-border');
        isValid = false;
    }

    // Address
    let address = document.getElementById('address').value.trim();
    if (address === '') {
        document.getElementById('addressError').textContent = 'Address is required';
        document.getElementById('address').classList.add('error-border');
        isValid = false;
    }

    // City
    let city = document.getElementById('city').value.trim();
    if (city === '') {
        document.getElementById('cityError').textContent = 'City is required';
        document.getElementById('city').classList.add('error-border');
        isValid = false;
    }

    // State
    let state = document.getElementById('state').value.trim();
    if (state === '') {
        document.getElementById('stateError').textContent = 'State is required';
        document.getElementById('state').classList.add('error-border');
        isValid = false;
    }

    // Zipcode
    let zipcode = document.getElementById('zipcode').value.trim();
    if (zipcode === '') {
        document.getElementById('zipcodeError').textContent = 'Zipcode is required';
        document.getElementById('zipcode').classList.add('error-border');
        isValid = false;
    }

    // Phone Number
    let phoneNumber = document.getElementById('phoneNumber').value.trim();
    if (phoneNumber === '') {
        document.getElementById('phoneNumberError').textContent = 'Phone Number is required';
        document.getElementById('phoneNumber').classList.add('error-border');
        isValid = false;
    }

    // Prevent form submission if validation fails
    if (!isValid) {
        event.preventDefault();
    }
});

// Add event listeners for input changes to remove error messages
document.getElementById('collegeId').addEventListener('input', function() {
    if (this.value.trim() !== '') {
        document.getElementById('collegeIdError').textContent = '';
        this.classList.remove('error-border');
    }
});

document.getElementById('collegeName').addEventListener('input', function() {
    if (this.value.trim() !== '') {
        document.getElementById('collegeNameError').textContent = '';
        this.classList.remove('error-border');
    }
});

document.getElementById('address').addEventListener('input', function() {
    if (this.value.trim() !== '') {
        document.getElementById('addressError').textContent = '';
        this.classList.remove('error-border');
    }
});

document.getElementById('city').addEventListener('input', function() {
    if (this.value.trim() !== '') {
        document.getElementById('cityError').textContent = '';
        this.classList.remove('error-border');
    }
});

document.getElementById('state').addEventListener('input', function() {
    if (this.value.trim() !== '') {
        document.getElementById('stateError').textContent = '';
        this.classList.remove('error-border');
    }
});

document.getElementById('zipcode').addEventListener('input', function() {
    if (this.value.trim() !== '') {
        document.getElementById('zipcodeError').textContent = '';
        this.classList.remove('error-border');
    }
});

document.getElementById('phoneNumber').addEventListener('input', function() {
    if (this.value.trim() !== '') {
        document.getElementById('phoneNumberError').textContent = '';
        this.classList.remove('error-border');
    }
});

// Clear all error messages and borders
function clearErrors() {
    document.getElementById('collegeIdError').textContent = '';
    document.getElementById('collegeNameError').textContent = '';
    document.getElementById('addressError').textContent = '';
    document.getElementById('cityError').textContent = '';
    document.getElementById('stateError').textContent = '';
    document.getElementById('zipcodeError').textContent = '';
    document.getElementById('phoneNumberError').textContent = '';

    document.getElementById('collegeId').classList.remove('error-border');
    document.getElementById('collegeName').classList.remove('error-border');
    document.getElementById('address').classList.remove('error-border');
    document.getElementById('city').classList.remove('error-border');
    document.getElementById('state').classList.remove('error-border');
    document.getElementById('zipcode').classList.remove('error-border');
    document.getElementById('phoneNumber').classList.remove('error-border');
}
