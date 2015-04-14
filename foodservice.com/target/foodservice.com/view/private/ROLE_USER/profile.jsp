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
</head>
<body >

<%--include header--%>
<%@ include file="/view/public/common/header.jsp" %>

<div id="content">
    <div id="leftBar">
        <form>
            <button formaction="${root}/user/friends" class="list">Friends</button>
            <button formaction="${root}/view/private/messages.jsp" class="list">Messages</button>
        </form>
    </div>
    <div id="centralBar">
        <h2><p>Profile</p></h2>
        <br>
        <div id="profileImage">
            <img height="150px" width="150px" src="${root}/content/photo/${user.photoId}"/>
        </div>
        <div id="profileText">
            <div id="profileTextKey">
                <p>First name : </p>
                <br>
                <p>Last name : </p>
                <br>
                <p>DOB : </p>
                <br>
                <p>E-mail : </p>
                <br>
                <p>Gender : </p>
                <br>
            </div>
            <div id="profileTextValue">
                <p id="profileFName">${user.firstName}</p>
                <br>
                <p id="profileLName">${user.lastName}</p>
                <br>
                <p id="profileDOB">${user.dob}</p>
                <br>
                <p id="profileEmail">${user.email}</p>
                <br>
                <p id="profileGender">${user.gender}</p>
                <br>
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
        Personal info:
        <div>
            <p id="profileInfo">${user.personalData}</p>
        </div>
    </div>
    <div id="rightBar">

    </div>
</div>

<%--include footer--%>
<%@ include file="/view/public/common/footer.jsp" %>

</body>
</html>