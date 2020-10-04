<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${role} page</title>
</head>
<body>
Welcome, ${firstName}  ${lastName}.
<br>
Phone number:${telephoneNumber}
<br>
Role:${role}
<br>
<form name="logout" action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="Log out">
</form>
</body>
</html>
