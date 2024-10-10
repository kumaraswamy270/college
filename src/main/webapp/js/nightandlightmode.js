document.addEventListener('DOMContentLoaded', function() {
    const modeToggle = document.getElementById('modeToggle');

    // Set initial mode based on current body class
    if (document.body.classList.contains('night-mode')) {
        modeToggle.textContent = 'Switch to Light Mode';
    } else {
        modeToggle.textContent = 'Switch to Night Mode';
    }

    modeToggle.addEventListener('click', function() {
        document.body.classList.toggle('night-mode');
        document.body.classList.toggle('light-mode');

        // Update button text based on current mode
        if (document.body.classList.contains('night-mode')) {
            modeToggle.textContent = 'Switch to Light Mode';
        } else {
            modeToggle.textContent = 'Switch to Night Mode';
        }
    });
});
