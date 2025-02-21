<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/layout/header.jsp" />

<!-- jQuery (for AJAX) -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<div class="full_container">
    <div class="inner_container">
        <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/layout/sidebar.jsp" />
        <div id="content">
            <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/layout/topbar.jsp" />
            <div class="midde_cont">
                <div class="container-fluid">
                    <!-- Page Title -->
                    <div class="row column_title">
                        <div class="col-md-12">
                            <div class="page_title">
                                <h2>Appointment</h2>
                            </div>
                        </div>
                    </div>

                    <!-- Search Form -->
                    <form id="searchForm" class="mb-4">
                        <div class="row">
                            <div class="col-md-3">
                                <input type="text" id="patientName" name="searchName" class="form-control" placeholder="Search by Patient Name">
                            </div>
                            <div class="col-md-3">
                                <input type="text" id="patientPhone" name="searchContact" class="form-control" placeholder="Search by Patient Phone">
                            </div>
                            <div class="col-md-3">
                                <select id="doctorId" name="doctorId" class="form-control">
                                    <option value="">Search by Doctor</option>
                                    <c:forEach var="doctor" items="${doctorList}">
                                        <option value="${doctor.code}">${doctor.desc}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <button type="submit" class="btn btn-primary w-100">Search</button>
                            </div>
                            <div class="col-md-1">
                                <button type="button" id="resetBtn" class="btn btn-secondary w-100">Reset</button>
                            </div>
                        </div>
                    </form>

                    <!-- Appointment Table -->
                    <div class="row column3">
                        <div class="card-body">
                            <a href="appointment/create" class="btn btn-success mb-3">Add New Appointment</a>

                            <table id="appointmentTable" class="table table-hover">
                                <thead class="thead-dark">
                                <tr>
                                    <th>#</th>
                                    <th>Patient Name</th>
                                    <th>Patient Phone</th>
                                    <th>Date</th>
                                    <th>Time</th>
                                    <th>Doctor</th>
                                    <th>Status</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:set var="rowNumber" value="${appointmentPage.number * appointmentPage.size + 1}" />
                                <c:forEach var="appointment" items="${appointmentPage.content}">
                                    <tr>
                                        <td>${rowNumber}</td>
                                        <td>${appointment.patientName}</td>
                                        <td>${appointment.patientPhone}</td>
                                        <td>${appointment.dateDesc}</td>
                                        <td>${appointment.time}</td>
                                        <td>${appointment.doctorName}</td>

                                        <td>
                                            <c:choose>
                                                <c:when test="${appointment.statusName eq 'Confirmed'}">
                                                    <span class="badge badge-success">Confirmed</span>
                                                </c:when>
                                                <c:when test="${appointment.statusName eq 'Pending'}">
                                                    <span class="badge badge-warning">Pending</span>
                                                </c:when>
                                                <c:when test="${appointment.statusName eq 'Cancelled'}">
                                                    <span class="badge badge-danger">Cancelled</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="badge badge-secondary">Finished</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <a href="appointment/edit/${appointment.appointmentId}" class="btn btn-warning btn-sm">Edit</a>
                                            <a href="appointment/delete/${appointment.appointmentId}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?');">Delete</a>
                                        </td>
                                    </tr>
                                    <c:set var="rowNumber" value="${rowNumber + 1}" />
                                </c:forEach>
                                <c:if test="${empty appointmentPage.content}">
                                    <tr>
                                        <td colspan="7" class="text-center text-danger">
                                                ${errorMessage != null ? errorMessage : "No Appointment found."}
                                        </td>
                                    </tr>
                                </c:if>
                                </tbody>
                            </table>

                            <!-- Pagination Controls -->
                            <div class="pagination-container d-flex justify-content-center mt-3">
                                <ul class="pagination">
                                    <!-- Previous Page -->
                                    <c:if test="${appointmentPage.hasPrevious()}">
                                        <li class="page-item">
                                            <a class="page-link" href="javascript:void(0);" onclick="fetchAppointment(${appointmentPage.number - 1})">Previous</a>
                                        </li>
                                    </c:if>

                                    <!-- Display Limited Page Numbers -->
                                    <c:set var="startPage" value="${appointmentPage.number - 2}" />
                                    <c:set var="endPage" value="${appointmentPage.number + 2}" />

                                    <!-- Ensure startPage is not less than 0 -->
                                    <c:if test="${startPage < 0}">
                                        <c:set var="startPage" value="0" />
                                    </c:if>

                                    <!-- Ensure endPage does not exceed total pages -->
                                    <c:if test="${endPage >= appointmentPage.totalPages}">
                                        <c:set var="endPage" value="${appointmentPage.totalPages - 1}" />
                                    </c:if>

                                    <c:forEach var="i" begin="${startPage}" end="${endPage}" varStatus="loop">
                                        <li class="page-item ${i == appointmentPage.number ? 'active' : ''}">
                                            <a class="page-link" href="javascript:void(0);" onclick="fetchAppointment(${i})">${i + 1}</a>
                                        </li>
                                    </c:forEach>

                                    <!-- Next Page -->
                                    <c:if test="${appointmentPage.hasNext()}">
                                        <li class="page-item">
                                            <a class="page-link" href="javascript:void(0);" onclick="fetchAppointment(${appointmentPage.number + 1})">Next</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/layout/footer.jsp" />
<script src="${pageContext.request.contextPath}/static/js/appointment/appointmentSearch.js"></script>