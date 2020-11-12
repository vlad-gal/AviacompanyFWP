<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>

<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">

    <title><fmt:message key="local.notFound"/> | <fmt:message key="local.error404"/></title>

</head>
<body>
<div class="container text-center">
    <div class="container">
        <div class="mt-5">
            <h1><fmt:message key="local.error404"/></h1>
            <p class="lead"><fmt:message key="local.notFound"/></p>
            <a href="${pageContext.request.contextPath}/controller?command=welcome_page"><fmt:message key="local.goToWelcome"/></a>
        </div>
    </div>
</div>
</body>
</html>