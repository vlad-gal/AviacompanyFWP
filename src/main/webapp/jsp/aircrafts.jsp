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

    <title><fmt:message key="local.aircrafts"/></title>
</head>
<body>
<c:import url="common/header.jsp"/>
<main class="content">
    <div class="container">
        <div class="row">
            <div class="align-self-center mr-auto col-6">
                <h5><fmt:message key="local.aircraftsTitle"/></h5>
                <p class="card-text"><fmt:message key="local.aircraftsDescription"/></p>
            </div>
            <div class="col-6 ml-auto">
                <div class="card ">
                    <div class="card-header text-center">
                        <h5><fmt:message key="local.aircrafts"/></h5>
                    </div>
                    <div class="card-body">
                        <c:choose>
                        <c:when test="${empty aircraftList}">
                            <div class="text-center">
                                <h5 class="card-title"><fmt:message key="local.emptyAircraftList"/></h5>
                            </div>
                        </c:when>
                        <c:otherwise>
                        <table class="table table-hover">
                            <thead class="thead-light">
                            <tr>
                                <th scope="col"><fmt:message key="local.tailNumber"/></th>
                                <th scope="col"><fmt:message key="local.aircraftName"/></th>
                                <th scope="col"><fmt:message key="local.aircraftType"/></th>
                                <c:if test="${sessionScope.user.role eq 'OPERATOR' or sessionScope.user.role eq 'ADMIN'}">
                                    <th scope="col"><fmt:message key="local.status"/></th>
                                </c:if>
                                <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                                    <th scope="col"><fmt:message key="local.actions"/></th>
                                </c:if>
                            </tr>
                            </thead>
                                <ctg:aircraftPagination currentPageNumber="${currentPageNumber}"/>
                            </c:otherwise>
                            </c:choose>
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
<script src="${pageContext.request.contextPath}/js/security.js"></script>