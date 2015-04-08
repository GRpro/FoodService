<%@ page import="com.foodservice.security.CustomUserDetails" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
<%@page session="true"%>
<head>
    <meta charset="utf-8">
    <title>ShopAdmin profile</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
    <script src="${root}/view/res/js/load/REST_client.js"></script>
    <script>
        function onLoadBody() {
            managerID = <%= ((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()%>
                    shops = ShopsByShopAdminUserID(managerID);
            ShopAdminUserByID(managerID);
            ManagerUsersGetByShopAdminID(managerID);
        }

    </script>
</head>
<body id="body" onload="onLoadBody()">

<%--include header--%>
<%@ include file="/view/public/common/header.jsp" %>

<div id="content">
    <div id="leftBar">
        <h2 style="text-align: center">Your managers</h2>
        <br>
        <div id="managerContainer">
        </div>
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
            <div id="profileMenu">
                <form>
                    <button formaction="${root}/form/shop" class="imageButton">
                        <img src="${root}/view/res/images/ic_store_white_48dp1.png" width="35px"/>
                    </button>
                    <button formaction="${root}/view/private/messages.jsp" class="imageButton">
                        <img src="${root}/view/res/images/ic_action_mail.png" width="35px"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
    <div id="rightBar">
        <h2 style="text-align: center;">Your shops</h2>
        <br>
        <div id="shopContainer">
        </div>
    </div>
</div>

<%--include footer--%>
<%@ include file="/view/public/common/footer.jsp" %>

</body>
</html>