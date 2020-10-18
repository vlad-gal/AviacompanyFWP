<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">

    <title>${registrationTitle}</title>
</head>
<jsp:include page="common/header.jsp"/>
<body class="login-page">
<section class="h-100">
    <div class="container h-100">
        <div class="row justify-content-md-center h-100">
            <div class="card-wrapper">
                <div class="card fat">
                    <div class="card-body">
                        <h5 class="card-title text-center">${authorizationTitle}</h5>
                        <h5 class="errorLoginPass text-center">${errorLoginPassword}</h5>
                        <form method="POST" class="login-validation" name="registrationForm"
                              action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="registration">
                            <div class="form-group">
                                <label for="login">${loginTitle}</label>
                                <input id="login" type="text" class="form-control" name="login"  placeholder="${loginTitle}"
                                       value="" required autofocus>
                            </div>
                            <div class="form-group">
                                <label for="password">${passwordTitle}
                                    <a href="controller?command=forgot_password_page" class="float-right">
                                        ${forgotPassword}
                                    </a>
                                </label>
                                <input id="password" type="password" class="form-control" name="password" required
                                       placeholder="${passwordTitle}"
                                       data-eye>
                            </div>
                            <div class="form-group">
                                <div class="custom-checkbox custom-control">
                                    <input type="checkbox" name="rememberUser" id="remember"
                                           class="custom-control-input">
                                    <label for="remember" class="custom-control-label">${rememberMe}</label>
                                </div>
                            </div>
                            <div class="form-group m-0">
                                <input type="submit" class="btn btn-primary btn-block" value="${authorizationTitle}">
                            </div>
                            <div class="mt-4 text-center">
                                ${dontHaveAccount} <a href="controller?command=registration_page">${registrationTitle}</a>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>
</section>

<%--Form for registration--%>
<%--<br>--%>
<%--<form action="${pageContext.request.contextPath}/controller" method="post" name="registration">--%>
    <%--<input type="hidden" name="command" value="registration">--%>
    <%--<br>--%>
    <%--E-mail:--%>
    <%--<br>--%>
    <%--<input type="email" name="email" value="">--%>
    <%--<br>--%>
    <%--Login:--%>
    <%--<br>--%>
    <%--<input type="text" name="login" value="">--%>
    <%--<br>--%>
    <%--Password:--%>
    <%--<br>--%>
    <%--<input type="password" name="password" value="">--%>
    <%--<br>--%>
    <%--Confirm your password:--%>
    <%--<br>--%>
    <%--<input type="password" name="passwordConfirm" value="">--%>
    <%--<br>--%>
    <%--Telephone number:--%>
    <%--<br>--%>
    <%--<input type="number" name="telephone" value="">--%>
    <%--<br>--%>
    <%--First name:--%>
    <%--<br>--%>
    <%--<input type="text" name="firstName" value="">--%>
    <%--<br>--%>
    <%--Last name:--%>
    <%--<br>--%>
    <%--<input type="text" name="lastName" value="">--%>
    <%--<br> <br> <br>--%>
    <%--<input type="submit" value="Registration">--%>

    <%--<br>--%>
    <%--${errorLoginPassMessage}--%>
    <%--<br>--%>
    <%--${wrongAction}--%>
    <%--<br>--%>
    <%--${nullPage}--%>
    <%--<br>--%>
<%--</form>--%>
</body>
<jsp:include page="common/footer.jsp"/>
</html>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/01efe1ad65.js"></script>