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

    <title><fmt:message key="local.settings"/></title>
</head>
<body class="login-page">
<c:import url="common/header.jsp"/>
<main class="content">
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="card-wrapper">
                <div class="card fat">
                    <div class="card-body">
                        <h5 class="card-title text-center"><fmt:message key="local.changePersonalData"/></h5>
                        <c:if test="${errorValidationFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.errorValidation"/></h6>
                        </c:if>
                        <c:if test="${updatingSuccessfulFlag eq true}">
                            <h6 class="alert-success text-center"><fmt:message key="local.updatingSuccessful"/></h6>
                        </c:if>
                        <c:if test="${errorUpdateUserFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.errorUpdating"/></h6>
                        </c:if>
                        <form method="POST" name="settingForm" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="update_user">
                            <div class="form-group">
                                <label for="firstName"><fmt:message key="local.firstName"/>*</label>
                                <input id="firstName" type="text" class="form-control" name="firstName"
                                       placeholder="<fmt:message key="local.firstName"/>" pattern="[A-ZА-Я][a-zа-я]{1,14}"
                                       title="<fmt:message key="local.incorrectNameMessage"/>"
                                       value="${user.firstName}" required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <c:if test="${user.firstName eq null}">
                                    <div class="errorLoginPass">
                                        <p class="errorLoginPass text-center"><fmt:message key="local.incorrectNameMessage"/></p>
                                    </div>
                                </c:if>
                            </c:if>
                            <div class="form-group">
                                <label for="lastName"><fmt:message key="local.lastName"/>*</label>
                                <input id="lastName" type="text" class="form-control" name="lastName"
                                       placeholder="<fmt:message key="local.lastName"/>" pattern="[A-ZА-Я][a-zа-я]{1,14}"
                                       title="<fmt:message key="local.incorrectNameMessage"/>"
                                       value="${user.lastName}" required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <c:if test="${user.lastName eq null}">
                                    <div class="errorLoginPass">
                                        <p class="errorLoginPass text-center"><fmt:message key="local.incorrectNameMessage"/></p>
                                    </div>
                                </c:if>
                            </c:if>
                            <div class="form-group">
                                <label for="telephoneNumber"><fmt:message key="local.telephoneNumber"/>*</label>
                                <input id="telephoneNumber" type="tel" class="form-control" name="telephoneNumber"
                                       placeholder="<fmt:message key="local.telephoneNumber"/>" pattern="\d{12}"
                                       title="<fmt:message key="local.incorrectTelephoneMessage"/>"
                                       value="${user.telephoneNumber}" required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <c:if test="${user.telephoneNumber eq null}">
                                    <div class="errorLoginPass">
                                        <p class="errorLoginPass text-center"><fmt:message key="local.incorrectTelephoneMessage"/></p>
                                    </div>
                                </c:if>
                            </c:if>
                            <div class="form-group m-0">
                                <input type="submit" class="btn btn-primary btn-block"
                                       value="<fmt:message key="local.change"/>">
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