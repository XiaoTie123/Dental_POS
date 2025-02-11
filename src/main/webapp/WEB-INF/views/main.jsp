<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dental POS Dashboard</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/static/css/style.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body class="dashboard">

<%@ include file="layout/header.jsp" %>

<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <div class="col-md-2">
            <%@ include file="layout/sidebar.jsp" %>
        </div>

        <!-- Main Content -->
        <div class="col-md-10">
            <jsp:include page="${content}" />
        </div>
    </div>
</div>

<%@ include file="layout/footer.jsp" %>

<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.bundle.min.js"></script>
<script src="/static/js/scripts.js"></script>
</body>
</html>