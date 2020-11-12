<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">

    <title><fmt:message key="local.changePassword"/></title>
</head>
<body class="login-page">
<c:import url="common/header.jsp"/>
<main class="content">
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="card-wrapper">
                <div class="card fat">
                    <div class="card-body">
                        <h5 class="card-title text-center"><fmt:message key="local.changePassword"/></h5>
                        <c:if test="${errorValidationFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.errorValidation"/></h6>
                        </c:if>
                        <c:if test="${updatingSuccessfulFlag eq true}">
                            <h6 class="alert-success text-center"><fmt:message key="local.updatingSuccessful"/></h6>
                        </c:if>
                        <c:if test="${errorUpdatePasswordFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.errorUpdating"/></h6>
                        </c:if>
                        <form method="POST" name="changePasswordForm"
                              action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="change_password">
                            <div class="form-group">
                                <label for="password"><fmt:message key="local.newPassword"/>*</label>
                                <input id="password" type="password" class="form-control" name="password"
                                       placeholder="<fmt:message key="local.newPassword"/>" pattern="[^\s]{8,25}"
                                       title="<fmt:message key="local.incorrectPasswordMessage"/>"
                                       value="" required>
                            </div>
                            <div class="form-group">
                                <label for="confirmPassword"><fmt:message key="local.confirmPassword"/>*</label>
                                <input id="confirmPassword" type="password" class="form-control" name="confirmPassword"
                                       placeholder="<fmt:message key="local.confirmPassword"/>" pattern="[^\s]{8,25}"
                                       title="<fmt:message key="local.incorrectPasswordMessage"/>"
                                       value="" required>
                            </div>
                            <div class="form-group m-0">
                                <input type="submit" class="btn btn-primary btn-block"
                                       value="<fmt:message key="local.change"/>">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<c:import url="common/footer.jsp"/>
</body>
</html>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/check-password.js"></script>
<script src="${pageContext.request.contextPath}/js/security.js"></script>