<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="ctg" uri="paginationTags" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tablesort.css">

    <title><fmt:message key="local.common.flights"/></title>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<div class="container h-100">
    <div class="row h-100">
        <div class="align-self-center mr-auto col-4">
            <h5><fmt:message key="local.flight.title"/></h5>
            <p class="card-text"><fmt:message key="local.flight.description"/></p>
        </div>
        <div class="col-8 ml-auto">
            <div class="card">
                <div class="card-header text-center">
                    <h5><fmt:message key="local.common.flights"/></h5>
                </div>
                <div class="card-body">
                    <c:choose>
                        <c:when test="${empty flightList}">
                            <div class="text-center">
                                <h5 class="card-title"><fmt:message key="local.flight.emptyFlightList"/></h5>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <table class="table table-hover" id="sort-flight">
                                <thead class="thead-light">
                                <tr class="row-a">
                                    <th scope="col"><fmt:message key="local.flight.departureAirport"/></th>
                                    <th scope="col"><fmt:message key="local.flight.destinationAirport"/></th>
                                    <th scope="col"><fmt:message key="local.flight.departTime"/></th>
                                    <th scope="col"><fmt:message key="local.flight.arriveTime"/></th>
                                </tr>
                                </thead>
                                <ctg:flightPagination currentPageNumber="${currentPageNumber}"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/01efe1ad65.js"></script>
<script src="${pageContext.request.contextPath}/js/tablesort.min.js"></script>
<script src="${pageContext.request.contextPath}/js/tablesort.date.min.js"></script>
<script src="${pageContext.request.contextPath}/js/tablesort.number.min.js"></script>
<script>
    new Tablesort(document.getElementById('sort-flight'));
</script>