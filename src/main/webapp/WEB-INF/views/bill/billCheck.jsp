<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">

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
                                <h2>Check Billing Details</h2>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <!-- Patient and Doctor Information -->
                            <div class="white_shd full margin_bottom_30">
                                <div class="full padding_infor_info">
                                    <form>
                                        <div class="form-group">
                                            <label>Patient Name:</label>
                                            <input type="text" value="${bill.patientName}" class="form-control" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label>Patient Contact:</label>
                                            <input type="text" value="${bill.patientContact}" class="form-control" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label>Doctor Name:</label>
                                            <input type="text" value="${bill.doctorName}" class="form-control" readonly>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <!-- Billing Details -->
                            <div class="white_shd full margin_bottom_30">
                                <div class="full padding_infor_info">
                                    <form>
                                        <div class="form-group">
                                            <label>Total Amount:</label>
                                            <input type="text" value="${bill.totalAmountDesc}" class="form-control" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label>Percentage:</label>
                                            <input type="text" value="${bill.percentageDesc}" class="form-control" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label>Tax:</label>
                                            <input type="text" value="${bill.taxDesc}" class="form-control" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label>Net Amount:</label>
                                            <input type="text" value="${bill.netAmountDesc}" class="form-control" readonly>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Service Line Details -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="white_shd full margin_bottom_30">
                                <div class="full price_table padding_infor_info">
                                    <form>
                                        <div class="form-group">
                                            <label>Clinic Services:</label>
                                            <div class="row">
                                                <c:forEach items="${bill.billDetailDtoList}" var="detail">
                                                    <div class="col-md-4">
                                                        <div class="card mb-3">
                                                            <div class="card-header bg-primary text-white">
                                                                Clinic Service
                                                            </div>
                                                            <div class="card-body">
                                                                <h5 class="card-title" style="font-weight: bold;">${detail.serviceName}</h5>
                                                                <p class="card-text">
                                                                    <strong>Amount:</strong> <span class="text-muted">${detail.serviceAmountDesc}</span>
                                                                </p>
                                                                <p class="card-text">
                                                                    <strong>Quantity:</strong> <span class="text-muted">${detail.qty}</span>
                                                                </p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="form-group mt-4 d-flex align-items-center">
                                            <a href="${pageContext.request.contextPath}/bill" class="btn btn-secondary mr-2">Back</a>
                                            <a href="${pageContext.request.contextPath}/bill/paySlip/${bill.billId}" class="btn btn-info">
                                                <i class="fas fa-file-invoice-dollar"></i> Pay Slip
                                            </a>
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
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/layout/footer.jsp" />