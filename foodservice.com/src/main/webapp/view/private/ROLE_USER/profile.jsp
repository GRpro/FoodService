<%@ page import="com.foodservice.security.CustomUserDetails" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
<head>
    <meta charset="utf-8">
    <title>User profile</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
    <%--<script src="${root}/view/res/js/scripts.js" type="text/javascript"></script>--%>
    <script src="${root}/view/res/js/load/REST_client.js"></script>
</head>
<body onload="SimpleUserByID('<%= ((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()%>');">

<%--include header--%>
<%@ include file="/view/public/common/header.jsp" %>

<div id="content">
    <div id="leftBar">
        <form>
            <button formaction="${root}/view/private/friends.jsp" class="list">Friends</button>
            <button formaction="${root}/view/private/messages.jsp" class="list">Messages</button>
        </form>
    </div>
    <div id="centralBar">
        <h2><p>Profile</p></h2>
        <br>
        <div id="profileImage">
            <img height="150px" src="${root}/view/res/images/photo.jpg"/>
        </div>
        <div id="profileText">
            <div id="profileTextKey">
                <p>First name : </p>
                <br>
                <p>Last name : </p>
                <br>
                <p>E-mail : </p>
                <br>
                <p>About you : </p>
            </div>
            <div id="profileTextValue">
                <p id="profileFName"></p>
                <br>
                <p id="profileLName"></p>
                <br>
                <p id="profileEmail"></p>
                <br>
                <p id="profileInfo"></p>
            </div>
            <%--<div id="profileMenu">--%>
                <%--<form>--%>
                    <%--<button formaction="${root}/view/private/friends.jsp" class="imageButton" width="35px">--%>
                        <%--<img src="${root}/view/res/images/ic_action_user.png"/>--%>
                    <%--</button>--%>
                    <%--<button formaction="${root}/view/private/messages.jsp" class="imageButton" width="35px">--%>
                        <%--<img src="${root}/view/res/images/ic_action_mail.png"/>--%>
                    <%--</button>--%>
                <%--</form>--%>
            <%--</div>--%>
        </div>
    </div>
    <div id="rightBar">
        <h2>Friends</h2>
        <br>
        <input class="searchLine" style="width: 89%">
        <button class="searchButton"><img src="${root}/view/res/images/ic_action_search.png" width="20px"></button>
        <div style="text-align: center;">
            <button style="width: 32%;">All friends</button>
            <button style="width: 32%;">Online</button>
            <button style="width: 32%;">Requests</button>
        </div>
    </div>
</div>

<%--include footer--%>
<%@ include file="/view/public/common/footer.jsp" %>

</body>
</html>