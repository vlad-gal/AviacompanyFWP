<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 04.10.2020
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin page</title>
</head>
<body>
Role:${role}
<br>
Status:${status}
<br>
<form name="logout" action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="Log out">
</form>
</body>
</html>
