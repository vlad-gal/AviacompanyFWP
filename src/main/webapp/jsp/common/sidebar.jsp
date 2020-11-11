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
                        <span><fmt:message key="local.sidebar.users"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=create_user_page">
                            <fmt:message key="local.sidebar.createUser"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_users&status=active">
                            <fmt:message key="local.sidebar.activeUsers"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_users&status=busy">
                            <fmt:message key="local.sidebar.busyUsers"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_users&status=fly">
                            <fmt:message key="local.sidebar.usersInFlight"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_users&status=inactive">
                            <fmt:message key="local.sidebar.inactiveUsers"/>
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.common.flights"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_flights&status=active">
                            <fmt:message key="local.sidebar.activeFlights"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_flights&status=inactive">
                            <fmt:message key="local.sidebar.inactiveFlights"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_flights&status=fly">
                            <fmt:message key="local.sidebar.flyingFlights"/>
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.crew.crews"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=active">
                            <fmt:message key="local.sidebar.activeCrews"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=inactive">
                            <fmt:message key="local.sidebar.inactiveCrews"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=fly">
                            <fmt:message key="local.sidebar.flyingCrews"/>
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.common.airports"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=create_airport_page">
                            <fmt:message key="local.sidebar.createAirport"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_airports">
                            <fmt:message key="local.sidebar.allAirports"/>
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.common.aircrafts"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=create_aircraft_page">
                            <fmt:message key="local.sidebar.createAircraft"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_aircrafts&status=active">
                            <fmt:message key="local.sidebar.activeAircrafts"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_aircrafts&status=inactive">
                            <fmt:message key="local.sidebar.inactiveAircrafts"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_aircrafts&status=fly">
                            <fmt:message key="local.sidebar.flyingAircrafts"/>
                        </a>
                    </li>
                </c:when>
                <c:when test="${user.role eq 'OPERATOR'}">
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.common.flights"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=create_flight_page">
                            <fmt:message key="local.common.createFlight"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_flights&status=active">
                            <fmt:message key="local.sidebar.activeFlights"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_flights&status=inactive">
                            <fmt:message key="local.sidebar.inactiveFlights"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_flights&status=fly">
                            <fmt:message key="local.sidebar.flyingFlights"/>
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.crew.crews"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=active">
                            <fmt:message key="local.sidebar.activeCrews"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=inactive">
                            <fmt:message key="local.sidebar.inactiveCrews"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=fly">
                            <fmt:message key="local.sidebar.flyingCrews"/>
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.common.airports"/></span>
                    </h6>>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_airports">
                            <fmt:message key="local.sidebar.allAirports"/>
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.common.aircrafts"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_aircrafts&status=active">
                            <fmt:message key="local.sidebar.activeAircrafts"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_aircrafts&status=inactive">
                            <fmt:message key="local.sidebar.inactiveAircrafts"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_aircrafts&status=fly">
                            <fmt:message key="local.sidebar.flyingAircrafts"/>
                        </a>
                    </li>
                </c:when>
                <c:when test="${user.role eq 'DISPATCHER'}">
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-2 mb-1 text-muted">
                        <span><fmt:message key="local.sidebar.users"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_users&status=active&role=pilot">
                            <fmt:message key="local.sidebar.pilots"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_users&status=active&role=navigator">
                            <fmt:message key="local.sidebar.navigators"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_users&status=active&role=radioman">
                            <fmt:message key="local.sidebar.radiomans"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_users&status=active&role=stewardess">
                            <fmt:message key="local.sidebar.stewardesses"/>
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.crew.crews"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=create_crew_page">
                            <fmt:message key="local.sidebar.createCrew"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=active">
                            <fmt:message key="local.sidebar.activeCrews"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=inactive">
                            <fmt:message key="local.sidebar.inactiveCrews"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_crews&status=fly">
                            <fmt:message key="local.sidebar.flyingCrews"/>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-2 mb-1 text-muted">
                        <span><fmt:message key="local.common.flights"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_user_flights&status=active">
                            <fmt:message key="local.sidebar.activeUsersFlight"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_user_flights&status=inactive">
                            <fmt:message key="local.sidebar.inactiveUsersFlight"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_user_flights&status=fly">
                            <fmt:message key="local.sidebar.flyUsersFlight"/>
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span><fmt:message key="local.crew.crews"/></span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_user_crews&status=active">
                            <fmt:message key="local.sidebar.activeUsersCrew"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_user_crews&status=inactive">
                            <fmt:message key="local.sidebar.inactiveUsersCrew"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_user_crews&status=fly">
                            <fmt:message key="local.sidebar.flyUsersCrew"/>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
