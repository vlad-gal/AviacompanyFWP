<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>


<div class="container">
    <div class="card">

        <c:choose>
        <c:when test="${not empty activeUserList}">
        <div class="card-header text-center">
                <h5>All active users</h5>
        </div>
        <div class=" card-body ">
            <div class="d-inline-flex">
                <%--<form action=""><input type="search" name="">--%>
            <%--</form>--%>
            <form action=""><input type="button" name="ADD">
            </form>
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
                    <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                        <th scope="col">Действия</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${activeUserList}">
                    <tr class="row-a">
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.telephoneNumber}</td>
                        <td>${user.email}</td>
                        <td>${user.role}</td>
                        <td>${user.status}</td>
                        <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/controller"
                                  name="deactivation">
                                <input type="hidden" name="command" value="deactivate_user">
                                <input type="submit" class="btn btn-info "
                                       value="${user.status}">
                            </form>
                        </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </c:when>
            <c:when test="${not empty inactiveUserList}">
            <div class="card-header text-center">
                <h5>All inactive users</h5>

            </div>
            <div class=" card-body ">
                <table class="table table-hover" id="sort-user">
                    <thead class="thead-light">
                    <tr class="row-a">
                        <th scope="col">Имя</th>
                        <th scope="col">Фамилия</th>
                        <th scope="col">номер телефона</th>
                        <th scope="col">Почта</th>
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
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.telephoneNumber}</td>
                            <td>${user.email}</td>
                            <td>${user.role}</td>
                            <td>${user.status}</td>
                            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                                <td>
                                    <form method="post" action="${pageContext.request.contextPath}/controller"
                                          name="deactivation">
                                        <input type="hidden" name="command" value="deactivate_user">
                                        <input type="submit" class="btn btn-info "
                                               value="${user.status}">
                                    </form>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            </c:choose>
        </div>
    </div>
</div>
