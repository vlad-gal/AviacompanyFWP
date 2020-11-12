<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="ctg" uri="paginationTags" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>

<div class="container">
    <div class="card">
        <c:if test="${showFlightsFlag eq true}">
            <c:set var="showFlightsFlag" value="true" scope="session"/>
            <c:remove var="showCrewsFlag" scope="session"/>
        </c:if>
        <c:if test="${showCrewsFlag eq true}">
            <c:set var="showCrewsFlag" value="true" scope="session"/>
            <c:remove var="showFlightsFlag" scope="session"/>
        </c:if>
        <c:if test="${showFlightsFlag eq true}">
            <c:if test="${not empty flightList}">
                <div class="card-header text-center">
                    <h5><fmt:message key="local.flights"/></h5>
                </div>
                <div class=" card-body ">
                    <table class="table table-hover">
                        <thead class="thead-light">
                        <tr>
                            <th scope="col"><fmt:message key="local.departureAirport"/></th>
                            <th scope="col"><fmt:message key="local.destinationAirport"/></th>
                            <th scope="col"><fmt:message key="local.departTime"/></th>
                            <th scope="col"><fmt:message key="local.arriveTime"/></th>
                            <th scope="col"><fmt:message key="local.crewName"/></th>
                            <th scope="col"><fmt:message key="local.status"/></th>
                            <th scope="col"><fmt:message key="local.operator"/></th>
                        </tr>
                        </thead>
                            <ctg:flightPagination currentPageNumber="${currentPageNumber}"/>
                </div>
            </c:if>
        </c:if>
        <c:if test="${showCrewsFlag eq true}">
            <c:if test="${not empty allCrewList}">
                <div class="card-header text-center">
                    <h5><fmt:message key="local.crews"/></h5>
                </div>
                <div class=" card-body ">
                    <table class="table table-hover">
                        <thead class="thead-light">
                        <tr>
                            <th scope="col"><fmt:message key="local.dispatcher"/></th>
                            <th scope="col"><fmt:message key="local.crewName"/></th>
                            <th scope="col"><fmt:message key="local.numberOfPilots"/></th>
                            <th scope="col"><fmt:message key="local.numberOfNavigators"/></th>
                            <th scope="col"><fmt:message key="local.numberOfRadioman"/></th>
                            <th scope="col"><fmt:message key="local.numberOfStewardesses"/></th>
                            <th scope="col"><fmt:message key="local.status"/></th>
                            <th scope="col"><fmt:message key="local.actions"/></th>
                        </tr>
                        </thead>
                            <ctg:crewPagination currentPageNumber="${currentPageNumber}"/>
                </div>
            </c:if>
        </c:if>
    </div>
</div>
