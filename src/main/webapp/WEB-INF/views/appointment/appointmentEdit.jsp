<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/layout/header.jsp" />

<div class="full_container">
    <div class="inner_container">
        <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/layout/sidebar.jsp" />
        <div id="content">
            <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/layout/topbar.jsp" />
            <div class="midde_cont">
                <div class="container-fluid">
                    <div class="row column_title">
                        <div class="col-md-12">
                            <div class="page_title">
                                <h2>Edit Appointment</h2>
                            </div>
                        </div>
                    </div>

                    <div class="row column1">
                        <div class="col-md-12">
                            <div class="white_shd full margin_bottom_30">
                                <div class="full price_table padding_infor_info">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <form action="${pageContext.request.contextPath}/appointment/update/${appointment.appointmentId}" method="post">
                                                <div class="row">
                                                    <!-- Left Column -->
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label for="patientId" class="form-label">Patient:</label>

                                                            <!-- Searchable Dropdown -->
                                                            <select id="patientId" name="patientId" class="form-control" readonly required>
                                                                <option value="">Select Patient</option>
                                                                <c:forEach var="patient" items="${patientList}">
                                                                    <option value="${patient.patientId}" ${patient.patientId == appointment.patientId ? 'selected' : ''}>${patient.name}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="dateDesc">Appointment Date:</label>
                                                            <input type="date" id="dateDesc" name="dateDesc" class="form-control" value="${appointment.dateDesc}" readonly required>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="status">Status:</label>
                                                            <select id="status" name="status" class="form-control" required>
                                                                <option value="">Select Status</option>
                                                                <c:forEach var="status" items="${statusList}">
                                                                    <option value="${status.code}" ${status.code == appointment.status ? 'selected' : ''}>${status.desc}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>

                                                    <!-- Right Column -->
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label for="doctorId">Doctor Assigned:</label>
                                                            <select id="doctorId" name="doctorId" class="form-control" readonly required>
                                                                <option value="">Select Doctor</option>
                                                                <c:forEach var="doctor" items="${doctorList}">
                                                                    <option value="${doctor.code}" ${doctor.code == appointment.doctorId ? 'selected' : ''}>${doctor.desc}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="timeId">Appointment Time:</label>
                                                            <select id="timeId" name="timeId" class="form-control" readonly required>
                                                                <option value="">Select Time</option>
                                                                <c:forEach var="time" items="${timeList}">
                                                                    <option value="${time.code}" ${time.code == appointment.timeId ? 'selected' : ''}>${time.desc}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group mt-3">
                                                    <button type="submit" class="btn btn-primary">Update</button>
                                                    <a href="${pageContext.request.contextPath}/appointment" class="btn btn-secondary">Cancel</a>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/layout/footer.jsp" />