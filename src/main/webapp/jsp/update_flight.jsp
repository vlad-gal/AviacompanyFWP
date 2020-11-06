<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">

    <title><fmt:message key="local.common.createFlight"/></title>
</head>
<body class="login-page">
<jsp:include page="common/header.jsp"/>
<section class="h-100">
    <div class="container h-100">
        <div class="row justify-content-md-center h-100">
            <div class="card-wrapper">
                <div class="card fat">
                    <div class="card-body">
                        <h5 class="card-title text-center"><fmt:message key="local.common.createFlight"/></h5>
                        <c:if test="${updatingSuccessfulFlag eq true}">
                            <h6 class="alert-success text-center"><fmt:message key="local.common.updatingSuccessful"/></h6>
                        </c:if>
                        <c:if test="${errorValidationFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.common.errorValidation"/></h6>
                        </c:if>
                        <c:if test="${errorUpdateFlightFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.common.errorUpdating"/></h6>
                        </c:if>
                        <form method="POST" class="login-validation" name="registrationForm"
                              action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="create_flight">
                            <div class="form-group">
                                <label for="departureAirport"><fmt:message key="local.flight.departureAirport"/></label>
                                <select id="departureAirport" name="departureAirport" class="custom-select" required>
                                    <option selected>${updatingFlight.departureAirport.airportName}, ${updatingFlight.departureAirport.city}, ${updatingFlight.departureAirport.country}</option>
                                    <c:forEach var="airport" items="${airports}">
                                        <option value="${airport.id}">
                                                ${airport.airportName}, ${airport.city}, ${airport.country}
                                        </option>
                                    </c:forEach>
                                </select>

                            </div>
                            <div class="form-group">
                                <label for="destinationAirport"><fmt:message
                                        key="local.flight.destinationAirport"/></label>
                                <select id="destinationAirport" name="destinationAirport" class="custom-select"  required>
                                    <option selected>${updatingFlight.destinationAirport.airportName}, ${updatingFlight.destinationAirport.city}, ${updatingFlight.destinationAirport.country}</option>
                                    <c:forEach var="airport" items="${airports}">
                                        <option value="${airport.id}">
                                                ${airport.airportName}, ${airport.city}, ${airport.country}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="departTime"><fmt:message key="local.flight.departTime"/></label>
                                <input id="departTime" type="datetime-local" class="form-control" name="departTime"
                                       placeholder="<fmt:message key="local.flight.departTime"/>" value="<fmt:formatDate value="${updatingFlight.departTime}" pattern="yyyy-M-dd'T'hh:mm"/>" required>
                            </div>
                            <div class="form-group">
                                <label for="arriveTime"><fmt:message key="local.flight.arriveTime"/></label>
                                <input id="arriveTime" type="datetime-local" class="form-control" name="arriveTime"
                                       placeholder="<fmt:message key="local.flight.arriveTime"/>" value="<fmt:formatDate value="${updatingFlight.arriveTime}" pattern="yyyy-M-dd'T'hh:mm"/>" required>
                            </div>
                            <div class="form-group">
                                <label for="crew"><fmt:message key="local.crew.crews"/></label>
                                <select id="crew" name="crew" class="custom-select" required>
                                    <option selected>${updatingFlight.crew.crewName}</option>
                                    <c:forEach var="crew" items="${crews}">
                                        <option value="${crew.id}">
                                                ${crew.crewName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="aircraft"><fmt:message key="local.common.aircraft"/></label>
                                <select id="aircraft" name="aircraft" class="custom-select" required>
                                    <option selected>${updatingFlight.aircraft.aircraftName}, <fmt:message key="local.aircraft.tailNumber"/> - ${updatingFlight.aircraft.tailNumber}</option>
                                    <c:forEach var="aircraft" items="${aircrafts}">
                                        <option value="${aircraft.id}">
                                                ${aircraft.aircraftName}, <fmt:message key="local.aircraft.tailNumber"/> - ${aircraft.tailNumber}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="operator"><fmt:message key="local.common.aircraft"/></label>
                                <select id="operator" name="operator" class="custom-select" required>
                                    <option selected>${updatingFlight.operator.firstName} ${updatingFlight.operator.lastName}</option>
                                    <c:forEach var="operator" items="${operators}">
                                        <option value="${operator.id}">
                                                ${operator.firstName} ${operator.lastName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group m-0">
                                <input type="submit" onclick="checkTime();checkSameAirports()" class="btn btn-primary btn-block"
                                       value="<fmt:message key="local.common.updateFlight"/>">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/01efe1ad65.js"></script>
<script src="${pageContext.request.contextPath}/js/flightValidation.js"></script>
