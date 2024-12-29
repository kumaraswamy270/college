$(document).ready(function () {

    // Load the first page on initial load
    loadPage(1);

    // Handle the page number click
    $(document).on('click', '.page-link', function () {
        var page = $(this).data('page');
        loadPage(page);
    });

    // Handle Prev button click
    $('#prevPage').click(function () {
        var currentPage = parseInt($('#currentPage').val());
        if (currentPage > 1) {
            loadPage(currentPage - 1);
        }
    });

    // Handle Next button click
    $('#nextPage').click(function () {
        var currentPage = parseInt($('#currentPage').val());
        var totalPages = parseInt($('#totalPages').val());
        if (currentPage < totalPages) {
            loadPage(currentPage + 1);
        }
    });

    // Function to load the page
    function loadPage(page) {
        $.get('/student/list', { page: page, pageSize: 3 }, function (data) {
            // Populate the student list
            var studentList = data.students;
            var html = '';
            $.each(studentList, function (index, student) {
                html += '<p>' + student.name + ' - ' + student.marks + '</p>';
            });
            $('#studentList').html(html);

            // Update current page and total pages
            var currentPage = data.currentPage;
            var totalPages = data.totalPages;

            // Update pagination controls
            $('#currentPage').val(currentPage);
            $('#totalPages').val(totalPages);

            // Update the page numbers
            var pageNumbersHtml = '';
            for (var i = 1; i <= totalPages; i++) {
                pageNumbersHtml += '<a href="javascript:void(0)" class="page-link" data-page="' + i + '">' + i + '</a>';
            }
            $('#pageNumbers').html(pageNumbersHtml);

            // Update the Prev and Next buttons state
            if (currentPage === 1) {
                $('#prevPage').addClass('disabled');
            } else {
                $('#prevPage').removeClass('disabled');
            }

            if (currentPage === totalPages) {
                $('#nextPage').addClass('disabled');
            } else {
                $('#nextPage').removeClass('disabled');
            }
        });
    }
});
