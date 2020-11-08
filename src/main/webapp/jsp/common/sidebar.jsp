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
                        <a class="nav-link" href="controller?command=all_users">
                            <fmt:message key="local.sidebar.allUsers"/>
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
                        <a class="nav-link" href="controller?command=create_flight_page">
                            <fmt:message key="local.common.createFlight"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_flights&status=active">
                            Active current flights
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_flights&status=inactive">
                            Inactive flights
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span>Crews</span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            All crews
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            Active crews
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span>Airports</span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            All airports
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span>Aircrafts</span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            All aircrafts
                        </a>
                    </li>
                </c:when>
                <c:when test="${user.role eq 'OPERATOR'}">
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span>Flights</span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            All flights
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            Current flights
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span>Crews</span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            All crews
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            Active crews
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span>Airports</span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            All airports
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span>Aircrafts</span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            All aircrafts
                        </a>
                    </li>
                </c:when>
                <c:when test="${user.role eq 'DISPATCHER'}">
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-2 mb-1 text-muted">
                        <span>Users</span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            All users
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span>Crews</span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            All crews
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            Active crews
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span>Crews</span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            All crews
                        </a>
                    </li>
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span>Flights</span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            All flights
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            Current flights
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
