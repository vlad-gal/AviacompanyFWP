<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.footer.developer" var="developer"/>
<fmt:message bundle="${local}" key="local.footer.copyright" var="copyright"/>
<fmt:message bundle="${local}" key="local.footer.follow" var="follow"/>

<footer class="bg-light">
        <div class="container">
            <div class="d-flex justify-content-between">
                <div class="footer-left">
                    <a class="btn" href="#">${developer}</a>
                </div>
                <div class="footer-right">
                <span>
                    ${follow}
                    <a href="https://www.facebook.com/"><i class="fab fa-facebook-f"></i></a>
                    <a href="https://www.instagram.com/"><i class="fab fa-instagram"></i></a>
                </span>
                </div>
            </div>
                <div class="text-center">
                    ${copyright}
                </div>
        </div>
</footer>
