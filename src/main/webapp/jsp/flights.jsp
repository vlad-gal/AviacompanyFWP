<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.flight.title" var="flightsTitle"/>
<fmt:message bundle="${local}" key="local.flight.emptyFlightList" var="emptyFlightList"/>
<fmt:message bundle="${local}" key="local.flight.departureAirport" var="depatureAirport"/>
<fmt:message bundle="${local}" key="local.flight.destinationAirport" var="destinationAirport"/>
<fmt:message bundle="${local}" key="local.flight.departTime" var="departTime"/>
<fmt:message bundle="${local}" key="local.flight.arriveTime" var="arriveTime"/>
<fmt:message bundle="${local}" key="local.flight.description" var="flightsDescription"/>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">

    <title>${flightsTitle}</title>
</head>
<jsp:include page="common/header.jsp"/>
<body>
    <div class="container h-100">

        <div class="row h-100">
            <div class="align-self-center mr-auto col-4">
                <h5>${flightsTitle}</h5>
                <p class="card-text">${flightsDescription}</p>
            </div>
            <div class="col-8 ml-auto">
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
                                        <th scope="col">${depatureAirport}</th>
                                        <th scope="col">${destinationAirport}</th>
                                        <th scope="col">${departTime}</th>
                                        <th scope="col">${arriveTime}</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="flight" items="${flightList}" end="9">
                                        <tr class="row-a"
                                            onclick="document.location ='controller?command=detail_page&flightId=${flight.id}'">
                                            <td>${flight.departureAirport.airportName}, ${flight.departureAirport.city}, ${flight.departureAirport.country}</td>
                                            <td>${flight.destinationAirport.airportName}, ${flight.destinationAirport.city}, ${flight.destinationAirport.country}</td>
                                            <td><fmt:formatDate value="${flight.departTime}"
                                                                pattern="d-MMMM-yyyy h:m"/></td>
                                            <td><fmt:formatDate value="${flight.arriveTime}"
                                                                pattern="d-MMMM-yyyy h:m"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${errorGetFlightDataFromDB ne null}">
                            <div class="text-center">
                                <h5 class="card-title">${errorGetFlightDataFromDB}</h5>
                            </div>
                        </c:if>
                    </div>
                </div>
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
</script>