<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout/header.jsp" %>
<div class="full_container">
    <div class="inner_container">
        <%@ include file="layout/sidebar.jsp" %>
        <div id="content">
            <%@ include file="layout/topbar.jsp" %>
            <div class="midde_cont">
                <div class="container-fluid">
                    <div class="row column_title">
                        <div class="col-md-12">
                            <div class="page_title">
                                <h2>Appointments</h2>
                            </div>
                        </div>
                    </div>
                    <div class="row column3">
                        <div class="card-body">
                            <table class="table table-hover">
                                <thead class="thead-dark">
                                <tr>
                                    <th>#</th>
                                    <th>Patient Name</th>
                                    <th>Date</th>
                                    <th>Time</th>
                                    <th>Doctor</th>
                                    <th>Status</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>John Doe</td>
                                    <td>10 Feb 2025</td>
                                    <td>10:00 AM</td>
                                    <td>Dr. Smith</td>
                                    <td><span class="badge badge-success">Confirmed</span></td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Jane Roe</td>
                                    <td>11 Feb 2025</td>
                                    <td>11:30 AM</td>
                                    <td>Dr. Adams</td>
                                    <td><span class="badge badge-warning">Pending</span></td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Michael Lee</td>
                                    <td>12 Feb 2025</td>
                                    <td>02:00 PM</td>
                                    <td>Dr. Brown</td>
                                    <td><span class="badge badge-danger">Cancelled</span></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="layout/footer.jsp" %>