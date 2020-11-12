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

    <title><fmt:message key="local.registrationTitle"/></title>
</head>
<body class="login-page">
<c:import url="common/header.jsp"/>
<main class="content">
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="card-wrapper">
                <div class="card fat">
                    <div class="card-body">
                        <h5 class="card-title text-center"><fmt:message key="local.registrationTitle"/></h5>
                        <c:if test="${errorRegisterUserFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.errorRegisterUser"/></h6>
                        </c:if>
                        <c:if test="${userAlreadyExistFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.userAlreadyExist"/></h6>
                        </c:if>
                        <c:if test="${errorValidationFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.errorValidation"/></h6>
                        </c:if>
                        <form method="POST" name="registrationForm"
                              action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="registration">
                            <div class="form-group">
                                <label for="login"><fmt:message key="local.login"/>*</label>
                                <input id="login" type="text" class="form-control" name="login"
                                       placeholder="<fmt:message key="local.login"/>" pattern="[\w-]{3,40}"
                                       title="<fmt:message key="local.incorrectLoginMessage"/>"
                                       value="${registrationData.login}" autofocus required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <c:if test="${registrationData.login eq null}">
                                    <div class="errorLoginPass">
                                        <p class="errorLoginPass text-center"><fmt:message key="local.incorrectLoginMessage"/></p>
                                    </div>
                                </c:if>
                            </c:if>
                            <div class="form-group">
                                <label for="email"><fmt:message key="local.email"/>*</label>
                                <input id="email" type="email" class="form-control" name="email"
                                       placeholder="<fmt:message key="local.email"/>"
                                       pattern="[\w-.]{3,20}@[a-zA-Zа-яА-Я]{2,14}\.[a-zа-я]{2,6}"
                                       title="<fmt:message key="local.incorrectEmailMessage"/>"
                                       value="${registrationData.email}" required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <c:if test="${registrationData.email eq null}">
                                    <div class="errorLoginPass">
                                        <p class="errorLoginPass text-center"><fmt:message key="local.incorrectEmailMessage"/></p>
                                    </div>
                                </c:if>
                            </c:if>
                            <div class="form-group">
                                <label for="password"><fmt:message key="local.password"/>*</label>
                                <input id="password" type="password" class="form-control" name="password"
                                       placeholder="<fmt:message key="local.password"/>" pattern="[^\s]{8,25}"
                                       title="<fmt:message key="local.incorrectPasswordMessage"/>"
                                       value="" required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <c:if test="${registrationData.password eq null}">
                                    <div class="errorLoginPass">
                                        <p class="errorLoginPass text-center"><fmt:message key="local.incorrectPasswordMessage"/></p>
                                    </div>
                                </c:if>
                            </c:if>
                            <div class="form-group">
                                <label for="confirmPassword"><fmt:message key="local.confirmPassword"/>*</label>
                                <input id="confirmPassword" type="password" class="form-control" name="confirmPassword"
                                       placeholder="<fmt:message key="local.confirmPassword"/>" pattern="[^\s]{8,25}"
                                       title="<fmt:message key="local.incorrectPasswordMessage"/>"
                                       value="" required>
                            </div>
                            <div class="form-group">
                                <label for="firstName"><fmt:message key="local.firstName"/>*</label>
                                <input id="firstName" type="text" class="form-control" name="firstName"
                                       placeholder="<fmt:message key="local.firstName"/>" pattern="[A-ZА-Я][a-zа-я]{1,14}"
                                       title="<fmt:message key="local.incorrectNameMessage"/>"
                                       value="${registrationData.firstName}" required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <c:if test="${registrationData.firstName eq null}">
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
                                       value="${registrationData.lastName}" required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <c:if test="${registrationData.lastName eq null}">
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
                                       value="${registrationData.telephoneNumber}" required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <c:if test="${registrationData.telephoneNumber eq null}">
                                    <div class="errorLoginPass">
                                        <p class="errorLoginPass text-center"><fmt:message key="local.incorrectTelephoneMessage"/></p>
                                    </div>
                                </c:if>
                            </c:if>
                            <div class="form-group">
                                <label for="role"><fmt:message key="local.role"/>*</label>
                                <select id="role" name="role" class="custom-select">
                                    <c:if test="${errorValidationFlag eq true}">
                                        <c:if test="${registrationData.role eq null}">
                                            <option selected>${role.roleName}</option>
                                        </c:if>
                                    </c:if>
                                    <c:forEach var="role" items="${sessionScope.roles}" begin="1">
                                        <option>
                                                ${role.roleName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group m-0">
                                <input type="submit" class="btn btn-primary btn-block"
                                       value="<fmt:message key="local.registration"/>">
                            </div>
                            <div class="mt-4 text-center">
                                <fmt:message key="local.alreadyHaveAccount"/> <a
                                    href="controller?command=authorization_page"><fmt:message key="local.authorizationTitle"/></a>
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
<script src="${pageContext.request.contextPath}/js/check-password.js"></script>
<script src="${pageContext.request.contextPath}/js/security.js"></script>