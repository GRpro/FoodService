<%@ page import="com.foodservice.security.CustomUserDetails" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<c:set var="show" value="<%=SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof CustomUserDetails %>"/>

  <link href="${root}/view/res/style.css" rel="stylesheet" type="text/css" media="screen" />
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
  <script src="${root}/view/res/js/animate-bg.js" type="text/javascript"></script>
  <script src="${root}/view/res/js/scripts.js" type="text/javascript"></script>

<header>
    <div class="headerCenter">
        <div id="logo">
            <div class="logoLink"><a href="${root}/view/public/index.jsp">FoodService</a></div></a>
        </div>
        <div id="menu">
            <ul id="nav">
                <li><a href="${root}/home">Home</a></li>
                    <li><a href="${root}/service">Service</a></li>
                    <li><a href="#">Work</a></li>
                    <li><a href="#">About</a></li>
            </ul>
        </div>
        <div class="search">
            <input class="searchLine"/>
        </div>
        <c:if test="${!show}">
                <div class="buttons">
                    <form style="display: inline-block; vertical-align: top;">
                        <button id="userSignIn" formaction="${root}/profile">Sign in</button>
                    </form>
                    <button id="managerSignUp" onclick="dropList()">Sign up</button>
                    <ul id="dropList">
                    </ul>
                </div>
        </c:if>
        <c:if test="${show}">
            <div id="profileHeader">
                <div id="profileHeaderText">
                    <p id="lNameIconData" style="display: inline-block; vertical-align: top; width: 120px">
                            <a href="${root}/view/private/redirect">
                                <sec:authentication property="principal.firstName" />
                                <sec:authentication property="principal.lastName" />
                            </a>
                    </p>
                    <p id="emailIconData" style="vertical-align: top; width: 120px"><sec:authentication property="principal.username" /></p>
                  </div>
                <button style="display: inline-block; vertical-align: top; margin-top: 3px;" onclick="javascript:formSubmit()">Logout</button>
                <c:url value="/j_spring_security_logout" var="logoutUrl" />
                  <!-- csrt for log out-->
                  <form id="logoutForm" action="${logoutUrl}" method="post" >
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}" />
                  </form>

                  <script>
                    function formSubmit() {
                      document.getElementById("logoutForm").submit();
                    }
                  </script>

            </div>
        </c:if>
    </div>
</header>