$(document).ready(function() {
    function fetchPatients(page) {
        let searchName = $("#searchName").val();
        let searchContact = $("#searchContact").val();

        $.ajax({
            type: "POST",
            url: "patients/search?page=" + page,
            data: {
                searchName: searchName,
                searchContact: searchContact
            },
            success: function(data) {
                $("#patientTable tbody").html($(data).find("#patientTable tbody").html());
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
        fetchPatients(page);
    });

    // Search Form Submit
    $("#searchForm").submit(function(event) {
        event.preventDefault();
        fetchPatients(0);
    });

    // Reset Button Click Event
    $("#resetBtn").click(function() {
        $("#searchName").val('');
        $("#searchContact").val('');
        fetchPatients(0);
    });
});