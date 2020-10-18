<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<c:set var="previousCommand" value="${pageContext.request.getParameter('command')}"/>--%>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.common.title" var="brandName"/>
<fmt:message bundle="${local}" key="local.common.aircrafts" var="aircrafts"/>
<fmt:message bundle="${local}" key="local.common.airports" var="airports"/>
<fmt:message bundle="${local}" key="local.common.flights" var="flights"/>
<fmt:message bundle="${local}" key="local.lang.english" var="english"/>
<fmt:message bundle="${local}" key="local.lang.russian" var="russian"/>
<fmt:message bundle="${local}" key="local.lang.language" var="dropLang"/>
<fmt:message bundle="${local}" key="local.common.authorization" var="auth"/>
<fmt:message bundle="${local}" key="local.common.registration" var="reg"/>
<fmt:message bundle="${local}" key="local.common.logout" var="lout"/>
<%--<c:set var="user" value="user.id" scope="session"/>--%>
<header class="bg-light">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="controller?command=welcome_page">${brandName}</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="controller?command=aircrafts_page">${aircrafts}</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="controller?command=flights_page">${flights}</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="controller?command=airports_page">${airports}</a>
                    </li>

                </ul>
                <ul class="navbar-nav ml-auto">
                    <div>
                        <li class="nav-item dropdown">
                            <a class="nav-link active dropdown-toggle" id="navbarDropdown" role="button"
                               data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false">
                                ${dropLang}
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item"
                                   href="controller?command=localization&lang=ru-RU">${russian}</a>
                                <a class="dropdown-item"
                                   href="controller?command=localization&lang=en-US">${english}</a>
                            </div>
                        </li>
                    </div>
                    <c:choose>
                        <c:when test="${empty sessionScope.userId}">
                            <li class="nav-item active">
                                <form name="authorization" method="get" action="/controller">
                                    <input type="hidden" name="command" value="authorization_page"/>
                                    <button type="submit" class="btn btn-info">${auth}</button>
                                </form>
                            </li>
                            <li class="nav-item active">
                                <form name="registration" method="get" action="/controller">
                                    <input type="hidden" name="command" value="registration_page"/>
                                    <button type="submit" class="btn btn-info">${reg}</button>
                                </form>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item active">
                                <a href="controller?command=user_page&id=${sessionScope.user.id}">user name</a>
                            </li>
                            <li class="nav-item active">
                                <form name="log_out" method="post" action="/controller">
                                    <input type="hidden" name="command" value="log_out"/>
                                    <button type="submit" class="btn btn-info">${lout}</button>
                                </form>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </nav>
    </div>
</header>
