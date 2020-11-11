<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="ctg" uri="paginationTags" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>

<div class="container">
    <div class="card">

        <c:if test="${showUsersFlag eq true}">
            <c:set var="showUsersFlag" value="true" scope="session"/>
            <c:remove var="showFlightsFlag" scope="session"/>
            <c:remove var="showCrewsFlag" scope="session"/>
            <c:remove var="showAirportsFlag" scope="session"/>
            <c:remove var="showAircraftsFlag" scope="session"/>
        </c:if>
        <c:if test="${showFlightsFlag eq true}">
            <c:set var="showFlightsFlag" value="true" scope="session"/>
            <c:remove var="showUsersFlag" scope="session"/>
            <c:remove var="showCrewsFlag" scope="session"/>
            <c:remove var="showAirportsFlag" scope="session"/>
            <c:remove var="showAircraftsFlag" scope="session"/>
        </c:if>
        <c:if test="${showCrewsFlag eq true}">
            <c:set var="showCrewsFlag" value="true" scope="session"/>
            <c:remove var="showUsersFlag" scope="session"/>
            <c:remove var="showFlightsFlag" scope="session"/>
            <c:remove var="showAirportsFlag" scope="session"/>
            <c:remove var="showAircraftsFlag" scope="session"/>
        </c:if>
        <c:if test="${showAirportsFlag eq true}">
            <c:set var="showAirportsFlag" value="true" scope="session"/>
            <c:remove var="showUsersFlag" scope="session"/>
            <c:remove var="showFlightsFlag" scope="session"/>
            <c:remove var="showCrewsFlag" scope="session"/>
            <c:remove var="showAircraftsFlag" scope="session"/>
        </c:if>
        <c:if test="${showAircraftsFlag eq true}">
            <c:set var="showAircraftsFlag" value="true" scope="session"/>
            <c:remove var="showUsersFlag" scope="session"/>
            <c:remove var="showFlightsFlag" scope="session"/>
            <c:remove var="showCrewsFlag" scope="session"/>
            <c:remove var="showAirportsFlag" scope="session"/>
        </c:if>
        <c:if test="${showUsersFlag eq true}">
            <c:if test="${not empty allUsersList}">
                <div class="card-header text-center">
                    <h5><fmt:message key="local.sidebar.users"/></h5>
                </div>
                <div class=" card-body">
                    <table class="table table-hover">
                        <thead class="thead-light">
                        <tr>
                            <th scope="col"><fmt:message key="local.common.firstName"/></th>
                            <th scope="col"><fmt:message key="local.common.lastName"/></th>
                            <th scope="col"><fmt:message key="local.common.telephoneNumber"/></th>
                            <th scope="col"><fmt:message key="local.common.email"/></th>
                            <th scope="col"><fmt:message key="local.common.role"/></th>
                            <th scope="col"><fmt:message key="local.common.status"/></th>
                            <th scope="col"><fmt:message key="local.common.actions"/></th>
                        </tr>
                        </thead>
                            <ctg:userPagination currentPageNumber="${currentPageNumber}"/>
                </div>
            </c:if>
        </c:if>
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
        </c:if>
        <c:if test="${showCrewsFlag eq true}">
            <c:if test="${not empty allCrewList}">
                <div class="card-header text-center">
                    <h5><fmt:message key="local.crew.crews"/></h5>
                </div>
                <div class=" card-body ">
                    <table class="table table-hover">
                        <thead class="thead-light">
                        <tr>
                            <th scope="col"><fmt:message key="local.crew.dispatcher"/></th>
                            <th scope="col"><fmt:message key="local.crew.crewName"/></th>
                            <th scope="col"><fmt:message key="local.crew.numberOfPilots"/></th>
                            <th scope="col"><fmt:message key="local.crew.numberOfNavigators"/></th>
                            <th scope="col"><fmt:message key="local.crew.numberOfRadioman"/></th>
                            <th scope="col"><fmt:message key="local.crew.numberOfStewardesses"/></th>
                            <th scope="col"><fmt:message key="local.common.status"/></th>
                            <th scope="col"><fmt:message key="local.common.actions"/></th>
                        </tr>
                        </thead>
                            <ctg:crewPagination currentPageNumber="${currentPageNumber}"/>
                </div>
            </c:if>
        </c:if>
        <c:if test="${showAirportsFlag eq true}">
            <c:if test="${not empty airportList}">
                <div class="card-header text-center">
                    <h5><fmt:message key="local.common.airports"/></h5>
                </div>
                <div class=" card-body ">
                    <table class="table table-hover">
                        <thead class="thead-light">
                        <tr>
                            <th scope="col"><fmt:message key="local.airport.name"/></th>
                            <th scope="col"><fmt:message key="local.airport.country"/></th>
                            <th scope="col"><fmt:message key="local.airport.city"/></th>
                            <th scope="col"><fmt:message key="local.common.actions"/></th>
                        </tr>
                        </thead>
                            <ctg:airportPagination currentPageNumber="${currentPageNumber}"/>
                </div>
            </c:if>
        </c:if>
        <c:if test="${showAircraftsFlag eq true}">
            <c:if test="${not empty aircraftList}">
                <div class="card-header text-center">
                    <h5><fmt:message key="local.common.airports"/></h5>
                </div>
                <div class=" card-body ">
                    <table class="table table-hover">
                        <thead class="thead-light">
                        <tr>
                            <th scope="col"><fmt:message key="local.aircraft.tailNumber"/></th>
                            <th scope="col"><fmt:message key="local.aircraft.name"/></th>
                            <th scope="col"><fmt:message key="local.aircraft.type"/></th>
                            <th scope="col"><fmt:message key="local.common.status"/></th>
                            <th scope="col"><fmt:message key="local.common.actions"/></th>
                        </tr>
                        </thead>
                            <ctg:aircraftPagination currentPageNumber="${currentPageNumber}"/>
                </div>
            </c:if>
        </c:if>

    </div>
</div>
<%--<c:if test="${showUsersFlag eq true}">--%>
<%--<c:if test="${not empty allUsersList}">--%>
<%--<div class="card-header text-center">--%>
<%--<h5><fmt:message key="local.sidebar.users"/></h5>--%>
<%--</div>--%>
<%--<div class=" card-body">--%>
<%--<table class="table table-hover">--%>
<%--<thead class="thead-light">--%>
<%--<tr>--%>
<%--<th scope="col"><fmt:message key="local.common.firstName"/></th>--%>
<%--<th scope="col"><fmt:message key="local.common.lastName"/></th>--%>
<%--<th scope="col"><fmt:message key="local.common.telephoneNumber"/></th>--%>
<%--<th scope="col"><fmt:message key="local.common.email"/></th>--%>
<%--<th scope="col"><fmt:message key="local.common.role"/></th>--%>
<%--<th scope="col"><fmt:message key="local.common.status"/></th>--%>
<%--<th scope="col"><fmt:message key="local.common.actions"/></th>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--</div>--%>
<%--<ctg:userPagination currentPageNumber="${currentPageNumber}"/>--%>
<%--<c:if test="${showUsersFlag eq true}">--%>
<%--<c:set var="showUsersFlag" value="false" scope="page"/>--%>
<%--</c:if>--%>
<%--</c:if>--%>
<%--</c:if>--%>
<%--<c:if test="${showFlightsFlag eq true}">--%>
<%--<c:if test="${not empty flightList}">--%>
<%--<div class="card-header text-center">--%>
<%--<h5><fmt:message key="local.common.flights"/></h5>--%>
<%--</div>--%>
<%--<div class=" card-body ">--%>
<%--<table class="table table-hover">--%>
<%--<thead class="thead-light">--%>
<%--<tr>--%>
<%--<th scope="col"><fmt:message key="local.flight.departureAirport"/></th>--%>
<%--<th scope="col"><fmt:message key="local.flight.destinationAirport"/></th>--%>
<%--<th scope="col"><fmt:message key="local.flight.departTime"/></th>--%>
<%--<th scope="col"><fmt:message key="local.flight.arriveTime"/></th>--%>
<%--<th scope="col"><fmt:message key="local.crew.crewName"/></th>--%>
<%--<th scope="col"><fmt:message key="local.common.status"/></th>--%>
<%--<th scope="col"><fmt:message key="local.common.operator"/></th>--%>
<%--<th scope="col"><fmt:message key="local.common.actions"/></th>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--<ctg:flightPagination currentPageNumber="${currentPageNumber}"/>--%>
<%--<c:if test="${showFlightsFlag eq true}">--%>
<%--<c:set var="showFlightsFlag" value="false" scope="page"/>--%>
<%--</c:if>--%>
<%--</div>--%>
<%--</c:if>--%>

<%--</c:if>--%>