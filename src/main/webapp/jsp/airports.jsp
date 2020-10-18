<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.airport.title" var="airportsTitle"/>
<fmt:message bundle="${local}" key="local.airport.emptyAirportList" var="emptyAirportList"/>
<fmt:message bundle="${local}" key="local.airport.name" var="airportName"/>
<fmt:message bundle="${local}" key="local.airport.city" var="airportCity"/>
<fmt:message bundle="${local}" key="local.airport.country" var="airportCountry"/>
<fmt:message bundle="${local}" key="local.airport.description" var="airportDescription"/>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">

    <title>${airportsTitle}</title>
</head>
<jsp:include page="common/header.jsp"/>
<body>
<div class="container h-100">

    <div class="row h-100">
        <div class="align-self-center mr-auto col-4">
            <h5>${airportsTitle}</h5>
            <p class="card-text">${airportDescription}</p>
        </div>
        <div class="col-8 ml-auto">
            <div class="card">
                <div class="card-header text-center">
                    <h5>${airportsTitle}</h5>
                </div>
                <div class="card-body">
                    <c:choose>
                        <c:when test="${empty airportList}">
                            <div class="text-center">
                                <h5 class="card-title">${emptyAirportList}</h5>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <table class="table" id="sort-airport">
                                <thead class="thead-light">
                                <tr class="row-a">
                                    <th scope="col" >${airportName}</th>
                                    <th scope="col" >${airportCountry}</th>
                                    <th scope="col" >${airportCity}</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="airport" items="${airportList}">
                                    <tr class="row-a"
                                        onclick="document.location ='controller?command=detail_page&airportId=${airport.id}'">
                                        <td>${airport.airportName}</td>
                                        <td>${airport.country}</td>
                                        <td>${airport.city}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${errorGetAirportDataFromDB ne null}">
                        <div class="text-center">
                            <h5 class="card-title">${errorGetAirportDataFromDB}</h5>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<jsp:include page="common/footer.jsp"/>
</html>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/01efe1ad65.js"></script>
<script src="${pageContext.request.contextPath}/js/tablesort.min.js"></script>
<script>
    new Tablesort(document.getElementById('sort-airport'));
</script>