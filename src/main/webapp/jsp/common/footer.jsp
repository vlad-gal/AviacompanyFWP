<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="local"/>

<footer class="bg-light">
    <div class="container">
        <div class="d-flex justify-content-between">
            <div class="footer-left">
                <a class="btn" href="#"><fmt:message key="local.footer.developer"/></a>
            </div>
            <div class="footer-right">
                <span>
                   <fmt:message key="local.footer.follow"/>
                    <a href="https://www.facebook.com/"><i class="fab fa-facebook-f"></i></a>
                    <a href="https://www.instagram.com/"><i class="fab fa-instagram"></i></a>
                </span>
            </div>
        </div>
        <div class="text-center">
            <fmt:message key="local.footer.copyright"/>
        </div>
    </div>
</footer>
