<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>

<%--<div class="col-3 h-100 bg-dark">--%>
<%--<c:choose>--%>
    <%--<c:when test="${user.role eq 'ADMIN'}">--%>
        <%--<div class="alert-success m-3 container ">--%>
            <%--<a class="m-2" href="">eerwer</a>--%>
        <%--</div>--%>
        <%--<a href="">All users</a>--%>
        <%--<a href="">All active users</a>--%>
        <%--<a href="">All inactive users</a>--%>
        <%--__________________________________--%>
        <%--<a href="">All flight</a>--%>
        <%--<a href="">All future flight</a>--%>
        <%--_____________________--%>
        <%--<a href="">All crews</a>--%>
        <%--____________________--%>
        <%--<a href="">All aircrafts</a>--%>
        <%--___________________________--%>
        <%--<a href="">All airports</a>--%>
        <%--__________________________--%>


    <%--</c:when>--%>
    <%--<c:when test="${user.role eq 'OPERATOR'}">--%>
        <%--__________________________________--%>
        <%--<a href="">All flight</a>--%>
        <%--<a href="">All future flight</a>--%>
        <%--_____________________--%>
        <%--<a href="">All crews</a>--%>
        <%--____________________--%>
        <%--<a href="">All aircrafts</a>--%>
    <%--</c:when>--%>
    <%--<c:when test="${user.role eq 'DISPATCHER'}">--%>
        <%--_____________________--%>
        <%--<a href="">All crews</a>--%>
    <%--</c:when>--%>
    <%--<c:otherwise>--%>
        <%--<a href="">Send admin notification</a>--%>
    <%--</c:otherwise>--%>
<%--</c:choose>--%>
<%--</div>--%>

<nav class="d-none d-md-block bg-light sidebar">
    <div class="sidebar-sticky">
        <ul class="nav flex-column">
            <c:choose>
                <c:when test="${user.role eq 'ADMIN'}">
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-2 mb-1 text-muted">
                        <span>Users</span>
                    </h6>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=all_users">
                            All users
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            Inactive users
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            Users without roles
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
                <c:when test="${user.role eq 'DEFAULT'}">
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            Choose role
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

<%--<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">--%>
<%--<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">--%>
<%--<h1 class="h2">Dashboard</h1>--%>
<%--<div class="btn-toolbar mb-2 mb-md-0">--%>
<%--<div class="btn-group mr-2">--%>
<%--<button class="btn btn-sm btn-outline-secondary">Share</button>--%>
<%--<button class="btn btn-sm btn-outline-secondary">Export</button>--%>
<%--</div>--%>
<%--<button class="btn btn-sm btn-outline-secondary dropdown-toggle">--%>
<%--<span data-feather="calendar"></span>--%>
<%--This week--%>
<%--</button>--%>
<%--</div>--%>
<%--</div>--%>

<%--<canvas class="my-4" id="myChart" width="900" height="380"></canvas>--%>

<%--<h2>Section title</h2>--%>
<%--<div class="table-responsive">--%>
<%--<table class="table table-striped table-sm">--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<th>#</th>--%>
<%--<th>Header</th>--%>
<%--<th>Header</th>--%>
<%--<th>Header</th>--%>
<%--<th>Header</th>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--<tbody>--%>
<%--<tr>--%>
<%--<td>1,001</td>--%>
<%--<td>Lorem</td>--%>
<%--<td>ipsum</td>--%>
<%--<td>dolor</td>--%>
<%--<td>sit</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>1,002</td>--%>
<%--<td>amet</td>--%>
<%--<td>consectetur</td>--%>
<%--<td>adipiscing</td>--%>
<%--<td>elit</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>1,003</td>--%>
<%--<td>Integer</td>--%>
<%--<td>nec</td>--%>
<%--<td>odio</td>--%>
<%--<td>Praesent</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>1,003</td>--%>
<%--<td>libero</td>--%>
<%--<td>Sed</td>--%>
<%--<td>cursus</td>--%>
<%--<td>ante</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>1,004</td>--%>
<%--<td>dapibus</td>--%>
<%--<td>diam</td>--%>
<%--<td>Sed</td>--%>
<%--<td>nisi</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>1,005</td>--%>
<%--<td>Nulla</td>--%>
<%--<td>quis</td>--%>
<%--<td>sem</td>--%>
<%--<td>at</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>1,006</td>--%>
<%--<td>nibh</td>--%>
<%--<td>elementum</td>--%>
<%--<td>imperdiet</td>--%>
<%--<td>Duis</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>1,007</td>--%>
<%--<td>sagittis</td>--%>
<%--<td>ipsum</td>--%>
<%--<td>Praesent</td>--%>
<%--<td>mauris</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>1,008</td>--%>
<%--<td>Fusce</td>--%>
<%--<td>nec</td>--%>
<%--<td>tellus</td>--%>
<%--<td>sed</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>1,009</td>--%>
<%--<td>augue</td>--%>
<%--<td>semper</td>--%>
<%--<td>porta</td>--%>
<%--<td>Mauris</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>1,010</td>--%>
<%--<td>massa</td>--%>
<%--<td>Vestibulum</td>--%>
<%--<td>lacinia</td>--%>
<%--<td>arcu</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>1,011</td>--%>
<%--<td>eget</td>--%>
<%--<td>nulla</td>--%>
<%--<td>Class</td>--%>
<%--<td>aptent</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>1,012</td>--%>
<%--<td>taciti</td>--%>
<%--<td>sociosqu</td>--%>
<%--<td>ad</td>--%>
<%--<td>litora</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>1,013</td>--%>
<%--<td>torquent</td>--%>
<%--<td>per</td>--%>
<%--<td>conubia</td>--%>
<%--<td>nostra</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>1,014</td>--%>
<%--<td>per</td>--%>
<%--<td>inceptos</td>--%>
<%--<td>himenaeos</td>--%>
<%--<td>Curabitur</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>1,015</td>--%>
<%--<td>sodales</td>--%>
<%--<td>ligula</td>--%>
<%--<td>in</td>--%>
<%--<td>libero</td>--%>
<%--</tr>--%>
<%--</tbody>--%>
<%--</table>--%>
<%--</div>--%>
<%--</main>--%>
