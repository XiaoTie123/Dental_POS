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
                                <h2>Patients</h2>
                            </div>
                        </div>
                    </div>

                    <!-- Search Form -->
                    <form id="searchForm" class="mb-4">
                        <div class="row">
                            <div class="col-md-4">
                                <input type="text" id="searchName" name="searchName" class="form-control" placeholder="Search by Name">
                            </div>
                            <div class="col-md-4">
                                <input type="text" id="searchContact" name="searchContact" class="form-control" placeholder="Search by Contact">
                            </div>
                            <div class="col-md-2">
                                <button type="submit" class="btn btn-primary w-100">Search</button>
                            </div>
                            <div class="col-md-2">
                                <button type="button" id="resetBtn" class="btn btn-secondary w-100">Reset</button>
                            </div>
                        </div>
                    </form>

                    <!-- Patients Table -->
                    <div class="row column3">
                        <div class="card-body">
                            <a href="patients/create" class="btn btn-success mb-3">Add New Patient</a>

                            <table id="patientTable" class="table table-hover">
                                <thead class="thead-dark">
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Age</th>
                                    <th>Contact</th>
                                    <th>Last Visit</th>
                                    <th>Doctor</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:set var="rowNumber" value="${patientPage.number * patientPage.size + 1}" />
                                <c:forEach var="patient" items="${patientPage.content}">
                                    <tr>
                                        <td>${rowNumber}</td>
                                        <td>${patient.name}</td>
                                        <td>${patient.age}</td>
                                        <td>${patient.phone}</td>
                                        <td>${patient.updatedDateTime}</td>
                                        <td>${patient.doctorName}</td>
                                        <td>
                                            <a href="patients/edit/${patient.patientId}" class="btn btn-warning btn-sm">Edit</a>
                                            <a href="patients/delete/${patient.patientId}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?');">Delete</a>
                                        </td>
                                    </tr>
                                    <c:set var="rowNumber" value="${rowNumber + 1}" />
                                </c:forEach>
                                <c:if test="${empty patientPage.content}">
                                    <tr>
                                        <td colspan="7" class="text-center text-danger">
                                                ${errorMessage != null ? errorMessage : "No patients found."}
                                        </td>
                                    </tr>
                                </c:if>
                                </tbody>
                            </table>

                            <!-- Pagination Controls -->
                            <div class="pagination-container d-flex justify-content-center mt-3">
                                <ul class="pagination">
                                    <!-- Previous Page -->
                                    <c:if test="${patientPage.hasPrevious()}">
                                        <li class="page-item">
                                            <a class="page-link" href="javascript:void(0);" onclick="fetchPatients(${patientPage.number - 1})">Previous</a>
                                        </li>
                                    </c:if>

                                    <!-- Display Limited Page Numbers -->
                                    <c:set var="startPage" value="${patientPage.number - 2}" />
                                    <c:set var="endPage" value="${patientPage.number + 2}" />

                                    <!-- Ensure startPage is not less than 0 -->
                                    <c:if test="${startPage < 0}">
                                        <c:set var="startPage" value="0" />
                                    </c:if>

                                    <!-- Ensure endPage does not exceed total pages -->
                                    <c:if test="${endPage >= patientPage.totalPages}">
                                        <c:set var="endPage" value="${patientPage.totalPages - 1}" />
                                    </c:if>

                                    <c:forEach var="i" begin="${startPage}" end="${endPage}" varStatus="loop">
                                        <li class="page-item ${i == patientPage.number ? 'active' : ''}">
                                            <a class="page-link" href="javascript:void(0);" onclick="fetchPatients(${i})">${i + 1}</a>
                                        </li>
                                    </c:forEach>

                                    <!-- Next Page -->
                                    <c:if test="${patientPage.hasNext()}">
                                        <li class="page-item">
                                            <a class="page-link" href="javascript:void(0);" onclick="fetchPatients(${patientPage.number + 1})">Next</a>
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
<script src="${pageContext.request.contextPath}/static/js/patients/patientsSearch.js"></script>