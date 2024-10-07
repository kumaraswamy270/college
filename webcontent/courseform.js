document.addEventListener('DOMContentLoaded', function() {
    // Attach event listener to form submission
    document.getElementById('courseForm').addEventListener('submit', function(event) {
        let isValid = true;

        clearErrors();

        let courseId = document.getElementById('courseId').value.trim();
        if (courseId === '') {
            document.getElementById('courseIdError').textContent = 'Course ID is required';
            document.getElementById('courseId').classList.add('error-border');
            isValid = false;
        }

        let courseName = document.getElementById('courseName').value.trim();
        if (courseName === '') {
            document.getElementById('courseNameError').textContent = 'Course Name is required';
            document.getElementById('courseName').classList.add('error-border');
            isValid = false;
        }

        let credits = document.getElementById('credits').value.trim();
        if (credits === '') {
            document.getElementById('creditsError').textContent = 'Credits are required';
            document.getElementById('credits').classList.add('error-border');
            isValid = false;
        }

        let department = document.getElementById('department').value.trim();
        if (department === '') {
            document.getElementById('departmentError').textContent = 'Department is required';
            document.getElementById('department').classList.add('error-border');
            isValid = false;
        }

        let duration = document.getElementById('duration').value.trim();
        if (duration === '') {
            document.getElementById('durationError').textContent = 'Duration is required';
            document.getElementById('duration').classList.add('error-border');
            isValid = false;
        }

        let feeStructure = document.getElementById('feeStructure').value.trim();
        if (feeStructure === '') {
            document.getElementById('feeStructureError').textContent = 'Fee Structure is required';
            document.getElementById('feeStructure').classList.add('error-border');
            isValid = false;
        }

        let lengthOfStudents = document.getElementById('lengthOfStudents').value.trim();
        if (lengthOfStudents === '') {
            document.getElementById('lengthOfStudentsError').textContent = 'Number of Students is required';
            document.getElementById('lengthOfStudents').classList.add('error-border');
            isValid = false;
        }

        let startDate = document.getElementById('startDate').value.trim();
        if (startDate === '') {
            document.getElementById('startDateError').textContent = 'Start Date is required';
            document.getElementById('startDate').classList.add('error-border');
            isValid = false;
        }

        let endDate = document.getElementById('endDate').value.trim();
        if (endDate === '') {
            document.getElementById('endDateError').textContent = 'End Date is required';
            document.getElementById('endDate').classList.add('error-border');
            isValid = false;
        }

        let courseType = document.getElementById('courseType').value.trim();
        if (courseType === '') {
            document.getElementById('courseTypeError').textContent = 'Course Type is required';
            document.getElementById('courseType').classList.add('error-border');
            isValid = false;
        }

        // Prevent form submission if validation fails
        if (!isValid) {
            event.preventDefault();
        }
    });

    // Event listeners for removing error messages on input change
    document.getElementById('courseId').addEventListener('input', function() {
        if (this.value.trim() !== '') {
            document.getElementById('courseIdError').textContent = '';
            this.classList.remove('error-border');
        }
    });

    document.getElementById('courseName').addEventListener('input', function() {
        if (this.value.trim() !== '') {
            document.getElementById('courseNameError').textContent = '';
            this.classList.remove('error-border');
        }
    });

    document.getElementById('credits').addEventListener('input', function() {
        if (this.value.trim() !== '') {
            document.getElementById('creditsError').textContent = '';
            this.classList.remove('error-border');
        }
    });

    document.getElementById('department').addEventListener('input', function() {
        if (this.value.trim() !== '') {
            document.getElementById('departmentError').textContent = '';
            this.classList.remove('error-border');
        }
    });

    document.getElementById('duration').addEventListener('input', function() {
        if (this.value.trim() !== '') {
            document.getElementById('durationError').textContent = '';
            this.classList.remove('error-border');
        }
    });

    document.getElementById('feeStructure').addEventListener('input', function() {
        if (this.value.trim() !== '') {
            document.getElementById('feeStructureError').textContent = '';
            this.classList.remove('error-border');
        }
    });

    document.getElementById('lengthOfStudents').addEventListener('input', function() {
        if (this.value.trim() !== '') {
            document.getElementById('lengthOfStudentsError').textContent = '';
            this.classList.remove('error-border');
        }
    });

    document.getElementById('startDate').addEventListener('input', function() {
        if (this.value.trim() !== '') {
            document.getElementById('startDateError').textContent = '';
            this.classList.remove('error-border');
        }
    });

    document.getElementById('endDate').addEventListener('input', function() {
        if (this.value.trim() !== '') {
            document.getElementById('endDateError').textContent = '';
            this.classList.remove('error-border');
        }
    });

    document.getElementById('courseType').addEventListener('change', function() {
        if (this.value.trim() !== '') {
            document.getElementById('courseTypeError').textContent = '';
            this.classList.remove('error-border');
        }
    });
});

function clearErrors() {
    document.getElementById('courseIdError').textContent = '';
    document.getElementById('courseNameError').textContent = '';
    document.getElementById('creditsError').textContent = '';
    document.getElementById('departmentError').textContent = '';
    document.getElementById('durationError').textContent = '';
    document.getElementById('feeStructureError').textContent = '';
    document.getElementById('lengthOfStudentsError').textContent = '';
    document.getElementById('startDateError').textContent = '';
    document.getElementById('endDateError').textContent = '';
    document.getElementById('courseTypeError').textContent = '';

    document.getElementById('courseId').classList.remove('error-border');
    document.getElementById('courseName').classList.remove('error-border');
    document.getElementById('credits').classList.remove('error-border');
    document.getElementById('department').classList.remove('error-border');
    document.getElementById('duration').classList.remove('error-border');
    document.getElementById('feeStructure').classList.remove('error-border');
    document.getElementById('lengthOfStudents').classList.remove('error-border');
    document.getElementById('startDate').classList.remove('error-border');
    document.getElementById('endDate').classList.remove('error-border');
    document.getElementById('courseType').classList.remove('error-border');
}
