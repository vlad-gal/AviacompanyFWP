<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="ctg" uri="paginationTags" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">

    <title><fmt:message key="local.userAccount"/></title>
</head>
<body>
<c:import url="common/header.jsp"/>
<main class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-3">
                <jsp:include page="common/sidebar.jsp"/>
            </div>
            <div class="col-9">
                <c:choose>
                    <c:when test="${user.role eq 'ADMIN'}">
                        <c:import url="common/admin_content.jsp"/>
                    </c:when>
                    <c:when test="${user.role eq 'OPERATOR'}">
                        <c:import url="common/operator_content.jsp"/>
                    </c:when>
                    <c:when test="${user.role eq 'DISPATCHER'}">
                        <c:import url="common/dispatcher_content.jsp"/>
                    </c:when>
                    <c:otherwise>
                        <c:import url="common/staff_content.jsp"/>
                    </c:otherwise>
                </c:choose>
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
<script src="${pageContext.request.contextPath}/js/security.js"></script>