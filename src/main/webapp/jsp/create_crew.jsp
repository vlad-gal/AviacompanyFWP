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

    <title><fmt:message key="local.common.createCrew"/></title>
</head>
<body class="login-page">
<jsp:include page="common/header.jsp"/>
<main class="content">
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="card-wrapper">
                <div class="card fat">
                    <div class="card-body">
                        <h5 class="card-title text-center"><fmt:message key="local.common.createCrew"/></h5>
                        <c:if test="${errorCreateCrewFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message
                                    key="local.common.errorCreateCrew"/></h6>
                        </c:if>
                        <c:if test="${createCrewSuccessfulFlag eq true}">
                            <h6 class="alert-success text-center"><fmt:message
                                    key="local.common.createCrewSuccessful"/></h6>
                        </c:if>
                        <c:if test="${errorValidationFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.common.errorValidation"/></h6>
                        </c:if>
                        <form method="POST" name="createCrewForm" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="create_crew">
                            <div class="form-group">
                                <label for="crewName"><fmt:message key="local.flight.departureAirport"/>*</label>
                                <input id="crewName" type="text" class="form-control" name="crewName"
                                       placeholder="<fmt:message key="local.common.crewName"/>"
                                       pattern="[a-zA-Z0-9]{3,10}"
                                       title="<fmt:message key="local.common.incorrectCrewNameMessage"/>"
                                       value="${crewDto.crewName}" autofocus required>
                            </div>
                            <div class="form-group">
                                <label for="numberOfPilots"><fmt:message key="local.crew.numberOfPilots"/>*</label>
                                <input id="numberOfPilots" type="text" class="form-control" name="numberOfPilots"
                                       placeholder="<fmt:message key="local.crew.numberOfPilots"/>"
                                       pattern="[1-2]"
                                       title="<fmt:message key="local.common.incorrectNumberOfStaffMessage"/>"
                                       value="${crewDto.numberOfPilots}" required>
                            </div>
                            <div class="form-group">
                                <label for="numberOfNavigators"><fmt:message
                                        key="local.crew.numberOfNavigators"/>*</label>
                                <input id="numberOfNavigators" type="text" class="form-control"
                                       name="numberOfNavigators"
                                       placeholder="<fmt:message key="local.crew.numberOfNavigators"/>"
                                       pattern="[1-2]"
                                       title="<fmt:message key="local.common.incorrectNumberOfStaffMessage"/>"
                                       value="${crewDto.numberOfNavigators}" required>
                            </div>
                            <div class="form-group">
                                <label for="numberOfRadioman"><fmt:message key="local.crew.numberOfRadioman"/>*</label>
                                <input id="numberOfRadioman" type="text" class="form-control" name="numberOfRadioman"
                                       placeholder="<fmt:message key="local.crew.numberOfRadioman"/>"
                                       pattern="[1-2]"
                                       title="<fmt:message key="local.common.incorrectNumberOfStaffMessage"/>"
                                       value="${crewDto.numberOfRadioman}" required>
                            </div>
                            <div class="form-group">
                                <label for="numberOfStewardesses"><fmt:message
                                        key="local.crew.numberOfStewardesses"/>*</label>
                                <input id="numberOfStewardesses" type="text" class="form-control"
                                       name="numberOfStewardesses"
                                       placeholder="<fmt:message key="local.crew.numberOfStewardesses"/>"
                                       pattern="[\d]"
                                       title="<fmt:message key="local.common.incorrecNumberOfStewardessesMessage"/>"
                                       value="${crewDto.numberOfStewardesses}" required>
                            </div>
                            <div class="form-group m-0">
                                <input type="submit" class="btn btn-primary btn-block"
                                       value="<fmt:message key="local.common.createCrew"/>">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
