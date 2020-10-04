<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 02.10.2020
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
Form for registration
<br>
<form action="${pageContext.request.contextPath}/controller" method="post" name="registration">
    <input type="hidden" name="command" value="registration">
    <br>
    E-mail:
    <br>
    <input type="email" name="email" value="">
    <br>
    Login:
    <br>
    <input type="text" name="login" value="">
    <br>
    Password:
    <br>
    <input type="password" name="password" value="">
    <br>
    Confirm your password:
    <br>
    <input type="password" name="passwordConfirm" value="">
    <br>
    Telephone number:
    <br>
    <input type="number" name="telephone" value="">
    <br>
    First name:
    <br>
    <input type="text" name="firstName" value="">
    <br>
    Last name:
    <br>
    <input type="text" name="lastName" value="">
    <br> <br> <br>
    <input type="submit" value="Registration">

    <br>
    ${errorLoginPassMessage}
    <br>
    ${wrongAction}
    <br>
    ${nullPage}
    <br>
</form>
</body>
</html>
