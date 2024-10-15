
document.addEventListener('DOMContentLoaded', function() {
    const input = document.getElementById('searchInput');
    const table = document.getElementById('studentTable');
    
    document.querySelector('form').onsubmit = function(event) {
        event.preventDefault(); // Prevent the form from submitting
        filterStudents(); // Call the filter function
    };

    function filterStudents() {
        const filter = input.value.toLowerCase();
        const tr = table.getElementsByTagName('tr');

        // Loop through all table rows, and hide those that don't match the search query
        for (let i = 1; i < tr.length; i++) { // Start from 1 to skip the header row
            const td = tr[i].getElementsByTagName('td');
            let isMatch = false;

            // Loop through each cell in the current row
            for (let j = 0; j < td.length; j++) {
                if (td[j] && td[j].innerText.toLowerCase().indexOf(filter) > -1) {
                    isMatch = true;
                    break;
                }
            }

            // Show or hide the row based on whether there was a match
            tr[i].style.display = isMatch ? '' : 'none';
        }
    }
});
