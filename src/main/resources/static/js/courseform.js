$(document).ready(function() {
    $('#courseForm').on('submit', function(event) {
        clearErrors();
        let isValid = true;

        // Existing fields validation
        let courseIdStr = $('#courseId').val().trim();
        if (courseIdStr === '') {
            $('#courseIdError').text('Course ID is required');
            $('#courseId').addClass('error-border');
            isValid = false;
        }

        let courseName = $('#courseName').val().trim();
        if (courseName === '') {
            $('#courseNameError').text('Course Name is required');
            $('#courseName').addClass('error-border');
            isValid = false;
        }

        let credits = $('#credits').val().trim();
        if (credits === '') {
            $('#creditsError').text('Credits are required');
            $('#credits').addClass('error-border');
            isValid = false;
        }

        let department = $('#department').val().trim();
        if (department === '') {
            $('#departmentError').text('Department is required');
            $('#department').addClass('error-border');
            isValid = false;
        }

        let duration = $('#duration').val().trim();
        if (duration === '') {
            $('#durationError').text('Duration is required');
            $('#duration').addClass('error-border');
            isValid = false;
        }

        let feeStructure = $('#feeStructure').val().trim();
        if (feeStructure === '') {
            $('#feeStructureError').text('Fee Structure is required');
            $('#feeStructure').addClass('error-border');
            isValid = false;
        }

        let lengthOfStudents = $('#lengthOfStudents').val().trim();
        if (lengthOfStudents === '') {
            $('#lengthOfStudentsError').text('Length of Students is required');
            $('#lengthOfStudents').addClass('error-border');
            isValid = false;
        }

        let startDate = $('#startDate').val().trim();
        if (startDate === '') {
            $('#startDateError').text('Start Date is required');
            $('#startDate').addClass('error-border');
            isValid = false;
        }

        let endDate = $('#endDate').val().trim();
        if (endDate === '') {
            $('#endDateError').text('End Date is required');
            $('#endDate').addClass('error-border');
            isValid = false;
        }

        // Prevent form submission if validation fails
        if (!isValid) {
            event.preventDefault();
        }
    });

    // Clear error messages function
    function clearErrors() {
        $('#courseIdError, #courseNameError, #creditsError, #departmentError, #durationError, #feeStructureError, #lengthOfStudentsError, #startDateError, #endDateError').text('');

        // Remove error border classes
        const errorFields = ['courseId', 'courseName', 'credits', 'department', 'duration', 'feeStructure', 'lengthOfStudents', 'startDate', 'endDate'];
        errorFields.forEach(field => {
            $('#' + field).removeClass('error-border');
        });
    }

    // Remove error border and message when user starts typing
    const fields = ['courseId', 'courseName', 'credits', 'department', 'duration', 'feeStructure', 'lengthOfStudents', 'startDate', 'endDate'];
    fields.forEach(field => {
        $('#' + field).on('input', function() {
            $('#' + field + 'Error').text('');
            $(this).removeClass('error-border');
        });
    });
});
