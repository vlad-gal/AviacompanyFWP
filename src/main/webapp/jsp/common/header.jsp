<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>
<header class="bg-light">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <c:choose>
            <c:when test="${empty sessionScope.user}">
            <a class="navbar-brand" href="controller?command=welcome_page"><fmt:message key="local.companyName"/></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="controller?command=aircrafts_page"><fmt:message key="local.aircrafts"/></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="controller?command=flights_page"><fmt:message key="local.flights"/></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="controller?command=airports_page"><fmt:message key="local.airports"/></a>
                    </li>
                </ul>
                </c:when>
                <c:otherwise>
                <a class="navbar-brand" href="controller?command=user_account_page"><fmt:message key="local.userAccount"/></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    </c:otherwise>
                    </c:choose>
                    <ul class="navbar-nav ml-auto">
                        <div>
                            <li class="nav-item dropdown">
                                <a class="nav-link active dropdown-toggle" id="navbarDropdown" role="button"
                                   data-toggle="dropdown"
                                   aria-haspopup="true" aria-expanded="false">
                                    <fmt:message key="local.language"/>
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item"
                                       href="controller?command=localization&lang=ru"><fmt:message key="local.russian"/>
                                    </a>
                                    <a class="dropdown-item"
                                       href="controller?command=localization&lang=en"><fmt:message key="local.english"/></a>
                                </div>
                            </li>
                        </div>
                        <c:choose>
                            <c:when test="${empty sessionScope.user}">
                                <li class="nav-item active">
                                    <form name="authorization" method="get" action="/controller">
                                        <input type="hidden" name="command" value="authorization_page"/>
                                        <button type="submit" class="btn btn-info"><fmt:message key="local.authorization"/></button>
                                    </form>
                                </li>
                                <li class="nav-item active">
                                    <form name="registration" method="get" action="/controller">
                                        <input type="hidden" name="command" value="registration_page"/>
                                        <button type="submit" class="btn btn-info"><fmt:message key="local.registration"/></button>
                                    </form>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <div>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link active dropdown-toggle" role="button"
                                           data-toggle="dropdown"
                                           aria-haspopup="true" aria-expanded="false">
                                                ${user.firstName} ${user.lastName}
                                        </a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item"
                                               href="controller?command=user_account_page"><fmt:message key="local.userAccount"/></a>
                                            <a class="dropdown-item"
                                               href="controller?command=settings_page"><fmt:message key="local.settings"/></a>
                                            <a class="dropdown-item"
                                               href="controller?command=change_password_page"><fmt:message key="local.changePassword"/></a>
                                        </div>
                                    </li>
                                </div>
                                <li class="nav-item active">
                                    <div class="nav-link"><fmt:message key="local.role"/>: ${user.role.roleName}</div>
                                </li>
                                <li class="nav-item active">
                                    <div class="nav-link"><fmt:message key="local.status"/>: ${user.status.statusName}</div>
                                </li>
                                <li class="nav-item active">
                                    <form name="log_out" method="post" action="/controller">
                                        <input type="hidden" name="command" value="log_out"/>
                                        <button type="submit" class="btn btn-info"><fmt:message key="local.logout"/></button>
                                    </form>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
        </nav>
    </div>
</header>
