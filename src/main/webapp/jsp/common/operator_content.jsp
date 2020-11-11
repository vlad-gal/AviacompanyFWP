<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="ctg" uri="paginationTags" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>

<div class="container">
    <div class="card">
        <c:if test="${showFlightsFlag eq true}">
            <c:if test="${not empty flightList}">
                <div class="card-header text-center">
                    <h5><fmt:message key="local.common.flights"/></h5>
                </div>
                <div class=" card-body ">
                    <table class="table table-hover">
                        <thead class="thead-light">
                        <tr>
                            <th scope="col"><fmt:message key="local.flight.departureAirport"/></th>
                            <th scope="col"><fmt:message key="local.flight.destinationAirport"/></th>
                            <th scope="col"><fmt:message key="local.flight.departTime"/></th>
                            <th scope="col"><fmt:message key="local.flight.arriveTime"/></th>
                            <th scope="col"><fmt:message key="local.crew.crewName"/></th>
                            <th scope="col"><fmt:message key="local.common.status"/></th>
                            <th scope="col"><fmt:message key="local.common.operator"/></th>
                            <th scope="col"><fmt:message key="local.common.actions"/></th>
                        </tr>
                        </thead>
                            <ctg:flightPagination currentPageNumber="${currentPageNumber}"/>
                </div>
            </c:if>
            <c:set var="showFlightsFlag" value="false" scope="session"/>
        </c:if>

    </div>
</div>
