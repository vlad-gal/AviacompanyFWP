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

    <title><fmt:message key="local.createCrew"/></title>
</head>
<body class="login-page">
<c:import url="common/header.jsp"/>
<main class="content">
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="card-wrapper">
                <div class="card fat">
                    <div class="card-body">
                        <h5 class="card-title text-center"><fmt:message key="local.createCrew"/></h5>
                        <c:if test="${errorCreateCrewFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.errorCreateCrew"/></h6>
                        </c:if>
                        <c:if test="${createCrewSuccessfulFlag eq true}">
                            <h6 class="alert-success text-center"><fmt:message key="local.createCrewSuccessful"/></h6>
                        </c:if>
                        <c:if test="${errorValidationFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.errorValidation"/></h6>
                        </c:if>
                        <form method="POST" name="createCrewForm" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="create_crew">
                            <div class="form-group">
                                <label for="crewName"><fmt:message key="local.crewName"/>*</label>
                                <input id="crewName" type="text" class="form-control" name="crewName"
                                       placeholder="<fmt:message key="local.crewName"/>"
                                       pattern="[a-zA-Z0-9]{3,15}"
                                       title="<fmt:message key="local.incorrectCrewNameMessage"/>"
                                       value="${crewDto.crewName}" autofocus required>
                            </div>
                            <div class="form-group">
                                <label for="numberOfPilots"><fmt:message key="local.numberOfPilots"/>*</label>
                                <input id="numberOfPilots" type="text" class="form-control" name="numberOfPilots"
                                       placeholder="<fmt:message key="local.numberOfPilots"/>"
                                       pattern="[1-2]"
                                       title="<fmt:message key="local.incorrectNumberOfStaffMessage"/>"
                                       value="${crewDto.numberOfPilots}" required>
                            </div>
                            <div class="form-group">
                                <label for="numberOfNavigators"><fmt:message key="local.numberOfNavigators"/>*</label>
                                <input id="numberOfNavigators" type="text" class="form-control"
                                       name="numberOfNavigators"
                                       placeholder="<fmt:message key="local.numberOfNavigators"/>"
                                       pattern="[1-2]"
                                       title="<fmt:message key="local.incorrectNumberOfStaffMessage"/>"
                                       value="${crewDto.numberOfNavigators}" required>
                            </div>
                            <div class="form-group">
                                <label for="numberOfRadioman"><fmt:message key="local.numberOfRadioman"/>*</label>
                                <input id="numberOfRadioman" type="text" class="form-control" name="numberOfRadioman"
                                       placeholder="<fmt:message key="local.numberOfRadioman"/>"
                                       pattern="[1-2]"
                                       title="<fmt:message key="local.incorrectNumberOfStaffMessage"/>"
                                       value="${crewDto.numberOfRadioman}" required>
                            </div>
                            <div class="form-group">
                                <label for="numberOfStewardesses"><fmt:message key="local.numberOfStewardesses"/>*</label>
                                <input id="numberOfStewardesses" type="text" class="form-control"
                                       name="numberOfStewardesses"
                                       placeholder="<fmt:message key="local.numberOfStewardesses"/>"
                                       pattern="[\d]"
                                       title="<fmt:message key="local.incorrectNumberOfStewardessesMessage"/>"
                                       value="${crewDto.numberOfStewardesses}" required>
                            </div>
                            <div class="form-group m-0">
                                <input type="submit" class="btn btn-primary btn-block"
                                       value="<fmt:message key="local.createCrew"/>">
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