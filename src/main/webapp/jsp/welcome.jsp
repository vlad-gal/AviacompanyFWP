<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.common.title" var="headTitle"/>
<fmt:message bundle="${local}" key="local.about.title" var="aboutTitle"/>
<fmt:message bundle="${local}" key="local.about.text" var="aboutText"/>
<fmt:message bundle="${local}" key="local.img.aircraft" var="aircraftImg"/>
<fmt:message bundle="${local}" key="local.flight.title" var="flightsTitle"/>
<fmt:message bundle="${local}" key="local.flight.emptyFlightList" var="emptyFlightList"/>
<fmt:message bundle="${local}" key="local.flight.departureAirport" var="depatureAirport"/>
<fmt:message bundle="${local}" key="local.flight.destinationAirport" var="destinationAirport"/>
<fmt:message bundle="${local}" key="local.flight.departTime" var="departTime"/>
<fmt:message bundle="${local}" key="local.flight.arriveTime" var="arriveTime"/>
<fmt:message bundle="${local}" key="local.flight.description" var="flightsDescription"/>
<fmt:message bundle="${local}" key="local.flight.button" var="flightsButton"/>
<fmt:message bundle="${local}" key="local.aircraft.title" var="aircraftsTitle"/>
<fmt:message bundle="${local}" key="local.aircraft.emptyAircraftList" var="emptyAircraftList"/>
<fmt:message bundle="${local}" key="local.aircraft.tailNumber" var="aircraftTailNumber"/>
<fmt:message bundle="${local}" key="local.aircraft.name" var="aircraftName"/>
<fmt:message bundle="${local}" key="local.aircraft.type" var="aircraftType"/>
<fmt:message bundle="${local}" key="local.aircraft.description" var="aircraftsDescription"/>
<fmt:message bundle="${local}" key="local.aircraft.button" var="aircraftButton"/>
<fmt:message bundle="${local}" key="local.airport.title" var="airportsTitle"/>
<fmt:message bundle="${local}" key="local.airport.emptyAirportList" var="emptyAirportList"/>
<fmt:message bundle="${local}" key="local.airport.name" var="airportName"/>
<fmt:message bundle="${local}" key="local.airport.city" var="airportCity"/>
<fmt:message bundle="${local}" key="local.airport.country" var="airportCountry"/>
<fmt:message bundle="${local}" key="local.airport.description" var="airportDescription"/>
<fmt:message bundle="${local}" key="local.airport.button" var="airportButton"/>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">

    <title>${headTitle}</title>
</head>
<jsp:include page="common/header.jsp"/>
<body>
<div class="container">
    <div class="row">
        <div class="col-12 col-lg-6 col-xl-5 offset-xl-1 align-self-center">
            <h1>${aboutTitle}</h1>
            <p>${aboutText}</p>
        </div>
        <div class="col-md-5 col-lg-5 offset-lg-1 offset-xl-0 d-none d-lg-block">
            <div>
                <img src="${pageContext.request.contextPath}/img/aircraft.jpg" alt="${aircraftImg}">
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="card">
        <div class="card-header text-center">
            <h5>${flightsTitle}</h5>
        </div>
        <div class="card-body">
            <c:choose>
                <c:when test="${empty flightList}">
                    <div class="text-center">
                        <h5 class="card-title">${emptyFlightList}</h5>
                    </div>
                </c:when>
                <c:otherwise>
                    <table class="table" id="sort-flight">
                        <thead class="thead-light">
                        <tr class="row-a">
                            <th scope="col" >${depatureAirport}</th>
                            <th scope="col">${destinationAirport}</th>
                            <th scope="col" >${departTime}</th>
                            <th scope="col" >${arriveTime}</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="flight" items="${flightList}" end="9">
                            <tr class="row-a"
                                onclick="document.location ='controller?command=detail_page&flightId=${flight.id}'">
                                <td>${flight.departureAirport.airportName}, ${flight.departureAirport.city}, ${flight.departureAirport.country}</td>
                                <td>${flight.destinationAirport.airportName}, ${flight.destinationAirport.city}, ${flight.destinationAirport.country}</td>
                                <td><fmt:formatDate value="${flight.departTime}" pattern="d-MMMM-yyyy h:m"/></td>
                                <td><fmt:formatDate value="${flight.arriveTime}" pattern="d-MMMM-yyyy h:m"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <p class="card-text">${flightsDescription}</p>
                    <div class="text-center">
                        <a href="controller?command=flights_page"
                           class="btn btn-primary align-content-center">${flightsButton}</a>
                    </div>
                </c:otherwise>
            </c:choose>
            <c:if test="${errorGetFlightDataFromDB ne null}">
                <div class="text-center">
                    <h5 class="card-title">${errorGetFlightDataFromDB}</h5>
                </div>
            </c:if>
        </div>
    </div>
    <div class="card">
        <div class="card-header text-center">
            <h5>${aircraftsTitle}</h5>
        </div>
        <div class="card-body">
            <c:choose>
                <c:when test="${empty aircraftList}">
                    <div class="text-center">
                        <h5 class="card-title">${emptyAircraftList}</h5>
                    </div>
                </c:when>
                <c:otherwise>
                    <table class="table" id="sort-aircraft">
                        <thead class="thead-light">
                        <tr class="row-a">
                            <th scope="col" >${aircraftTailNumber}</th>
                            <th scope="col">${aircraftName}</th>
                            <th scope="col" >${aircraftType}</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="aircraft" items="${aircraftList}" end="9">
                            <tr class="row-a"
                                onclick="document.location ='controller?command=detail_page&aircraftId=${aircraft.id}'">
                                <td>${aircraft.tailNumber}</td>
                                <td>${aircraft.aircraftName}</td>
                                <td>${aircraft.aircraftType}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <p class="card-text">${aircraftsDescription}</p>
                    <div class="text-center">
                        <a href="controller?command=aircrafts_page"
                           class="btn btn-primary align-content-center">${aircraftButton}</a>
                    </div>
                </c:otherwise>
            </c:choose>
            <c:if test="${errorGetAircraftDataFromDB ne null}">
                <div class="text-center">
                    <h5 class="card-title">${errorGetAircraftDataFromDB}</h5>
                </div>
            </c:if>
        </div>
    </div>
    <div class="card">
        <div class="card-header text-center">
            <h5>${airportsTitle}</h5>
        </div>
        <div class="card-body">
            <c:choose>
                <c:when test="${empty airportList}">
                    <div class="text-center">
                        <h5 class="card-title">${emptyAirportList}</h5>
                    </div>
                </c:when>
                <c:otherwise>
                    <table class="table" id="sort-airport">
                        <thead class="thead-light">
                        <tr class="row-a">
                            <th scope="col" >${airportName}</th>
                            <th scope="col" >${airportCountry}</th>
                            <th scope="col" >${airportCity}</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="airport" items="${airportList}" end="9">
                            <tr class="row-a"
                                onclick="document.location ='controller?command=detail_page&airportId=${airport.id}'">
                                <td>${airport.airportName}</td>
                                <td>${airport.country}</td>
                                <td>${airport.city}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <p class="card-text">${airportDescription}</p>
                    <div class="text-center">
                        <a href="controller?command=airports_page"
                           class="btn btn-primary align-content-center">${airportButton}</a>
                    </div>
                </c:otherwise>
            </c:choose>
            <c:if test="${errorGetAirportDataFromDB ne null}">
                <div class="text-center">
                    <h5 class="card-title">${errorGetAirportDataFromDB}</h5>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
<jsp:include page="common/footer.jsp"/>

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
    new Tablesort(document.getElementById('sort-aircraft'));
    new Tablesort(document.getElementById('sort-airport'));
</script>