<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.aircraft.title" var="aircraftsTitle"/>
<fmt:message bundle="${local}" key="local.common.aircrafts" var="title"/>
<fmt:message bundle="${local}" key="local.aircraft.emptyAircraftList" var="emptyAircraftList"/>
<fmt:message bundle="${local}" key="local.aircraft.tailNumber" var="aircraftTailNumber"/>
<fmt:message bundle="${local}" key="local.aircraft.name" var="aircraftName"/>
<fmt:message bundle="${local}" key="local.aircraft.type" var="aircraftType"/>
<fmt:message bundle="${local}" key="local.aircraft.description" var="aircraftsDescription"/>
<fmt:message bundle="${local}" key="local.aircraft.button" var="aircraftButton"/>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">

    <title>${title}</title>
</head>
<jsp:include page="common/header.jsp"/>
<body>
<div class="container">

    <div class="row">
        <div class="align-self-center mr-auto col-6">
            <h5>${aircraftsTitle}</h5>
            <p class="card-text">${aircraftsDescription}</p>
        </div>
        <div class="col-6 ml-auto">
            <div class="card ">
                <div class="card-header text-center">
                    <h5>${aircraftsTitle}</h5>
                </div>
                <div class="card-body">
                    <c:choose>
                        <c:when test="${empty aircraftList}">
                            <div class="text-center">
                                <h5 class="card-title">${emptyAircraftList}</h5>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <table class="table" id="sort-aircraft">
                                <thead class="thead-light">
                                <tr class="row-a">
                                    <th scope="col" >${aircraftTailNumber}</th>
                                    <th scope="col">${aircraftName}</th>
                                    <th scope="col" >${aircraftType}</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="aircraft" items="${aircraftList}">
                                    <tr class="row-a"
                                        onclick="document.location ='controller?command=detail_page&aircraftId=${aircraft.id}'">
                                        <td>${aircraft.tailNumber}</td>
                                        <td>${aircraft.aircraftName}</td>
                                        <td>${aircraft.aircraftType}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${errorGetAircraftDataFromDB ne null}">
                        <div class="text-center">
                            <h5 class="card-title">${errorGetAircraftDataFromDB}</h5>
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
    new Tablesort(document.getElementById('sort-aircraft'));
</script>