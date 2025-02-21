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
                                <h2>Add New Patient</h2>
                            </div>
                        </div>
                    </div>

                    <div class="row column1">
                        <div class="col-md-12">
                            <div class="white_shd full margin_bottom_30">
                                <div class="full price_table padding_infor_info">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <form action="${pageContext.request.contextPath}/patients/save" method="post">
                                                <div class="row">
                                                    <!-- Left Column -->
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label for="ref">Reference:</label>
                                                            <input type="text" id="ref" name="ref" class="form-control" value="${patient.ref}" readonly required>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="phone">Phone:</label>
                                                            <input type="text" id="phone" name="phone" class="form-control" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="address">Address:</label>
                                                            <textarea id="address" name="address" class="form-control" rows="3" required></textarea>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="doctorId">Doctor Assigned:</label>
                                                            <select id="doctorId" name="doctorId" class="form-control" required>
                                                                <option value="">Select Doctor</option>
                                                                <c:forEach var="doctor" items="${doctorList}">
                                                                    <option value="${doctor.code}">${doctor.desc}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>

                                                    <!-- Right Column -->
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label for="name">Name:</label>
                                                            <input type="text" id="name" name="name" class="form-control" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="age">Age:</label>
                                                            <input type="number" id="age" name="age" class="form-control" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="contact_detail">Patient Records:</label>
                                                            <textarea id="contact_detail" name="contactDetail" class="form-control" rows="3" required></textarea>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group mt-3">
                                                    <button type="submit" class="btn btn-primary">Save</button>
                                                    <a href="${pageContext.request.contextPath}/patients" class="btn btn-secondary">Cancel</a>
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
