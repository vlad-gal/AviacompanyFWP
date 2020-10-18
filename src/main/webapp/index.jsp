<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty sessionScope.lang}">
    <c:set var="lang" value="ru-RU" scope="session"/>
</c:if>
<c:redirect url="/controller?command=welcome_page"/>
