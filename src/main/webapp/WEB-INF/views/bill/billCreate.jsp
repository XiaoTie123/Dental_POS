<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/layout/header.jsp" />

<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bill/bill.css">

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
                                <h2>Add New Billing</h2>
                            </div>
                        </div>
                    </div>

                    <div class="row column1">
                        <div class="col-md-12">
                            <div class="white_shd full margin_bottom_30">
                                <div class="full price_table padding_infor_info">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <form action="${pageContext.request.contextPath}/bill/save" method="post">
                                                <div class="row">
                                                    <!-- Left Column -->
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label for="patientId" class="form-label">Patient:</label>
                                                            <select id="patientId" name="patientId" class="form-control" required>
                                                                <option value="">Select Patient</option>
                                                                <c:forEach var="patient" items="${patientList}">
                                                                    <option value="${patient.patientId}" ${patient.patientId == bill.patientId ? 'selected' : ''}>${patient.name}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="percentage">Percentage: </label>
                                                            <input type="number" id="percentage" name="percentage" value="${bill.percentage}" class="form-control" required>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="transfer">Transfer:</label>
                                                            <select id="transfer" name="transfer" class="form-control" required>
                                                                <option value="">Select Transfer</option>
                                                                <c:forEach var="transfer" items="${transferList}">
                                                                    <option value="${transfer.code}" ${transfer.code == bill.transfer ? 'selected' : ''}>${transfer.desc}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>

                                                    <!-- Right Column -->
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label for="doctorId">Doctor Assigned:</label>
                                                            <select id="doctorId" name="doctorId" class="form-control" required>
                                                                <option value="">Select Doctor</option>
                                                                <c:forEach var="doctor" items="${doctorList}">
                                                                    <option value="${doctor.code}" ${doctor.code == bill.doctorId ? 'selected' : ''}>${doctor.desc}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="tax">Tax: </label>
                                                            <input type="number" id="tax" name="tax" value="${bill.tax}" class="form-control" required>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="currency">Currency:</label>
                                                            <select id="currency" name="currency" class="form-control" required>
                                                                <option value="">Select Currency</option>
                                                                <c:forEach var="currency" items="${currencyList}">
                                                                    <option value="${currency.code}" ${currency.code == bill.currency ? 'selected' : ''}>${currency.desc}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>

                                                        <div class="form-group">
                                                            <c:forEach items="${bill.billDetailDtoList}" var="detail" varStatus="status">
                                                                <div class="serviceEntry">
                                                                    <label>Clinic Service:</label>
                                                                    <select name="billDetailDtoList[${status.index}].serviceId" class="form-control">
                                                                        <option value="">Select Clinic Service</option>
                                                                        <c:forEach items="${clinicServiceList}" var="clinicService">
                                                                            <option value="${clinicService.serviceId}" ${detail.serviceId == clinicService.serviceId ? 'selected' : ''}>${clinicService.name} - ${clinicService.description} ( ${clinicService.amountDesc} )</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                    <select style="margin-top: 10px;" name="billDetailDtoList[${status.index}].currency" class="form-control">
                                                                        <option value="">Select Currency</option>
                                                                        <c:forEach items="${currencyList}" var="currency">
                                                                            <option value="${currency.code}" ${detail.currency == currency.code ? 'selected' : ''}>${currency.desc}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                    <input type="number" style="margin-top: 10px;" name="billDetailDtoList[${status.index}].serviceAmount" placeholder="Amount" class="form-control" value="${detail.serviceAmount}">
                                                                    <input type="number" style="margin-top: 10px;" name="billDetailDtoList[${status.index}].qty" placeholder="Quantity" class="form-control" value="${detail.qty}">
                                                                    <button type="submit" formaction="${pageContext.request.contextPath}/bill/removeServiceLine/${status.index}" class="btn btn-danger" style="margin-top: 10px;">Remove</button>
                                                                </div>
                                                            </c:forEach>
                                                        </div>

                                                    </div>
                                                </div>

                                                <div class="form-group mt-4">
                                                    <button type="submit" formaction="${pageContext.request.contextPath}/bill/addServiceLine" class="btn btn-primary">Add Clinic Service</button>
                                                    <button type="submit" class="btn btn-primary">Save</button>
                                                    <a href="${pageContext.request.contextPath}/bill" class="btn btn-secondary">Cancel</a>
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
