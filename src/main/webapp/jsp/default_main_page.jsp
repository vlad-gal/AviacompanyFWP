<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 02.10.2020
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
Welcome, ${firstName}  ${lastName}.
<br>
Phone number:${telephoneNumber}
<br>
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
