<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">

    <title><fmt:message key="local.createAirport"/></title>
</head>
<body class="login-page">
<c:import url="common/header.jsp"/>
<main class="content">
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="card-wrapper">
                <div class="card fat">
                    <div class="card-body">
                        <h5 class="card-title text-center"><fmt:message key="local.createAirport"/></h5>
                        <c:if test="${errorCreateAirportFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.errorCreateAirport"/></h6>
                        </c:if>
                        <c:if test="${createAirportSuccessfulFlag eq true}">
                            <h6 class="alert-success text-center"><fmt:message key="local.createAirportSuccessful"/></h6>
                        </c:if>
                        <c:if test="${errorValidationFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.errorValidation"/></h6>
                        </c:if>
                        <form method="POST" name="createAirportForm"
                              action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="create_airport">
                            <div class="form-group">
                                <label for="airportName"><fmt:message key="local.airportName"/>*</label>
                                <input id="airportName" type="text" class="form-control" name="airportName"
                                       placeholder="<fmt:message key="local.airportName"/>" pattern="[A-Za-zА-Яа-я0-9\s]{2,15}"
                                       title="<fmt:message key="local.incorrectAirportNameMessage"/>"
                                       value="${airportName}" autofocus required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <div class="errorLoginPass">
                                    <p class="errorLoginPass text-center"><fmt:message key="local.incorrectAirportNameMessage"/></p>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label for="city"><fmt:message key="local.airportCity"/>*</label>
                                <input id="city" type="text" class="form-control" name="city"
                                       placeholder="<fmt:message key="local.airportCity"/>" pattern="[A-Za-zА-Яа-я\-]{2,20}"
                                       title="<fmt:message key="local.incorrectCityMessage"/>"
                                       value="${city}" required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <div class="errorLoginPass">
                                    <p class="errorLoginPass text-center"><fmt:message key="local.incorrectCityMessage"/></p>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label for="country"><fmt:message key="local.airportCountry"/>*</label>
                                <input id="country" type="text" class="form-control" name="country"
                                       placeholder="<fmt:message key="local.airportCountry"/>" pattern="[A-Za-zА-Яа-я\-]{2,20}"
                                       title="<fmt:message key="local.incorrectCountryMessage"/>"
                                       value="${country}" required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <div class="errorLoginPass">
                                    <p class="errorLoginPass text-center"><fmt:message key="local.incorrectCountryMessage"/></p>
                                </div>
                            </c:if>

                            <div class="form-group m-0">
                                <input type="submit" class="btn btn-primary btn-block"
                                       value="<fmt:message key="local.createAirport"/>">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<c:import url="common/footer.jsp"/>
</body>
</html>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/security.js"></script>