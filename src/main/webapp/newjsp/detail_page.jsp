<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>

<%--<fmt:setLocale value="${sessionScope.lang}"/>--%>
<%--<fmt:setBundle basename="local"/>--%>
<%--<!doctype html>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<meta charset="utf-8">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">--%>

    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">--%>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">--%>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/tablesort.css">--%>

    <%--<title>--%>
        <%--<fmt:message key="local.detail.detailPageTitle"/>--%>
    <%--</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<jsp:include page="common/header.jsp"/>--%>
<%--<div class="container">--%>
    <%--<div class="row h-100">--%>
        <%--<c:if test="${not empty param.flightId}">--%>
            <%--<div class="container align-text-top">--%>
                <%--<div class="card">--%>
                    <%--<div class="card-header text-center">--%>
                        <%--<h5><fmt:message key="local.common.flight"/></h5>--%>
                    <%--</div>--%>
                    <%--<div class="card-body">--%>
                        <%--<c:choose>--%>
                            <%--<c:when test="${flightNotFoundFlag eq true}">--%>
                                <%--<div class="text-center">--%>
                                    <%--<h5><fmt:message key="local.detail.flightNotFound"/></h5>--%>
                                <%--</div>--%>
                            <%--</c:when>--%>
                            <%--<c:when test="${incorrectIdFlag eq true}">--%>
                                <%--<div class="text-center">--%>
                                    <%--<h5><fmt:message key="local.detail.incorrectId"/></h5>--%>
                                <%--</div>--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                                <%--<table class="table">--%>
                                    <%--<thead class="thead-light">--%>
                                    <%--<tr class="row-a">--%>
                                        <%--<th scope="col"><fmt:message key="local.departureAirport"/></th>--%>
                                        <%--<th scope="col"><fmt:message key="local.destinationAirport"/></th>--%>
                                        <%--<th scope="col"><fmt:message key="local.departTime"/></th>--%>
                                        <%--<th scope="col"><fmt:message key="local.arriveTime"/></th>--%>
                                    <%--</tr>--%>
                                    <%--</thead>--%>
                                    <%--<tbody>--%>
                                    <%--<tr class="row-a">--%>
                                        <%--<td>${flight.departureAirport.airportName}, ${flight.departureAirport.city}, ${flight.departureAirport.country}</td>--%>
                                        <%--<td>${flight.destinationAirport.airportName}, ${flight.destinationAirport.city}, ${flight.destinationAirport.country}</td>--%>
                                        <%--<td><fmt:formatDate value="${flight.departTime}"--%>
                                                            <%--pattern="d-MMMM-yyyy h:m"/></td>--%>
                                        <%--<td><fmt:formatDate value="${flight.arriveTime}"--%>
                                                            <%--pattern="d-MMMM-yyyy h:m"/></td>--%>
                                    <%--</tr>--%>
                                    <%--</tbody>--%>
                                <%--</table>--%>
                            <%--</c:otherwise>--%>
                        <%--</c:choose>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</c:if>--%>
        <%--<c:if test="${not empty param.airportId}">--%>
            <%--<div class="container align-text-top">--%>
                <%--<div class="card">--%>
                    <%--<div class="card-header text-center">--%>
                        <%--<h5><fmt:message key="local.common.airport"/></h5>--%>
                    <%--</div>--%>

                    <%--<div class="card-body">--%>
                        <%--<c:choose>--%>
                            <%--<c:when test="${airportNotFoundFlag eq true}">--%>
                                <%--<div class="text-center">--%>
                                    <%--<h5><fmt:message key="local.detail.airportNotFound"/></h5>--%>
                                <%--</div>--%>
                            <%--</c:when>--%>
                            <%--<c:when test="${incorrectIdFlag eq true}">--%>
                                <%--<div class="text-center">--%>
                                    <%--<h5><fmt:message key="local.detail.incorrectId"/></h5>--%>
                                <%--</div>--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                                <%--<table class="table">--%>
                                    <%--<thead class="thead-light">--%>
                                    <%--<tr class="row-a">--%>
                                        <%--<th scope="col"><fmt:message key="local.airportName"/></th>--%>
                                        <%--<th scope="col"><fmt:message key="local.airportCountry"/></th>--%>
                                        <%--<th scope="col"><fmt:message key="local.airportCity"/></th>--%>
                                    <%--</tr>--%>
                                    <%--</thead>--%>
                                    <%--<tbody>--%>
                                    <%--<tr class="row-a">--%>
                                        <%--<td>${airport.airportName}</td>--%>
                                        <%--<td>${airport.country}</td>--%>
                                        <%--<td>${airport.city}</td>--%>
                                    <%--</tr>--%>
                                    <%--</tbody>--%>
                                <%--</table>--%>
                            <%--</c:otherwise>--%>
                        <%--</c:choose>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</c:if>--%>
        <%--<c:if test="${not empty param.aircraftId }">--%>
            <%--<div class="container align-text-top">--%>
                <%--<div class="card">--%>
                    <%--<div class="card-header text-center">--%>
                        <%--<h5><fmt:message key="local.aircraft"/></h5>--%>
                    <%--</div>--%>
                    <%--<div class="card-body">--%>
                        <%--<c:choose>--%>
                            <%--<c:when test="${aircraftNotFoundFlag eq true}">--%>
                                <%--<div class="text-center">--%>
                                    <%--<h5><fmt:message key="local.detail.aircraftNotFound"/></h5>--%>
                                <%--</div>--%>
                            <%--</c:when>--%>
                            <%--<c:when test="${incorrectIdFlag eq true}">--%>
                                <%--<div class="text-center">--%>
                                    <%--<h5><fmt:message key="local.detail.incorrectId"/></h5>--%>
                                <%--</div>--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                                <%--<table class="table">--%>
                                    <%--<thead class="thead-light">--%>
                                    <%--<tr class="row-a">--%>
                                        <%--<th scope="col"><fmt:message key="local.tailNumber"/></th>--%>
                                        <%--<th scope="col"><fmt:message key="local.aircraftName"/></th>--%>
                                        <%--<th scope="col"><fmt:message key="local.aircraftType"/></th>--%>
                                    <%--</tr>--%>
                                    <%--</thead>--%>
                                    <%--<tbody>--%>
                                    <%--<tr class="row-a">--%>
                                        <%--<td>${aircraft.tailNumber}</td>--%>
                                        <%--<td>${aircraft.aircraftName}</td>--%>
                                        <%--<td>${aircraft.aircraftType}</td>--%>
                                    <%--</tr>--%>
                                    <%--</tbody>--%>
                                <%--</table>--%>
                            <%--</c:otherwise>--%>
                        <%--</c:choose>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</c:if>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<jsp:include page="common/footer.jsp"/>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.slim.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>--%>
