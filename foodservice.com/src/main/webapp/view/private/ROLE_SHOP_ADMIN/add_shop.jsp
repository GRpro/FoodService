<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
<%--<c:set var="managerUserId" value="<%=((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof CustomUserDetails %>"/>--%>
<head>
    <meta charset="utf-8">
    <title>New shop</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
    <script src="${root}/view/res/js/load/REST_client.js"></script>
    <script>
        $(function() {
            $('#submitSearchShop').click(function (e) {
                ShopCreate('<sec:authentication property="principal.id" />');
            })
        })
    </script>
</head>
<body>

<%--include header--%>
<%@ include file="/view/public/common/header.jsp" %>

<div id="content">
    <div id="leftBar">
        <p>Leftbar</p>
    </div>
    <div id="centralBar">
        <h2>Add shop</h2>
        <br>
        <form action="${root}/register/shop"  method="post" enctype="multipart/form-data">
            <p>Shop name</p>
            <p><input id="shopName" name="name" type="text" placeholder="Your shop name" required></p>
            <p>Country</p>
            <p><input id="country" name="location.country" type="text" placeholder="Country of your shop" required></p>
            <p>City</p>
            <p><input id="city" name="location.city" type="text"  placeholder="City of your shop" required></p>
            <p>Street</p>
            <p><input id="street" name="location.street" type="text" placeholder="Street of your shop" required></p>
            <p>Building</p>
            <p><input id="building" name="location.building" type="text" placeholder="Building of your shop" required></p>
            <p>Other location info</p>
            <p><input id="otherInfo" name="location.otherInfo" type="text" placeholder="Building of your shop" required></p>
            <p>Primary photo</p>
            <p><input id="shopPhoto" name="primaryPhoto" type="file" accept=".jpeg, .jpg"></p><br>
            <p>Description</p>
            <p><input id="description" name="description" type="text" placeholder="Description of your shop"></p>

            <input type="hidden" name="shopAdminUserId" value="<sec:authentication property="principal.id" />">
            <input id="submitShopForm" type="submit" value="Register">
        </form>
    </div>
    <div id="rightBar">
        <p>Rightbar</p>
    </div>
</div>

<%--include footer--%>
<%@ include file="/view/public/common/footer.jsp" %>

</body>
</html>