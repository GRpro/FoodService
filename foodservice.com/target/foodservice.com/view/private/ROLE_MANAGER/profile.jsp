<%@ page import="com.foodservice.security.CustomUserDetails" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
<%@page session="true"%>
<head>
    <meta charset="utf-8">
    <title>Manager profile</title>

</head>
<body id="body" >

<%--include header--%>
<%@ include file="/view/public/common/header.jsp" %>

<div id="content">
    <div id="leftBar">

    </div>
    <div id="centralBar">

    </div>
    <div id="rightBar">

    </div>
</div>

<%--include footer--%>
<%@ include file="/view/public/common/footer.jsp" %>

</body>
</html>