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

    <title><fmt:message key="local.createAircraft"/></title>
</head>
<body class="login-page">
<c:import url="common/header.jsp"/>
<main class="content">
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="card-wrapper">
                <div class="card fat">
                    <div class="card-body">
                        <h5 class="card-title text-center"><fmt:message key="local.createAircraft"/></h5>
                        <c:if test="${errorCreateAircraftFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.errorCreateAircraft"/></h6>
                        </c:if>
                        <c:if test="${createAircraftSuccessfulFlag eq true}">
                            <h6 class="alert-success text-center"><fmt:message key="local.createAircraftSuccessful"/></h6>
                        </c:if>
                        <c:if test="${errorValidationFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.errorValidation"/></h6>
                        </c:if>
                        <form method="POST" name="createAircraftForm" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="create_aircraft">
                            <div class="form-group">
                                <label for="tailNumber"><fmt:message key="local.tailNumber"/>*</label>
                                <input id="tailNumber" type="text" class="form-control" name="tailNumber"
                                       placeholder="<fmt:message key="local.tailNumber"/>" pattern="[A-Z0-9]{2,10}"
                                       title="<fmt:message key="local.incorrectTailNumberMessage"/>"
                                       value="${tailNumber}" autofocus required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <div class="errorLoginPass">
                                    <p class="errorLoginPass text-center"><fmt:message key="local.incorrectTailNumberMessage"/></p>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label for="aircraftName"><fmt:message key="local.aircraftName"/>*</label>
                                <input id="aircraftName" type="text" class="form-control" name="aircraftName"
                                       placeholder="<fmt:message key="local.aircraftName"/>" pattern="[A-Za-z0-9/\-\s]{2,40}"
                                       title="<fmt:message key="local.incorrectAircraftNameMessage"/>"
                                       value="${aircraftName}" required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <div class="errorLoginPass">
                                    <p class="errorLoginPass text-center"><fmt:message key="local.incorrectAircraftNameMessage"/></p>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label for="aircraftType"><fmt:message key="local.aircraftType"/>*</label>
                                <select id="aircraftType" name="aircraftType" class="custom-select" required>
                                    <c:if test="${errorValidationFlag eq true || errorCreateAircraftFlag eq true}">
                                        <option value="${aircraftType}" selected>${aircraftType.typeName}</option>
                                    </c:if>
                                    <c:forEach var="aircraftType" items="${sessionScope.aircraftTypes}">
                                        <option value="${aircraftType}">
                                                ${aircraftType.typeName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="status"><fmt:message key="local.status"/>*</label>
                                <select id="status" name="status" class="custom-select" required>
                                    <c:if test="${errorValidationFlag eq true || errorCreateAircraftFlag eq true}">
                                        <option value="${status}" selected>${status.statusName}</option>
                                    </c:if>
                                    <c:forEach var="status" items="${sessionScope.statuses}">
                                        <option value="${status}">
                                                ${status.statusName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group m-0">
                                <input type="submit" class="btn btn-primary btn-block"
                                       value="<fmt:message key="local.createAircraft"/>">
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