<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="local"/>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">

    <title><fmt:message key="local.common.successfulChangePasswordTitle"/></title>
</head>
<body>
<div class="container text-center">
    <div class="container h-50">
        <div style="margin-top: 20%">
            <h2><fmt:message key="local.common.successfulChangePasswordTitle"/></h2>
            <a href="${pageContext.request.contextPath}/controller?command=authorization_page"><fmt:message key="local.common.authorizationTitle"/></a>
        </div>
    </div>
</div>
</body>
</html>
