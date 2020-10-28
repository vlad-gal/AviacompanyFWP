<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.common.registrationTitle" var="registrationTitle"/>
<fmt:message bundle="${local}" key="local.common.login" var="loginTitle"/>
<fmt:message bundle="${local}" key="local.common.email" var="emailTitle"/>
<fmt:message bundle="${local}" key="local.common.password" var="passwordTitle"/>
<fmt:message bundle="${local}" key="local.common.confirmPassword" var="confirmPasswordTitle"/>
<fmt:message bundle="${local}" key="local.common.firstName" var="firstNameTitle"/>
<fmt:message bundle="${local}" key="local.common.lastName" var="lastNameTitle"/>
<fmt:message bundle="${local}" key="local.common.telephoneNumber" var="telephoneNumberTitle"/>
<fmt:message bundle="${local}" key="local.common.registration" var="registrationButton"/>
<fmt:message bundle="${local}" key="local.common.authorizationTitle" var="authorizationTitle"/>
<fmt:message bundle="${local}" key="local.common.haveAccount" var="haveAccount"/>

<fmt:message bundle="${local}" key="local.common.incorrectLoginMessage" var="incorrectLoginMessage"/>
<fmt:message bundle="${local}" key="local.common.incorrectPasswordMessage" var="incorrectPasswordMessage"/>
<fmt:message bundle="${local}" key="local.common.incorrectEmailMessage" var="incorrectEmailMessage"/>
<fmt:message bundle="${local}" key="local.common.incorrectNameMessage" var="incorrectNameMessage"/>
<fmt:message bundle="${local}" key="local.common.incorrectTelephoneMessage" var="incorrectTelephoneMessage"/>
<fmt:message bundle="${local}" key="local.common.errorRegisterUser" var="errorRegisterUser"/>
<fmt:message bundle="${local}" key="local.common.userAlreadyExist" var="userAlreadyExist"/>
<fmt:message bundle="${local}" key="local.common.errorValidation" var="errorValidation"/>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">

    <title>${registrationTitle}</title>
</head>
<body class="login-page">
<jsp:include page="common/header.jsp"/>
<section class="h-100">
    <div class="container h-100">
        <div class="row justify-content-md-center h-100">
            <div class="card-wrapper">
                <div class="card fat">
                    <div class="card-body">
                        <h5 class="card-title text-center">${registrationTitle}</h5>
                        <c:if test="${errorRegisterUserFlag eq true}">
                            <h6 class="errorLoginPass text-center">${errorRegisterUser}</h6>
                        </c:if>
                        <c:if test="${userAlreadyExistFlag eq true}">
                            <h6 class="errorLoginPass text-center">${userAlreadyExist}</h6>
                        </c:if>
                        <c:if test="${errorValidationFlag eq true}">
                            <h6 class="errorLoginPass text-center">${errorValidation}</h6>
                        </c:if>
                        <form method="POST" class="login-validation" name="registrationForm"
                              action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="registration">
                            <div class="form-group">
                                <label for="login">${loginTitle}</label>
                                <input id="login" type="text" class="form-control" name="login"
                                       placeholder="${loginTitle}" pattern="[\w-]{3,40}" title="${incorrectLoginMessage}"
                                       value="${registrationData.login}"  autofocus required>
                            </div>
                            <div class="form-group">
                                <label for="email">${emailTitle}</label>
                                <input id="email" type="text" class="form-control" name="email"
                                       placeholder="${emailTitle}" pattern="[\w-.]+@[a-zA-Z]+\.[a-z]{2,6}" title="${incorrectEmailMessage}"
                                       value="${registrationData.email}" required>
                            </div>
                            <div class="form-group">
                                <label for="password">${passwordTitle}</label>
                                <input id="password" type="password" class="form-control" name="password"
                                       placeholder="${passwordTitle}" pattern="[^\s]{8,25}" title="${incorrectPasswordMessage}"
                                       value="" required>
                            </div>
                            <div class="form-group">
                                <label for="confirmPassword">${confirmPasswordTitle}</label>
                                <input id="confirmPassword" type="password" class="form-control" name="confirmPassword"
                                       placeholder="${passwordTitle}" pattern="[^\s]{8,25}" title="${incorrectPasswordMessage}"
                                       value="" required>
                            </div>
                            <div class="form-group">
                                <label for="firstName">${firstNameTitle}</label>
                                <input id="firstName" type="text" class="form-control" name="firstName"
                                       placeholder="${firstNameTitle}" pattern="[A-ZА-Я][a-zа-я]+" title="${incorrectNameMessage}"
                                       value="${registrationData.firstName}" required>
                            </div>
                            <div class="form-group">
                                <label for="lastName">${lastNameTitle}</label>
                                <input id="lastName" type="text" class="form-control" name="lastName"
                                       placeholder="${lastNameTitle}" pattern="[A-ZА-Я][a-zа-я]+" title="${incorrectNameMessage}"
                                       value="${registrationData.lastName}" required>
                            </div>
                            <div class="form-group">
                                <label for="telephoneNumber">${telephoneNumberTitle}</label>
                                <input id="telephoneNumber" type="tel" class="form-control" name="telephoneNumber"
                                       placeholder="${telephoneNumberTitle}" pattern="\d{12}" title="${incorrectTelephoneMessage}"
                                       value="${registrationData.telephoneNumber}" required>
                            </div>
                            <div class="form-group m-0">
                                <input type="submit" class="btn btn-primary btn-block" value="${registrationButton}">
                            </div>
                            <div class="mt-4 text-center">
                                ${haveAccount} <a
                                    href="controller?command=authorization_page">${authorizationTitle}</a>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>
</section>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/01efe1ad65.js"></script>
<script src="${pageContext.request.contextPath}/js/check-password.js"></script>

