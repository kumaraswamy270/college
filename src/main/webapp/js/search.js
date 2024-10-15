
function filterStudents() {
    // Get the search input value
    const searchInput = document.getElementById('searchInput').value.toLowerCase();
    const table = document.getElementById('studentTable');
    const tbody = table.getElementsByTagName('tbody')[0];
    const rows = tbody.getElementsByTagName('tr');
    let found = false;

    // Clear previous "Student not found" message
    const notFoundMessage = document.getElementById('notFoundMessage');
    if (notFoundMessage) {
        notFoundMessage.remove();
    }

    // Loop through the rows and hide those that do not match the search
    for (let i = 0; i < rows.length; i++) {
        const rollNumber = rows[i].getElementsByTagName('td')[1]; 
        if (rollNumber) {
            // Check if the roll number matches the search input
            if (rollNumber.textContent.toLowerCase() === searchInput) {
                rows[i].style.display = ''; // Show the matching row
                found = true; // Set found to true if a match is found
            } else {
                rows[i].style.display = 'none'; // Hide the non-matching row
            }
        }
    }

    // If no match was found, display a "Student not found" message
    if (!found && searchInput) {
        const message = document.createElement('div');
        message.id = 'notFoundMessage';
        message.className = 'alert alert-warning'; 
        message.textContent = 'No Result Found';
        table.parentNode.insertBefore(message, table); 
    }
}
