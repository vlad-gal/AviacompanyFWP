<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="local" var="local"/>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">

    <title>${authorizationTitle}</title>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<div class="container">
    <div class="row h-100">
        <jsp:include page="common/sidebar.jsp"/>
        <div class="col-9">
            gkjlfgdlgjfdklgjf
        </div>
    </div>

</div>


<jsp:include page="common/footer.jsp"/>
<%--Welcome, ${firstName}  ${lastName}.--%>
<%--<br>--%>
<%--Phone number:${telephoneNumber}--%>
<%--<br>--%>
<%--Role:${role}--%>
<%--<br>--%>
<%--Status:${status}--%>
<%--<br>--%>
<%--<form name="logout" action="${pageContext.request.contextPath}/controller" method="post">--%>
<%--<input type="hidden" name="command" value="log_out">--%>
<%--<input type="submit" value="Log out">--%>
<%--</form>--%>
</body>
</html>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/01efe1ad65.js"></script>