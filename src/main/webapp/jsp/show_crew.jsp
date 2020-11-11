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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tablesort.css">

    <title>
        <fmt:message key="local.detail.detailCrew"/>
    </title>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<main class="content">
    <div class="container">
        <div class="row">
                <div class="container align-text-top">
                        <div class="card">
                            <c:choose>
                                <c:when test="${errorValidationFlag eq true}">
                                <div class="card-header text-center">
                                    <h6 class="errorLoginPass text-center"><fmt:message key="local.incorrectId"/></h6>
                                </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="card-header text-center">
                                        <h5><fmt:message key="local.sidebar.showCrew"/></h5>
                                    </div>
                                    <div class=" card-body">
                                        <div class="container m-2">
                                            <h6>Dispatcher: ${dispatcher.firstName} ${dispatcher.lastName}</h6>
                                        </div>
                                        <c:forEach var="pilot" items="${pilots}" varStatus="counter">
                                            <div class="container m-2">
                                                <h6>Pilot ${counter.count}: ${pilot.firstName} ${pilot.lastName}</h6>
                                            </div>
                                        </c:forEach>
                                        <c:forEach var="navigator" items="${navigators}" varStatus="counter">
                                            <div class="container m-2">
                                                <h6>Navigator ${counter.count}: ${navigator.firstName} ${navigator.lastName}</h6>
                                            </div>
                                        </c:forEach>
                                        <c:forEach var="radioman" items="${radiomans}" varStatus="counter">
                                            <div class="container m-2">
                                                <h6>Radioman ${counter.count}: ${radioman.firstName} ${radioman.lastName}</h6>
                                            </div>
                                        </c:forEach>
                                        <c:forEach var="stewardess" items="${stewardesses}" varStatus="counter">
                                            <div class="container m-2">
                                                <h6>Stewardess ${counter.count}: ${stewardess.firstName} ${stewardess.lastName}</h6>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:otherwise>
                            </c:choose>
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
