<%@ page import="com.foodservice.security.CustomUserDetails" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<footer>
<!--    <div id="footerPanel">-->
        <c:if test="${!show}">
            <div id="registrationPoint">
                <form>
                <table>
                    <tr>GRYI production</tr>
                    <tr>
<!--                        <td><button id="signUp" formaction="${root}/view/public/pre_registration.jsp"><p>Sign up</p></button>-->
                    </tr>
                </table>
                </form>
            </div>

        </c:if>
<!--    </div>-->
</footer>

