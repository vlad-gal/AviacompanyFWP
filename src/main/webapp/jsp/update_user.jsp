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

    <title><fmt:message key="local.common.changeText"/></title>
</head>
<body class="login-page">
<jsp:include page="common/header.jsp"/>
<main class="content">
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="card-wrapper">
                <div class="card fat">
                    <div class="card-body">
                        <h5 class="card-title text-center"><fmt:message key="local.common.changeText"/></h5>
                        <c:if test="${updatingSuccessfulFlag eq true}">
                            <h6 class="alert-success text-center"><fmt:message key="local.common.updatingSuccessful"/></h6>
                        </c:if>
                        <c:if test="${errorValidationFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.common.errorValidation"/></h6>
                        </c:if>
                        <c:if test="${errorUpdateUserFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.common.errorUpdating"/></h6>
                        </c:if>
                        <form method="POST" name="updatingForm" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="admin_update_user">
                            <div class="form-group">
                                <label for="firstName"><fmt:message key="local.common.firstName"/>*</label>
                                <input id="firstName" type="text" class="form-control" name="firstName"
                                       placeholder="<fmt:message key="local.common.firstName"/>" pattern="[A-ZА-Я][a-zа-я]+"
                                       title="<fmt:message key="local.common.incorrectNameMessage"/>"
                                       value="${updatingUser.firstName}" required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <c:if test="${updatingUser.firstName eq null}">
                                    <div class="errorLoginPass">
                                        <p class="errorLoginPass text-center"><fmt:message key="local.common.incorrectNameMessage"/></p>
                                    </div>
                                </c:if>
                            </c:if>
                            <div class="form-group">
                                <label for="lastName"><fmt:message key="local.common.lastName"/>*</label>
                                <input id="lastName" type="text" class="form-control" name="lastName"
                                       placeholder="<fmt:message key="local.common.lastName"/>" pattern="[A-ZА-Я][a-zа-я]+"
                                       title="<fmt:message key="local.common.incorrectNameMessage"/>"
                                       value="${updatingUser.lastName}" required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <c:if test="${updatingUser.lastName eq null}">
                                    <div class="errorLoginPass">
                                        <p class="errorLoginPass text-center"><fmt:message key="local.common.incorrectNameMessage"/></p>
                                    </div>
                                </c:if>
                            </c:if>
                            <div class="form-group">
                                <label for="telephoneNumber"><fmt:message key="local.common.telephoneNumber"/>*</label>
                                <input id="telephoneNumber" type="tel" class="form-control" name="telephoneNumber"
                                       placeholder="<fmt:message key="local.common.telephoneNumber"/>" pattern="\d{12}"
                                       title="<fmt:message key="local.common.incorrectTelephoneMessage"/>"
                                       value="${updatingUser.telephoneNumber}" required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <c:if test="${updatingUser.telephoneNumber eq null}">
                                    <div class="errorLoginPass">
                                        <p class="errorLoginPass text-center"><fmt:message key="local.common.incorrectTelephoneMessage"/></p>
                                    </div>
                                </c:if>
                            </c:if>
                            <div class="form-group">
                                <label for="role"><fmt:message key="local.common.role"/>*</label>
                                <select id="role" name="role" class="custom-select">
                                    <c:forEach var="role" items="${sessionScope.roles}" begin="1" end="6">
                                        <c:choose>
                                            <c:when test="${updatingUser.role == role}">
                                                <option selected>${role.roleName}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option>
                                                        ${role.roleName}
                                                </option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="status"><fmt:message key="local.common.status"/>*</label>
                                <select id="status" name="status" class="custom-select">
                                    <c:forEach var="status" items="${sessionScope.statuses}">
                                        <c:choose>
                                            <c:when test="${updatingUser.status == status}">
                                                <option selected>${status.statusName}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option>
                                                        ${status.statusName}
                                                </option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group m-0">
                                <input type="submit" class="btn btn-primary btn-block" value="<fmt:message key="local.common.change"/>">
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

