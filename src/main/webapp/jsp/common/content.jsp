<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>


<div class="container">
    <c:choose>
        <c:when test="${not empty userList}">
            <div class="text-center">
                <h5 class="card-title">All users</h5>
            </div>
            <table class="table table-hover" id="sort-user">
                <thead class="thead-light">
                <tr class="row-a">
                    <th scope="col">Имя</th>
                    <th scope="col">Фамилия</th>
                    <th scope="col">номер телефона</th>
                    <th scope="col">Почта</th>
                    <th scope="col">Логин</th>
                    <th scope="col">Роль</th>
                    <th scope="col">Статус</th>
                    <c:if test="${user.role eq 'ADMIN'}">
                    <th scope="col">Действия</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="user" items="${userList}">
                    <tr class="row-a">
                        <td>${airport.airportName}</td>
                        <td>${airport.country}</td>
                        <td>${airport.city}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:when test="">

        </c:when>
    </c:choose>


</div>
