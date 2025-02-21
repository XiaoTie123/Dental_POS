<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav id="sidebar" style="background-image: url('${pageContext.request.contextPath}/static/images/layout_img/pattern_h.png')">
    <h4>General</h4>
    <ul class="list-unstyled components">
        <li><a href="dashboard"><i class="fas fa-chart-line"></i> <span>Dashboard</span></a></li>
        <li><a href="${pageContext.request.contextPath}/patients"><i class="fas fa-user-injured"></i> <span>Patients</span></a></li>
        <li><a href="${pageContext.request.contextPath}/appointment"><i class="fas fa-calendar-check"></i> <span>Appointments</span></a></li>
        <li><a href="${pageContext.request.contextPath}/billing"><i class="fas fa-file-invoice-dollar"></i> <span>Billing</span></a></li>

    </ul>
</nav>
