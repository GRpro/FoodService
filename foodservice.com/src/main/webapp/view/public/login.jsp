<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />

<head>
  <title>Login Page</title>
</head>

<body onload='document.f.username.focus();'>
    
    <%--include header--%>
    <%@ include file="/view/public/common/header.jsp" %>

    <div id="content">
      <div id="leftBar">
      </div>
      <div id="centralBar">
        <h2>Sign in</h2>

        <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
          <div class="errorblock">
            Your login attempt was not successful, try again.<br/>
              ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
          </div>
        </c:if>
        
        
        <br>
        <form name='f' action="<c:url value='/j_spring_security_check' />" method='POST'>
          <p>Login</p>
          <p><input type='text' name='username' style="border-width: 1px; border-style: solid"></p>
          <p>Password</p>
          <p><input type='password' name='password' style="border-width: 1px; border-style: solid"></p>
          <p><input id="remember" name="_spring_security_remember_me" type="checkbox" /> Remember me</p>
          <button name="submit" type="submit">Sign in</button>

          <input type="hidden" name="${_csrf.parameterName}"
                 value="${_csrf.token}" />
        </form>
      </div>
      <div id="rightBar">
      </div>
    </div>

    <%--include footer--%>
    <%@ include file="/view/public/common/footer.jsp" %>

</body>
</html>
