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

    <title><fmt:message key="local.common.addUserIntoCrew"/></title>
</head>
<body class="login-page">
<jsp:include page="common/header.jsp"/>
<main class="content">
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="card-wrapper">
                <div class="card fat">
                    <div class="card-body">
                        <h5 class="card-title text-center"><fmt:message key="local.common.createFlight"/></h5>
                        <c:if test="${errorAddUserIntoCrewFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.common.errorAddUserIntoCrew"/></h6>
                        </c:if>
                        <c:if test="${addUserIntoCrewSuccessfulFlag eq true}">
                            <h6 class="alert-success text-center"><fmt:message key="local.common.addUserIntoCrewSuccessful"/></h6>
                        </c:if>
                        <c:if test="${errorValidationFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.common.errorValidation"/></h6>
                        </c:if>
                        <c:if test="${availablePlacesInCrewFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.common.availablePlacesInCrew"/> ${addingUser.role.roleName} = ${availablePlacesInCrew}</h6>
                        </c:if>
                        <form method="POST" name="addUserIntoCrewForm" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="add_user_into_crew">
                            <div class="form-group">
                                <label for="addingUser"><fmt:message
                                        key="local.crew.addingUser"/></label>
                                <input id="addingUser" type="text" class="form-control"
                                       name="addingUser"
                                       value="${addingUser.firstName} ${addingUser.lastName} - ${addingUser.role.roleName}" disabled>
                            </div>
                            <div class="form-group">
                                <label for="crew"><fmt:message key="local.crew.crews"/>*</label>
                                <select id="crew" name="crew" class="custom-select" required>
                                    <c:if test="${errorAddUserIntoCrewFlag eq true ||errorValidationFlag eq true}">
                                        <option value="${crew.id}" selected>${crew.crewName}</option>>
                                    </c:if>
                                    <c:forEach var="crew" items="${crewList}">
                                        <option value="${crew.id}">
                                                ${crew.crewName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group m-0">
                                <input type="submit" class="btn btn-primary btn-block"
                                       value="<fmt:message key="local.common.addUser"/>">
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
