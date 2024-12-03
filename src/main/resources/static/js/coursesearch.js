// Wait for the DOM to be fully loaded
$(document).ready(function () {
    // Attach an event listener to the form submission
    $('#searchForm').on('submit', function (event) {
        event.preventDefault(); // Prevent the default form submission behavior
        filterCourses(); // Call the filterCourses function
    });
});

// The filterCourses function
function filterCourses() {
    // Get the search input value
    const searchInput = $('#searchInput').val().toLowerCase();
    const table = $('#courseTable');
    const rows = table.find('tbody tr');
    let found = false;

    // Clear previous "Course not found" message
    $('#notFoundMessage').remove();

    // Loop through the rows and hide those that do not match the search
    rows.each(function () {
        const courseId = $(this).find('td').eq(0); // Fix index to match "Course ID"
        const courseName = $(this).find('td').eq(1); // Add matching for "Course Name"

        if (courseId.length && courseName.length) {
            // Check if the Course ID or Course Name matches the search input
            if (
                courseId.text().toLowerCase().includes(searchInput) ||
                courseName.text().toLowerCase().includes(searchInput)
            ) {
                $(this).show(); // Show the matching row
                found = true; // Set found to true if a match is found
            } else {
                $(this).hide(); // Hide the non-matching row
            }
        }
    });

    // If no match was found, display a "Course not found" message
    if (!found && searchInput) {
        $('<div>', {
            id: 'notFoundMessage',
            class: 'alert alert-warning',
            text: 'No Result Found',
        }).insertBefore(table);
    }
}
