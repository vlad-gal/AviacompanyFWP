<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="local" var="local"/>

<div class="col-3 h-100 bg-dark">
    <c:choose>
        <c:when test="${user.role eq 'ADMIN'}">
            <a href="">All users</a>
            <a href="">All active users</a>
            <a href="">All inactive users</a>
            __________________________________
            <a href="">All flight</a>
            <a href="">All future flight</a>
            _____________________
            <a href="">All crews</a>
            ____________________
            <a href="">All aircrafts</a>
            ___________________________
            <a href="">All airports</a>
            __________________________


        </c:when>
        <c:when test="${user.role eq 'OPERATOR'}">
            __________________________________
            <a href="">All flight</a>
            <a href="">All future flight</a>
            _____________________
            <a href="">All crews</a>
            ____________________
            <a href="">All aircrafts</a>
        </c:when>
        <c:when test="${user.role eq 'DISPATCHER'}">
            _____________________
            <a href="">All crews</a>
        </c:when>
        <c:otherwise>
            <a href="">Send admin notification</a>
        </c:otherwise>
    </c:choose>
</div>