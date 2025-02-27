$(document).ready(function() {
    function fetchClinicService(page) {
        let name = $("#name").val();

        $.ajax({
            type: "POST",
            url: "clinicService/search?page=" + page,
            data: {
                name: name
            },
            success: function(data) {
                $("#clinicServiceTable tbody").html($(data).find("#clinicServiceTable tbody").html());
                $(".pagination-container").html($(data).find(".pagination-container").html());
            },
            error: function() {
                alert("Error fetching search results!");
            }
        });
    }

    // Pagination Click Event
    $(document).on("click", ".pagination a", function(event) {
        event.preventDefault();
        let page = $(this).attr("onclick").match(/\d+/)[0]; // Extract page number
        fetchClinicService(page);
    });

    // Search Form Submit
    $("#searchForm").submit(function(event) {
        event.preventDefault();
        fetchClinicService(0);
    });

    // Reset Button Click Event
    $("#resetBtn").click(function() {
        $("#name").val('');
        fetchClinicService(0);
    });
});