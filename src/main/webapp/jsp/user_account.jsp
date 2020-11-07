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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">

    <title>${authorizationTitle}</title>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-3">
            <jsp:include page="common/sidebar.jsp"/>
        </div>
        <div class="col-9">
            <c:choose>
                <c:when test="${user.role eq 'ADMIN'}">
                    <jsp:include page="common/admin_content.jsp"/>
                </c:when>
                <c:when test="${user.role eq 'OPERATOR'}">
                    <jsp:include page="common/operator_content.jsp"/>
                </c:when>
                <c:when test="${user.role eq 'DISPATCHER'}">
                    <jsp:include page="common/dispatcher_content.jsp"/>
                </c:when>
                <c:otherwise>
                    <jsp:include page="common/staff_content.jsp"/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
