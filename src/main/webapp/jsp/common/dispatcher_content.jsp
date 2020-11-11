<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="ctg" uri="paginationTags" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>

<div class="container">
<div class="card">

<%--<c:if test="${showUsersFlag eq true}">--%>
    <%--<c:set var="showUsersFlag" value="true" scope="session"/>--%>
    <%--<c:remove var="showFlightsFlag" scope="session"/>--%>
    <%--<c:remove var="showCrewsFlag" scope="session"/>--%>
<%--</c:if>--%>
<%--<c:if test="${showFlightsFlag eq true}">--%>
    <%--<c:set var="showFlightsFlag" value="true" scope="session"/>--%>
    <%--<c:remove var="showUsersFlag" scope="session"/>--%>
    <%--<c:remove var="showCrewsFlag" scope="session"/>--%>
<%--</c:if>--%>
<%--<c:if test="${showCrewsFlag eq true}">--%>
    <%--<c:set var="showCrewsFlag" value="true" scope="session"/>--%>
    <%--<c:remove var="showUsersFlag" scope="session"/>--%>
    <%--<c:remove var="showFlightsFlag" scope="session"/>--%>
<%--</c:if>--%>
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
</div>
</div>