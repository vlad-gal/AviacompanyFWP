<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>

<footer class="bg-light">
    <div class="container">
        <div class="text-center">
            <fmt:message key="local.copyright"/>
        </div>
    </div>
</footer>
