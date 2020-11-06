<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>


<div class="container">
    <div class="card">
        <c:choose>
            <c:when test="${not empty allUsersList}">
                <div class="card-header text-center">
                    <h5>All active users</h5>
                </div>
                <div class=" card-body ">
                    <div class="d-inline-flex">
                            <%--<form action="${pageContext.request.contextPath}/controller" method="post" name="addUser">--%>
                            <%--<input type="hidden" name="command" value="add_user">--%>
                        <div class="m-2">
                            <a href="controller?command=create_user_page" class="btn btn-info">ADD</a>
                        </div>
                            <%--</form>--%>
                    </div>
                    <table class="table table-hover" id="sort-user">
                        <thead class="thead-light">
                        <tr class="row-a">
                            <th scope="col">Имя</th>
                            <th scope="col">Фамилия</th>
                            <th scope="col">номер телефона</th>
                            <th scope="col">Почта</th>
                            <th scope="col">Роль</th>
                            <th scope="col">Статус</th>
                            <th scope="col">Действия</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${allUsersList}">
                            <tr class="row-a">
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.telephoneNumber}</td>
                                <td>${user.email}</td>
                                <td>${user.role}</td>
                                <td>${user.status}</td>
                                <td>
                                    <div class="d-inline-flex">
                                            <%--<form method="post" action="${pageContext.request.contextPath}/controller"--%>
                                            <%--name="deactivation">--%>
                                            <%--<input type="hidden" name="command" value="deactivate_user">--%>
                                            <%--<input type="submit" class="btn btn-info "--%>
                                            <%--value="${user.status}">--%>
                                            <%--</form>--%>
                                        <div class="ml-2">
                                            <a href="controller?command=admin_update_user_page&login=${user.login}"
                                               class="btn btn-info">Edit</a>
                                        </div>
                                    </div>

                                </td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:when test="${not empty allFlightsList}">
                <div class="card-header text-center">
                    <h5>All active users</h5>
                </div>
                <div class=" card-body ">
                    <div class="d-inline-flex">
                            <%--<form action="${pageContext.request.contextPath}/controller" method="post" name="addUser">--%>
                            <%--<input type="hidden" name="command" value="add_user">--%>
                        <div class="m-2">
                            <a href="controller?command=create_flight_page" class="btn btn-info">ADD</a>
                        </div>
                            <%--</form>--%>
                    </div>
                    <table class="table table-hover" id="sort-flight">
                        <thead class="thead-light">
                        <tr class="row-a">
                            <th scope="col">Имя</th>
                            <th scope="col">Фамилия</th>
                            <th scope="col">номер телефона</th>
                            <th scope="col">Почта</th>
                            <th scope="col">Роль</th>
                            <th scope="col">Статус</th>
                            <th scope="col">Действия</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="flight" items="${allFlightsList}">
                            <tr class="row-a">
                                <td>${flight.departureAirport.airportName}, ${flight.departureAirport.city}, ${flight.departureAirport.country}</td>
                                <td>${flight.destinationAirport.airportName}, ${flight.destinationAirport.city}, ${flight.destinationAirport.country}</td>
                                <td><fmt:formatDate value="${flight.departTime}"
                                                    pattern="d-MMMM-yyyy h:m"/></td>
                                <td><fmt:formatDate value="${flight.arriveTime}"
                                                    pattern="d-MMMM-yyyy h:m"/></td>

                                <td>${flight.crew.crewName}</td>
                                <td>${flight.status}</td>
                                <td>
                                    <div class="d-inline-flex">
                                            <%--<form method="post" action="${pageContext.request.contextPath}/controller"--%>
                                            <%--name="deactivation">--%>
                                            <%--<input type="hidden" name="command" value="deactivate_user">--%>
                                            <%--<input type="submit" class="btn btn-info "--%>
                                            <%--value="${user.status}">--%>
                                            <%--</form>--%>
                                        <div class="ml-2">
                                            <a href="controller?command=update_flight_page&flightId=${flight.id}"
                                               class="btn btn-info">Edit</a>
                                        </div>
                                    </div>

                                </td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
        </c:choose>

    </div>
</div>
