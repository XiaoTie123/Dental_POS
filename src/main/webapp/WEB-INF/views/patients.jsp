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
                                <h2>Patients</h2>
                            </div>
                        </div>
                    </div>
                    <div class="row column3">
                        <div class="card-body">
                            <table class="table table-hover">
                                <thead class="thead-dark">
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Age</th>
                                    <th>Contact</th>
                                    <th>Last Visit</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>John Doe</td>
                                    <td>35</td>
                                    <td>(123) 456-7890</td>
                                    <td>10 Jan 2025</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Jane Roe</td>
                                    <td>29</td>
                                    <td>(987) 654-3210</td>
                                    <td>15 Dec 2024</td>
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