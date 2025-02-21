$(document).ready(function() {
    function fetchAppointment(page) {
        let patientName = $("#patientName").val();
        let patientPhone = $("#patientPhone").val();
        let doctorId = $("#doctorId").val();

        $.ajax({
            type: "POST",
            url: "appointment/search?page=" + page,
            data: {
                patientName: patientName,
                patientPhone: patientPhone,
                doctorId: doctorId,
            },
            success: function(data) {
                $("#appointmentTable tbody").html($(data).find("#appointmentTable tbody").html());
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
        fetchAppointment(page);
    });

    // Search Form Submit
    $("#searchForm").submit(function(event) {
        event.preventDefault();
        fetchAppointment(0);
    });

    // Reset Button Click Event
    $("#resetBtn").click(function() {
        $("#patientName").val('');
        $("#patientPhone").val('');
        $("#doctorId").val('');
        fetchAppointment(0);
    });
});