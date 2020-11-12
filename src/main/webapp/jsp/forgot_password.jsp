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

    <title><fmt:message key="local.resetPasswordTitle"/></title>
</head>
<body class="login-page">
<main class="content">
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="card-wrapper ">
                <div class="card fat">
                    <div class="card-body">
                        <h5 class="card-title text-center"><fmt:message key="local.resetPasswordTitle"/></h5>
                        <c:if test="${emailNotExistFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.emailNotExist"/></h6>
                        </c:if>
                        <form method="POST" name="forgotPasswordForm" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="forgot_password">
                            <div class="form-group">
                                <label for="email"><fmt:message key="local.email"/>*</label>
                                <input id="email" type="email" class="form-control" name="email"
                                       placeholder="<fmt:message key="local.email"/>"
                                       value="" pattern="[\w-.]{3,20}@[a-zA-Zа-яА-Я]{2,14}\.[a-zа-я]{2,6}"
                                       title="<fmt:message key="local.incorrectEmailMessage"/>" required
                                       autofocus>
                                <c:if test="${incorrectEmailFlag eq true}">
                                <div class="errorLoginPass">
                                    <p class="errorLoginPass text-center"><fmt:message key="local.incorrectEmailMessage"/></p>
                                </div>
                                </c:if>
                                <div class="form-text text-muted m-2">
                                    <fmt:message key="local.resetWarning"/>
                                </div>
                                <div class="form-group m-0">
                                    <input type="submit" class="btn btn-primary btn-block"
                                           value="<fmt:message key="local.resetPasswordTitle"/>">
                                </div>
                                <div class="mt-4 text-center">
                                    <a href="controller?command=authorization_page"><fmt:message key="local.returnToAuthorization"/></a>
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
<script src="${pageContext.request.contextPath}/js/security.js"></script>