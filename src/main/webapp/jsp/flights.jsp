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

    <title><fmt:message key="local.flights"/></title>
</head>
<body>
<c:import url="common/header.jsp"/>
<main class="content">
    <div class="container">
        <div class="row">
            <div class="align-self-center mr-auto col-3">
                <h5><fmt:message key="local.currentFlights"/></h5>
                <p class="card-text"><fmt:message key="local.descriptionFlights"/></p>
            </div>
            <div class="col-9 ml-auto">
                <div class="card">
                    <div class="card-header text-center">
                        <h5><fmt:message key="local.flights"/></h5>
                    </div>
                    <div class="card-body">
                        <c:choose>
                        <c:when test="${empty flightList}">
                            <div class="text-center">
                                <h5 class="card-title"><fmt:message key="local.emptyFlightList"/></h5>
                            </div>
                        </c:when>
                        <c:otherwise>
                        <table class="table table-hover">
                            <thead class="thead-light">
                            <tr>
                                <th scope="col"><fmt:message key="local.departureAirport"/></th>
                                <th scope="col"><fmt:message key="local.destinationAirport"/></th>
                                <th scope="col"><fmt:message key="local.departTime"/></th>
                                <th scope="col"><fmt:message key="local.arriveTime"/></th>
                                <c:if test="${not empty sessionScope.user}">
                                    <th scope="col"><fmt:message key="local.crewName"/></th>
                                    <th scope="col"><fmt:message key="local.status"/></th>
                                    <th scope="col"><fmt:message key="local.operator"/></th>
                                    <c:if test="${sessionScope.user.role eq 'ADMIN' || sessionScope.user.role eq 'OPERATOR'}">
                                        <th scope="col"><fmt:message key="local.actions"/></th>
                                    </c:if>
                                </c:if>
                            </tr>
                            </thead>
                                <ctg:flightPagination currentPageNumber="${currentPageNumber}"/>
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