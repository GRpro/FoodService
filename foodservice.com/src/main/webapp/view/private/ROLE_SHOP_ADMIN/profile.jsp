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
    <%--<script>--%>
        <%--function onLoadBody() {--%>
            <%--managerID = <%= ((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()%>--%>
                    <%--shops = ShopsByShopAdminUserID(managerID);--%>
            <%--ShopAdminUserByID(managerID);--%>
            <%--ManagerUsersGetByShopAdminID(managerID);--%>
        <%--}--%>

    <%--</script>--%>
</head>
<body id="body" >

<%--include header--%>
<%@ include file="/view/public/common/header.jsp" %>

<div id="content">
    <div id="leftBar">
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
    <div id="centralBar" style="width: 376px">
        <h2><p>Profile</p></h2>
        <br>
        <div id="profileImage">
            <img height="150px" src="${root}/content/photo/${user.photoId}"/>
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
                <p id="profileFName">${user.firstName}</p>
                <br>
                <p id="profileLName">${user.lastName}</p>
                <br>
                <p id="profileEmail">${user.email}</p>
                <br>
                <p id="profileInfo">${user.personalData}</p>
            </div>

        </div>
    </div>
    <div class="rightBar">
        <h2 style="text-align: center">managers</h2>
        <br>
        <div id="managerContainer">
            <c:forEach items="${managers}" var="manager">
                <div id="managerLeft">
                    <div class="shopText">
                        <a href="" class="shopRightName">${manager.firstName} ${manager.lastName}</a>
                        <p class="smallText">${manager.systemStatus}</p></div>
                    <div class="shopPhoto">
                        <img id="thumbnailManager" src="${root}/content/photo/${manager.photoId}" width="40px" height="40px">
                        </div></div>
            </c:forEach>
        </div>
    </div>
    <div class="rightBar">
        <h2 style="text-align: center;">shops</h2>
        <br>
        <div id="shopContainer">
            <c:forEach items="${shops}" var="shop">
                <div id="shopRight">
                    <div class="shopText">
                        <a href="${root}" class="shopRightName">${shop.name}</a>
                        <p class="smallText">${shop.location.region} ${shop.location.city} ${shop.location.street} ${shop.location.building}</p>
                    </div>
                    <div class="shopPhoto">
                        <img id="thumbnailShop" src="${root}/content/photo/${shop.primaryPhotoId}" width="40px" height="40px">

                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<%--include footer--%>
<%@ include file="/view/public/common/footer.jsp" %>

</body>
</html>