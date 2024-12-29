$(document).ready(function () {
    $('#gmailForm').on('submit', function (event) {
        event.preventDefault(); // Prevent default form submission

        // Collect form data
        const formData = {
            toEmail: $('#toEmail').val(),
            subject: $('#subject').val(),
            message: $('#message').val()
        };

        // Perform AJAX request
        $.ajax({
            url: '/send-gmail',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function (response) {
                // Handle successful response
                alert('Email sent successfully!');
                window.location.href = 'https://mail.google.com/mail/u/0/#inbox';
            },
            error: function (xhr, status, error) {
                // Handle errors
                console.error('Error:', error);
                alert('Failed to send email. Please try again.');
            }
        });
    });
});

