<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">

    <title><fmt:message key="local.common.registrationTitle"/></title>
</head>
<body class="login-page">
<jsp:include page="common/header.jsp"/>
<section class="h-100">
    <div class="container h-100">
        <div class="row justify-content-md-center h-100">
            <div class="card-wrapper">
                <div class="card fat">
                    <div class="card-body">
                        <h5 class="card-title text-center"><fmt:message key="local.common.registrationTitle"/></h5>
                        <c:if test="${errorRegisterUserFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.common.errorRegisterUser"/></h6>
                        </c:if>
                        <c:if test="${userAlreadyExistFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.common.userAlreadyExist"/></h6>
                        </c:if>
                        <c:if test="${errorValidationFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.common.errorValidation"/></h6>
                        </c:if>
                        <form method="POST" class="login-validation" name="registrationForm"
                              action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="registration">
                            <div class="form-group">
                                <label for="login"><fmt:message key="local.common.login"/></label>
                                <input id="login" type="text" class="form-control" name="login"
                                       placeholder="<fmt:message key="local.common.login"/>" pattern="[\w-]{3,40}"
                                       title="<fmt:message key="local.common.incorrectLoginMessage"/>"
                                       value="${registrationData.login}"  autofocus required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <c:if test="${registrationData.login eq null}">
                                    <div class="errorLoginPass">
                                        <p class="errorLoginPass text-center"><fmt:message key="local.common.incorrectLoginMessage"/></p>
                                    </div>
                                </c:if>
                            </c:if>
                            <div class="form-group">
                                <label for="email"><fmt:message key="local.common.email"/></label>
                                <input id="email" type="text" class="form-control" name="email"
                                       placeholder="<fmt:message key="local.common.email"/>" pattern="[\w-.]+@[a-zA-Z]+\.[a-z]{2,6}"
                                       title="<fmt:message key="local.common.incorrectEmailMessage"/>"
                                       value="${registrationData.email}" required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <c:if test="${registrationData.email eq null}">
                                    <div class="errorLoginPass">
                                        <p class="errorLoginPass text-center"><fmt:message key="local.common.incorrectEmailMessage"/></p>
                                    </div>
                                </c:if>
                            </c:if>
                            <div class="form-group">
                                <label for="password"><fmt:message key="local.common.password"/></label>
                                <input id="password" type="password" class="form-control" name="password"
                                       placeholder="<fmt:message key="local.common.password"/>" pattern="[^\s]{8,25}"
                                       title="<fmt:message key="local.common.incorrectPasswordMessage"/>"
                                       value="" required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <c:if test="${registrationData.password eq null}">
                                    <div class="errorLoginPass">
                                        <p class="errorLoginPass text-center"><fmt:message key="local.common.incorrectPasswordMessage"/></p>
                                    </div>
                                </c:if>
                            </c:if>
                            <div class="form-group">
                                <label for="confirmPassword"><fmt:message key="local.common.confirmPassword"/></label>
                                <input id="confirmPassword" type="password" class="form-control" name="confirmPassword"
                                       placeholder="<fmt:message key="local.common.confirmPassword"/>" pattern="[^\s]{8,25}"
                                       title="<fmt:message key="local.common.incorrectPasswordMessage"/>"
                                       value="" required>
                            </div>
                            <div class="form-group">
                                <label for="firstName"><fmt:message key="local.common.firstName"/></label>
                                <input id="firstName" type="text" class="form-control" name="firstName"
                                       placeholder="<fmt:message key="local.common.firstName"/>" pattern="[A-ZА-Я][a-zа-я]+"
                                       title="<fmt:message key="local.common.incorrectNameMessage"/>"
                                       value="${registrationData.firstName}" required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <c:if test="${registrationData.firstName eq null}">
                                    <div class="errorLoginPass">
                                        <p class="errorLoginPass text-center"><fmt:message key="local.common.incorrectNameMessage"/></p>
                                    </div>
                                </c:if>
                            </c:if>
                            <div class="form-group">
                                <label for="lastName"><fmt:message key="local.common.lastName"/></label>
                                <input id="lastName" type="text" class="form-control" name="lastName"
                                       placeholder="<fmt:message key="local.common.lastName"/>" pattern="[A-ZА-Я][a-zа-я]+"
                                       title="<fmt:message key="local.common.incorrectNameMessage"/>"
                                       value="${registrationData.lastName}" required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <c:if test="${registrationData.lastName eq null}">
                                    <div class="errorLoginPass">
                                        <p class="errorLoginPass text-center"><fmt:message key="local.common.incorrectNameMessage"/></p>
                                    </div>
                                </c:if>
                            </c:if>
                            <div class="form-group">
                                <label for="telephoneNumber"><fmt:message key="local.common.telephoneNumber"/></label>
                                <input id="telephoneNumber" type="tel" class="form-control" name="telephoneNumber"
                                       placeholder="<fmt:message key="local.common.telephoneNumber"/>" pattern="\d{12}"
                                       title="<fmt:message key="local.common.incorrectTelephoneMessage"/>"
                                       value="${registrationData.telephoneNumber}" required>
                            </div>
                            <c:if test="${errorValidationFlag eq true}">
                                <c:if test="${registrationData.telephoneNumber eq null}">
                                    <div class="errorLoginPass">
                                        <p class="errorLoginPass text-center"><fmt:message key="local.common.incorrectTelephoneMessage"/></p>
                                    </div>
                                </c:if>
                            </c:if>
                            <div class="form-group m-0">
                                <input type="submit" class="btn btn-primary btn-block" value="<fmt:message key="local.common.registration"/>">
                            </div>
                            <div class="mt-4 text-center">
                                <fmt:message key="local.common.haveAccount"/> <a
                                    href="controller?command=authorization_page"><fmt:message key="local.common.authorizationTitle"/></a>
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

