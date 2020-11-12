<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>

<nav class="d-none d-md-block bg-light sidebar">
    <div class="sidebar-sticky">
        <ul class="nav flex-column">
            <c:choose>
                <c:when test="${user.role eq 'ADMIN'}">
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-2 mb-1 text-muted">
                        <span><fmt:message key="local.users"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=create_user_page">
                            <fmt:message key="local.createUser"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_users&status=active">
                            <fmt:message key="local.activeUsers"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_users&status=busy">
                            <fmt:message key="local.busyUsers"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_users&status=fly">
                            <fmt:message key="local.usersInFlight"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_users&status=inactive">
                            <fmt:message key="local.inactiveUsers"/>
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.flights"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_flights&status=active">
                            <fmt:message key="local.activeFlights"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_flights&status=inactive">
                            <fmt:message key="local.inactiveFlights"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_flights&status=fly">
                            <fmt:message key="local.flyingFlights"/>
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.crews"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=active">
                            <fmt:message key="local.activeCrews"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=busy">
                            <fmt:message key="local.busyCrews"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=inactive">
                            <fmt:message key="local.inactiveCrews"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=fly">
                            <fmt:message key="local.flyingCrews"/>
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.airports"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=create_airport_page">
                            <fmt:message key="local.createAirport"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_airports">
                            <fmt:message key="local.allAirports"/>
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.aircrafts"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=create_aircraft_page">
                            <fmt:message key="local.createAircraft"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_aircrafts&status=active">
                            <fmt:message key="local.activeAircrafts"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_aircrafts&status=busy">
                            <fmt:message key="local.busyAircrafts"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_aircrafts&status=inactive">
                            <fmt:message key="local.inactiveAircrafts"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_aircrafts&status=fly">
                            <fmt:message key="local.flyingAircrafts"/>
                        </a>
                    </li>
                </c:when>
                <c:when test="${user.role eq 'OPERATOR'}">
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.flights"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=create_flight_page">
                            <fmt:message key="local.createFlight"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_flights&status=active">
                            <fmt:message key="local.activeFlights"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_flights&status=inactive">
                            <fmt:message key="local.inactiveFlights"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_flights&status=fly">
                            <fmt:message key="local.flyingFlights"/>
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.crews"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=active">
                            <fmt:message key="local.activeCrews"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=busy">
                            <fmt:message key="local.busyCrews"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=inactive">
                            <fmt:message key="local.inactiveCrews"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=fly">
                            <fmt:message key="local.flyingCrews"/>
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.airports"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_airports">
                            <fmt:message key="local.allAirports"/>
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.aircrafts"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_aircrafts&status=active">
                            <fmt:message key="local.activeAircrafts"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_aircrafts&status=busy">
                            <fmt:message key="local.busyAircrafts"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_aircrafts&status=inactive">
                            <fmt:message key="local.inactiveAircrafts"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_aircrafts&status=fly">
                            <fmt:message key="local.flyingAircrafts"/>
                        </a>
                    </li>
                </c:when>
                <c:when test="${user.role eq 'DISPATCHER'}">
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-2 mb-1 text-muted">
                        <span><fmt:message key="local.users"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_users&status=active&role=pilot">
                            <fmt:message key="local.pilots"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_users&status=active&role=navigator">
                            <fmt:message key="local.navigators"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_users&status=active&role=radioman">
                            <fmt:message key="local.radiomans"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_users&status=active&role=stewardess">
                            <fmt:message key="local.stewardesses"/>
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.crews"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=create_crew_page">
                            <fmt:message key="local.createCrew"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=active">
                            <fmt:message key="local.activeCrews"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=busy">
                            <fmt:message key="local.busyCrews"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=inactive">
                            <fmt:message key="local.inactiveCrews"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=fly">
                            <fmt:message key="local.flyingCrews"/>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-2 mb-1 text-muted">
                        <span><fmt:message key="local.flights"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_user_flights&status=active">
                            <fmt:message key="local.activeUsersFlight"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_user_flights&status=inactive">
                            <fmt:message key="local.inactiveUsersFlight"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_user_flights&status=fly">
                            <fmt:message key="local.flyUsersFlight"/>
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.crews"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_user_crews&status=active">
                            <fmt:message key="local.activeUsersCrew"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_user_crews&status=busy">
                            <fmt:message key="local.busyUsersCrew"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_user_crews&status=inactive">
                            <fmt:message key="local.inactiveUsersCrew"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_user_crews&status=fly">
                            <fmt:message key="local.flyUsersCrew"/>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
