<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="layout/header.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script> <!-- Linking Chart.js -->

<div class="full_container">
    <div class="inner_container">
        <%@ include file="layout/sidebar.jsp" %>
        <div id="content">
            <%@ include file="layout/topbar.jsp" %>
            <div class="middle_cont">
                <div class="container-fluid">
                    <!-- Dashboard Title -->
                    <div class="row column_title">
                        <div class="col-md-12">
                            <div class="page_title">
                                <h2>Dashboard</h2>
                            </div>
                        </div>
                    </div>
                    <!-- Charts Container -->
                    <div class="row">
                        <div class="col-md-6">
                            <canvas id="patientsChart"></canvas> <!-- Canvas for Patients Chart -->
                        </div>
                        <div class="col-md-6">
                            <canvas id="appointmentsChart"></canvas> <!-- Canvas for Appointments Chart -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="layout/footer.jsp" %>

<!-- Chart.js Scripts -->
<script>
    var ctx = document.getElementById('patientsChart').getContext('2d');
    var patientsChart = new Chart(ctx, {
        type: 'bar', // or 'line', 'doughnut', etc.
        data: {
            labels: ['Dr. Zaw Myo Htet', 'Dr. Ye Min Maung', 'Dr. Ye Thiha', 'Dr. Myo Wai Yan'], // Example data
            datasets: [{
                label: '# of Visits',
                data: [12, 19, 14, 16], // Example data
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(128,255,86,0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(128,255,86,0.2)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    var ctx2 = document.getElementById('appointmentsChart').getContext('2d');
    var appointmentsChart = new Chart(ctx2, {
        type: 'pie', // Type of chart
        data: {
            labels: ['Confirmed', 'Pending', 'Cancelled'],
            datasets: [{
                label: 'Appointment Status',
                data: [50, 30, 20], // Example data
                backgroundColor: [
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(255, 205, 86, 0.2)',
                    'rgba(255, 99, 132, 0.2)'
                ],
                borderColor: [
                    'rgba(75, 192, 192, 1)',
                    'rgba(255, 205, 86, 1)',
                    'rgba(255, 99, 132, 1)'
                ],
                borderWidth: 1
            }]
        }
    });
</script>
