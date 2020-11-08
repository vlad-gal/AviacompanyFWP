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

    <title><fmt:message key="local.common.resetPasswordTitle"/></title>
</head>
<body class="login-page">
<main class="content">
        <div class="container">
            <div class="row justify-content-md-center align-items-center">
                <div class="card-wrapper">
                    <div class="card fat">
                        <div class="card-body">
                            <h5 class="card-title text-center"><fmt:message key="local.common.resetPasswordTitle"/></h5>
                            <form method="POST" name="resetPasswordForm" action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="command" value="reset_password">
                                <div class="form-group m-2">
                                    <label for="password"><fmt:message key="local.common.newPassword"/></label>
                                    <input id="password" type="password" class="form-control" name="password"
                                           placeholder="<fmt:message key="local.common.password"/>"
                                           value="" pattern="[\w-!@#$%^&*~,.:;/]{8,25}"
                                           title="<fmt:message key="local.common.incorrectPasswordMessage"/>" required
                                           autofocus>
                                    <c:if test="${incorrectPasswordFlag eq true}">
                                        <div class="errorLoginPass">
                                            <p class="errorLoginPass text-center"><fmt:message
                                                    key="local.common.incorrectPasswordMessage"/></p>
                                        </div>
                                    </c:if>
                                    <c:if test="${emailNotExistFlag eq true}">
                                        <div class="errorLoginPass">
                                            <p class="errorLoginPass text-center"><fmt:message
                                                    key="local.common.emailNotExist"/></p>
                                        </div>
                                    </c:if>
                                </div>
                                <div class="form-group m-2 mt-4">
                                    <input type="submit" class="btn btn-primary btn-block"
                                           value="<fmt:message key="local.common.resetPasswordTitle"/>">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</main>
</body>
</html>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
