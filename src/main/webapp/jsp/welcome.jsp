<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tablesort.css">

    <title><fmt:message key="local.companyName"/></title>
</head>
<body>
<c:import url="common/header.jsp"/>
<main class="content">
    <div class="container">
        <div class="container">
            <div class="row">
                <div class="col-12 col-lg-6 col-xl-5 offset-xl-1 align-self-center">
                    <h1><fmt:message key="local.aboutCompanyTitle"/></h1>
                    <p><fmt:message key="local.aboutCompanyText"/></p>
                </div>
                <div class="col-md-5 col-lg-5 offset-lg-1 offset-xl-0 d-none d-lg-block">
                    <div>
                        <img src="${pageContext.request.contextPath}/img/aircraft.jpg"
                             alt="<fmt:message key="local.imgAircraft"/>">
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="card">
                <div class="card-header text-center">
                    <h5><fmt:message key="local.currentFlights"/></h5>
                </div>
                <div class="card-body">
                    <c:choose>
                        <c:when test="${empty flightList}">
                            <div class="text-center">
                                <h5 class="card-title"><fmt:message key="local.emptyFlightList"/></h5>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <table class="table table-hover" id="sort-flight">
                                <thead class="thead-light">
                                <tr>
                                    <th scope="col"><fmt:message key="local.departureAirport"/></th>
                                    <th scope="col"><fmt:message key="local.destinationAirport"/></th>
                                    <th scope="col"><fmt:message key="local.departTime"/></th>
                                    <th scope="col"><fmt:message key="local.arriveTime"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="flight" items="${flightList}" end="4">
                                    <tr>
                                        <td>${flight.departureAirport.airportName}, ${flight.departureAirport.city}, ${flight.departureAirport.country}</td>
                                        <td>${flight.destinationAirport.airportName}, ${flight.destinationAirport.city}, ${flight.destinationAirport.country}</td>
                                        <td><fmt:formatDate value="${flight.departTime}" pattern="d-MMMM-yyyy hh:mm"/></td>
                                        <td><fmt:formatDate value="${flight.arriveTime}" pattern="d-MMMM-yyyy hh:mm"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <p class="card-text"><fmt:message key="local.descriptionFlights"/></p>
                            <div class="text-center">
                                <a href="controller?command=flights_page"
                                   class="btn btn-primary align-content-center"><fmt:message
                                        key="local.showAllActualFlightsButton"/></a>
                            </div>
                        </c:otherwise>
                    </c:choose>
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
<script src="${pageContext.request.contextPath}/js/tablesort.min.js"></script>
<script src="${pageContext.request.contextPath}/js/security.js"></script>
<script>
    new Tablesort(document.getElementById('sort-flight'));
</script>