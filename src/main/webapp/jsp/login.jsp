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

    <title><fmt:message key="local.authorization"/></title>
</head>
<body class="login-page">
<c:import url="common/header.jsp"/>
<main class="content">
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="card-wrapper">
                <div class="card fat">
                    <div class="card-body">
                        <h5 class="card-title text-center"><fmt:message key="local.authorization"/></h5>
                        <c:if test="${errorLoginPasswordFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.errorLoginPassword"/></h6>
                        </c:if>
                        <c:if test="${inactiveUserFlag eq true}">
                            <h6 class="errorLoginPass text-center"><fmt:message key="local.inactiveUser"/></h6>
                        </c:if>
                        <form method="POST" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="authorization">
                            <div class="form-group">
                                <label for="login"><fmt:message key="local.login"/></label>
                                <input id="login" type="text" class="form-control" name="login"
                                       placeholder="<fmt:message key="local.login"/>"
                                       value="${authorizationData.login}" pattern="[\w-]{3,40}"
                                       title="<fmt:message key="local.incorrectLoginMessage"/>" required autofocus>
                                <c:if test="${errorValidationFlag eq true}">
                                    <c:if test="${authorizationData.login eq null}">
                                        <div class="errorLoginPass">
                                            <p class="errorLoginPass text-center"><fmt:message key="local.incorrectLoginMessage"/></p>
                                        </div>
                                    </c:if>
                                </c:if>
                            </div>
                            <div class="form-group">
                                <label for="password"><fmt:message key="local.password"/>
                                    <a href="controller?command=forgot_password_page" class="float-right">
                                        <fmt:message key="local.forgotPassword"/>
                                    </a>
                                </label>
                                <input id="password" type="password" class="form-control" name="password" required
                                       pattern="[^\s]{8,25}"
                                       title="<fmt:message key="local.incorrectPasswordMessage"/>"
                                       placeholder="<fmt:message key="local.password"/>">
                                <c:if test="${errorValidationFlag eq true}">
                                    <c:if test="${authorizationData.password eq null}">
                                        <div class="errorLoginPass">
                                            <p class="errorLoginPass text-center"><fmt:message key="local.incorrectPasswordMessage"/></p>
                                        </div>
                                    </c:if>
                                </c:if>
                            </div>
                            <div class="form-group m-0">
                                <input type="submit" class="btn btn-primary btn-block"
                                       value="<fmt:message key="local.authorization"/>">
                            </div>
                            <div class="mt-4 text-center">
                                <fmt:message key="local.dontHaveAccount"/> <a
                                    href="controller?command=registration_page"><fmt:message key="local.registration"/></a>
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