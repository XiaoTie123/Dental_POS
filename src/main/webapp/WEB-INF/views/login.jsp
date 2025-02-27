<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/static/images/layout_img/logo.jpg">
    <title>Login Page</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="/static/css/login.css">
</head>
<body class="login-body">
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="login-box">
        <h4 class="text-center" style="color:gray">ACCOUNT LOGIN</h4>
        <form action="/login" method="post">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="User name" name="email" required>
                <input type="password" class="form-control" placeholder="Password" name="password" required>
            </div>
            <button type="submit" class="btn btn-dark btn-block mt-3">SIGN IN</button>
        </form>
    </div>
</div>
</body>
</html>


