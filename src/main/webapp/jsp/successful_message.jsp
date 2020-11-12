<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">

    <title><fmt:message key="local.messagesTitle"/></title>
</head>
<body>
<div class="container text-center">
    <div class="container">
        <div class="mt-5">
            <c:if test="${registrationSuccessfulFlag eq true}">
                <h2><fmt:message key="local.successfulRegistrationTitle"/></h2>
                <h6>
                    <fmt:message key="local.successfulRegistrationText"/>
                </h6>
                <a href="${pageContext.request.contextPath}/controller?command=welcome_page"><fmt:message key="local.goToWelcome"/></a>
            </c:if>
            <c:if test="${activationSuccessfulFlag eq true}">
                <h2><fmt:message key="local.successfulActivationTitle"/></h2>
                <a href="${pageContext.request.contextPath}/controller?command=authorization_page"><fmt:message key="local.authorizationTitle"/></a>
            </c:if>
            <c:if test="${updatingSuccessfulFlag eq true}">
                <h2><fmt:message key="local.successfulChangePasswordTitle"/></h2>
                <a href="${pageContext.request.contextPath}/controller?command=authorization_page"><fmt:message key="local.authorizationTitle"/></a>
            </c:if>
            <c:if test="${resetLinkSentSuccessfulFlag eq true}">
                <h2><fmt:message key="local.resetLinkSentText"/></h2>
                <a href="${pageContext.request.contextPath}/controller?command=welcome_page"><fmt:message key="local.goToWelcome"/></a>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
<script src="${pageContext.request.contextPath}/js/security.js"></script>