
// Wait for the DOM to be fully loaded
$(document).ready(function() {
    // Attach an event listener to the form submission
    $('#searchForm').on('submit', function(event) {
        event.preventDefault(); // Prevent the default form submission behavior
        filterStudents(); // Call the filterStudents function
    });
});

// The filterStudents function remains as it is
function filterStudents() {
    // Get the search input value
    const searchInput = $('#searchInput').val().toLowerCase();
    const table = $('#studentTable');
    const rows = table.find('tbody tr');
    let found = false;

    // Clear previous "Student not found" message
    $('#notFoundMessage').remove();

    // Loop through the rows and hide those that do not match the search
    rows.each(function () {
        const rollNumber = $(this).find('td').eq(1);
        if (rollNumber.length) {
            // Check if the roll number matches the search input
            if (rollNumber.text().toLowerCase() === searchInput) {
                $(this).show(); // Show the matching row
                found = true; // Set found to true if a match is found
            } else {
                $(this).hide(); // Hide the non-matching row
            }
        }
    });

    // If no match was found, display a "Student not found" message
    if (!found && searchInput) {
        $('<div>', {
            id: 'notFoundMessage',
            class: 'alert alert-warning',
            text: 'No Result Found'
        }).insertBefore(table);
    }
}
