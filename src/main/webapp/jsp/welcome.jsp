<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
Welcome to aviacompany FWP.
<br>
<br>
<br>
<br>
Our aircrafts:
<form name="showAircraft" method="post" action="${pageContext.request.contextPath}/controller" autocomplete="on" >
    <input type="hidden"  name="command" value="selectAircraft" >
    <input type="submit"  value="Show available aircraft" >
</form>
<table border="1px">
    <c:forEach var="aircraft" items="${aircraftList}">
        <tr><td><c:out value="${aircraft}"/></td></tr>
    </c:forEach>
</table>

<br><br><br><br>
<a href="${pageContext.request.contextPath}/jsp/login.jsp">Log in</a>
<a href="${pageContext.request.contextPath}/jsp/registration.jsp">Sign in</a>
</body>
</html>
