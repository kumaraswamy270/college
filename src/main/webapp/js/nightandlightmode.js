$(document).ready(function() {
	const $modeToggle = $('#modeToggle');

	// Set initial mode based on current body class
	if ($('body').hasClass('night-mode')) {
		$modeToggle.text('Switch to Light Mode');
	} else {
		$modeToggle.text('Switch to Night Mode');
	}

	$modeToggle.on('click', function() {
		$('body').toggleClass('night-mode light-mode');

		// Update button text based on current mode
		if ($('body').hasClass('night-mode')) {
			$modeToggle.text('Switch to Light Mode');
		} else {
			$modeToggle.text('Switch to Night Mode');
		}
	});
});
