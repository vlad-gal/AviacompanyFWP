<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tablesort.css">

    <title><fmt:message key="local.common.title"/></title>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-12 col-lg-6 col-xl-5 offset-xl-1 align-self-center">
            <h1><fmt:message key="local.about.title"/></h1>
            <p><fmt:message key="local.about.text"/></p>
        </div>
        <div class="col-md-5 col-lg-5 offset-lg-1 offset-xl-0 d-none d-lg-block">
            <div>
                <img src="${pageContext.request.contextPath}/img/aircraft.jpg"
                     alt="<fmt:message key="local.img.aircraft"/>">
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="card">
        <div class="card-header text-center">
            <h5><fmt:message key="local.flight.title"/></h5>
        </div>
        <div class="card-body">
            <c:choose>
                <c:when test="${empty flightList}">
                    <div class="text-center">
                        <h5 class="card-title"><fmt:message key="local.flight.emptyFlightList"/></h5>
                    </div>
                    <c:if test="${errorGetDataFromDBFlag eq true}">
                        <div class="text-center">
                            <h5 class="card-title"><fmt:message key="local.error.errorGetFlightDataFromDB"/></h5>
                        </div>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <table class="table table-hover" id="sort-flight">
                        <thead class="thead-light">
                        <tr class="row-a">
                            <th scope="col"><fmt:message key="local.flight.departureAirport"/></th>
                            <th scope="col"><fmt:message key="local.flight.destinationAirport"/></th>
                            <th scope="col"><fmt:message key="local.flight.departTime"/></th>
                            <th scope="col"><fmt:message key="local.flight.arriveTime"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="flight" items="${flightList}" end="4">
                            <tr class="row-a"
                                onclick="document.location ='controller?command=flight_detail_page&flightId=${flight.id}'">
                                <td>${flight.departureAirport.airportName}, ${flight.departureAirport.city}, ${flight.departureAirport.country}</td>
                                <td>${flight.destinationAirport.airportName}, ${flight.destinationAirport.city}, ${flight.destinationAirport.country}</td>
                                <td><fmt:formatDate value="${flight.departTime}" pattern="d-MMMM-yyyy h:m"/></td>
                                <td><fmt:formatDate value="${flight.arriveTime}" pattern="d-MMMM-yyyy h:m"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <p class="card-text"><fmt:message key="local.flight.description"/></p>
                    <div class="text-center">
                        <a href="controller?command=flights_page"
                           class="btn btn-primary align-content-center"><fmt:message key="local.flight.button"/></a>
                    </div>
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
<script src="${pageContext.request.contextPath}/js/01efe1ad65.js"></script>
<script src="${pageContext.request.contextPath}/js/tablesort.min.js"></script>
<script src="${pageContext.request.contextPath}/js/tablesort.date.min.js"></script>
<script src="${pageContext.request.contextPath}/js/tablesort.number.min.js"></script>
<script>
    new Tablesort(document.getElementById('sort-flight'));
</script>