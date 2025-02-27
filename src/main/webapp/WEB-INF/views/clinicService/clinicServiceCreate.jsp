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
                                <h2>Add New Clinic Service</h2>
                            </div>
                        </div>
                    </div>

                    <div class="row column1">
                        <div class="col-md-12">
                            <div class="white_shd full margin_bottom_30">
                                <div class="full price_table padding_infor_info">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <form action="${pageContext.request.contextPath}/clinicService/save" method="post">
                                                <div class="row">
                                                    <!-- Left Column -->
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label for="name">Name: </label>
                                                            <input type="text" id="name" name="name" class="form-control" required>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="amount">Amount: </label>
                                                            <input type="number" id="amount" name="amount" class="form-control" required>
                                                        </div>
                                                    </div>

                                                    <!-- Right Column -->
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label for="description">Description:</label>
                                                            <input type="text" id="description" name="description" class="form-control" required>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group mt-3">
                                                    <button type="submit" class="btn btn-primary">Save</button>
                                                    <a href="${pageContext.request.contextPath}/clinicService" class="btn btn-secondary">Cancel</a>
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