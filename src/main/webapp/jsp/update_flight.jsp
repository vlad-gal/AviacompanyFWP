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

    <title><fmt:message key="local.updateFlight"/></title>
</head>
<body class="login-page">
<c:import url="common/header.jsp"/>
<main class="content">
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="card-wrapper">
                <div class="card fat">
                    <div class="card-body">
                        <h5 class="card-title text-center"><fmt:message key="local.updateFlight"/></h5>
                        <c:if test="${updatingSuccessfulFlag eq true}">
                            <h6 class="alert-success text-center"><fmt:message key="local.updatingSuccessful"/></h6>
                        </c:if>
                        <c:if test="${errorValidationFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.errorValidation"/></h6>
                        </c:if>
                        <c:if test="${errorUpdateFlightFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.errorUpdating"/></h6>
                        </c:if>
                        <form method="POST" class="login-validation" name="updateFlightForm"
                              action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="update_flight">
                            <div class="form-group">
                                <label for="departureAirport"><fmt:message key="local.departureAirport"/>*</label>
                                <select id="departureAirport" name="departureAirport" class="custom-select" required>
                                    <c:forEach var="airport" items="${airports}">
                                        <c:choose>
                                            <c:when test="${updatingFlight.departureAirport.id == airport.id}">
                                                <option value="${updatingFlight.departureAirport.id}" selected>
                                                        ${updatingFlight.departureAirport.airportName}, ${updatingFlight.departureAirport.city}, ${updatingFlight.departureAirport.country}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${airport.id}">
                                                        ${airport.airportName}, ${airport.city}, ${airport.country}
                                                </option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="destinationAirport"><fmt:message key="local.destinationAirport"/>*</label>
                                <select id="destinationAirport" name="destinationAirport" class="custom-select"
                                        required>
                                    <c:forEach var="airport" items="${airports}">
                                        <c:choose>
                                            <c:when test="${updatingFlight.destinationAirport.id == airport.id}">
                                                <option value="${updatingFlight.destinationAirport.id}"
                                                        selected>${updatingFlight.destinationAirport.airportName}, ${updatingFlight.destinationAirport.city}, ${updatingFlight.destinationAirport.country}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${airport.id}">
                                                        ${airport.airportName}, ${airport.city}, ${airport.country}
                                                </option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="departTime"><fmt:message key="local.departTime"/>*</label>
                                <input id="departTime" type="datetime-local" class="form-control" name="departTime"
                                       placeholder="<fmt:message key="local.departTime"/>"
                                       value="<fmt:formatDate value="${updatingFlight.departTime}" pattern="yyyy-M-dd'T'hh:mm"/>"
                                       required>
                            </div>
                            <div class="form-group">
                                <label for="arriveTime"><fmt:message key="local.arriveTime"/>*</label>
                                <input id="arriveTime" type="datetime-local" class="form-control" name="arriveTime"
                                       placeholder="<fmt:message key="local.arriveTime"/>"
                                       value="<fmt:formatDate value="${updatingFlight.arriveTime}" pattern="yyyy-M-dd'T'hh:mm"/>"
                                       required>
                            </div>
                            <div class="form-group">
                                <label for="crew"><fmt:message key="local.crews"/>*</label>
                                <select id="crew" name="crew" class="custom-select" required>
                                    <c:forEach var="crew" items="${crews}">
                                        <c:choose>
                                            <c:when test="${updatingFlight.crew.id == crew.id}">
                                                <option value="${updatingFlight.crew.id}"
                                                        selected>${updatingFlight.crew.crewName}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${crew.id}">
                                                        ${crew.crewName}
                                                </option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="aircraft"><fmt:message key="local.aircraft"/>*</label>
                                <select id="aircraft" name="aircraft" class="custom-select" required>
                                    <c:forEach var="aircraft" items="${aircrafts}">
                                        <c:choose>
                                            <c:when test="${updatingFlight.aircraft.id == aircraft.id}">
                                                <option value="${updatingFlight.aircraft.id}"
                                                        selected>${updatingFlight.aircraft.aircraftName}, <fmt:message key="local.tailNumber"/>
                                                    - ${updatingFlight.aircraft.tailNumber}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${aircraft.id}">
                                                        ${aircraft.aircraftName}, <fmt:message key="local.tailNumber"/>
                                                    - ${aircraft.tailNumber}
                                                </option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="operator"><fmt:message key="local.operator"/>*</label>
                                <select id="operator" name="operator" class="custom-select" required>
                                    <c:forEach var="operator" items="${operators}">
                                        <c:choose>
                                            <c:when test="${updatingFlight.operator.id == operator.id}">
                                                <option value="${updatingFlight.operator.id}"
                                                        selected>${updatingFlight.operator.firstName} ${updatingFlight.operator.lastName}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${operator.id}">
                                                        ${operator.firstName} ${operator.lastName}
                                                </option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="status"><fmt:message key="local.status"/>*</label>
                                <select id="status" name="status" class="custom-select">
                                    <c:forEach var="status" items="${sessionScope.statuses}" end="2">
                                        <c:choose>
                                            <c:when test="${updatingFlight.status == status}">
                                                <option selected>${status.statusName}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option>
                                                        ${status.statusName}
                                                </option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group m-0">
                                <input type="submit" onclick="checkTime();checkSameAirports()"
                                       class="btn btn-primary btn-block"
                                       value="<fmt:message key="local.change"/>">
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