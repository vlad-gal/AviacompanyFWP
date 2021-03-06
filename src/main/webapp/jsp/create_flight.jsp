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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">

    <title><fmt:message key="local.createFlight"/></title>
</head>
<body class="login-page">
<c:import url="common/header.jsp"/>
<main class="content">
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="card-wrapper">
                <div class="card fat">
                    <div class="card-body">
                        <h5 class="card-title text-center"><fmt:message key="local.createFlight"/></h5>
                        <c:if test="${errorCreateFlightFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.errorCreateFlight"/></h6>
                        </c:if>
                        <c:if test="${createFlightSuccessfulFlag eq true}">
                            <h6 class="alert-success text-center"><fmt:message key="local.createFlightSuccessful"/></h6>
                        </c:if>
                        <c:if test="${errorValidationFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.errorValidation"/></h6>
                        </c:if>
                        <form method="POST" name="createFlightForm" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="create_flight">
                            <div class="form-group">
                                <label for="departureAirport"><fmt:message key="local.departureAirport"/>*</label>
                                <select id="departureAirport" name="departureAirport" class="custom-select" required>
                                    <c:if test="${errorCreateFlightFlag eq true ||errorValidationFlag eq true}">
                                        <option value="${airport.id}" selected>${departureAirport.airportName}, ${departureAirport.city}, ${departureAirport.country}</option>>
                                    </c:if>
                                    <c:forEach var="airport" items="${airports}">
                                        <option value="${airport.id}">
                                                ${airport.airportName}, ${airport.city}, ${airport.country}
                                        </option>
                                    </c:forEach>
                                </select>

                            </div>
                            <div class="form-group">
                                <label for="destinationAirport"><fmt:message
                                        key="local.destinationAirport"/>*</label>
                                <select id="destinationAirport" name="destinationAirport" class="custom-select"  required>
                                    <c:if test="${errorCreateFlightFlag eq true ||errorValidationFlag eq true}">
                                        <option value="${airport.id}" selected>${destinationAirport.airportName}, ${destinationAirport.city}, ${destinationAirport.country}</option>>
                                    </c:if>
                                    <c:forEach var="airport" items="${airports}">
                                        <option value="${airport.id}">
                                                ${airport.airportName}, ${airport.city}, ${airport.country}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="departTime"><fmt:message key="local.departTime"/>*</label>
                                <input id="departTime" type="datetime-local" class="form-control" name="departTime"
                                       placeholder="<fmt:message key="local.departTime"/>" value="${requestScope.departTime}" required>
                            </div>
                            <div class="form-group">
                                <label for="arriveTime"><fmt:message key="local.arriveTime"/>*</label>
                                <input id="arriveTime" type="datetime-local" class="form-control" name="arriveTime"
                                       placeholder="<fmt:message key="local.arriveTime"/>" value="${requestScope.arriveTime}" required>
                            </div>
                            <div class="form-group">
                                <label for="crew"><fmt:message key="local.crews"/>*</label>
                                <select id="crew" name="crew" class="custom-select" required>
                                    <c:if test="${errorCreateFlightFlag eq true ||errorValidationFlag eq true}">
                                        <option value="${crew.id}" selected>${crew.crewName}</option>>
                                    </c:if>
                                    <c:forEach var="crew" items="${crews}">
                                        <option value="${crew.id}">
                                                ${crew.crewName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="aircraft"><fmt:message key="local.aircraft"/>*</label>
                                <select id="aircraft" name="aircraft" class="custom-select" required>
                                    <c:if test="${errorCreateFlightFlag eq true ||errorValidationFlag eq true}">
                                        <option value="${aircraft.id}" selected>${aircraft.aircraftName}, <fmt:message key="local.tailNumber"/> - ${aircraft.tailNumber}</option>>
                                    </c:if>
                                    <c:forEach var="aircraft" items="${aircrafts}">
                                        <option value="${aircraft.id}">
                                                ${aircraft.aircraftName}, <fmt:message key="local.tailNumber"/> - ${aircraft.tailNumber}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group m-0">
                                <input type="submit" onclick="checkTime();checkSameAirports()" class="btn btn-primary btn-block"
                                       value="<fmt:message key="local.createFlight"/>">
                            </div>
                        </form>
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
<script src="${pageContext.request.contextPath}/js/flightValidation.js"></script>
<script src="${pageContext.request.contextPath}/js/security.js"></script>