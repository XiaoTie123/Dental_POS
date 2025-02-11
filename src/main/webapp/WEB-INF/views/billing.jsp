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
                                <h2>Billing</h2>
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
                                    <th>Service</th>
                                    <th>Amount</th>
                                    <th>Date</th>
                                    <th>Status</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>John Doe</td>
                                    <td>Dental Cleaning</td>
                                    <td>$100</td>
                                    <td>10 Feb 2025</td>
                                    <td><span class="badge badge-success">Paid</span></td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Jane Roe</td>
                                    <td>Root Canal</td>
                                    <td>$500</td>
                                    <td>11 Feb 2025</td>
                                    <td><span class="badge badge-warning">Pending</span></td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Michael Lee</td>
                                    <td>Tooth Extraction</td>
                                    <td>$150</td>
                                    <td>12 Feb 2025</td>
                                    <td><span class="badge badge-danger">Overdue</span></td>
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
