<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
	<head>
		<meta charset="utf-8">
		<title>Service</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
        <script src="${root}/view/res/js/load/REST_client.js"></script>
        <script>
//            $(document).ready(function() {
                $(function() {
                    $('body').load(function (e) {
                        alert("See");
                        document.getElementById('centralBar').innerHTML = '<h2>Shops</h2>' +
                        '<div id="resultShopList"></div>';
                        ShopsByCriterion();
                    })
                });
                $(function() {
                    $('#submitSearch').click(function (e) {
                        document.getElementById('centralBar').innerHTML = '<h2>Shops</h2>' +
                        '<div id="resultShopList"></div>';
                        ShopsByCriterion();
                    })
                });

                function test() {
                    document.getElementById('centralBar').innerHTML = '<h2>Shops</h2>' +
                    '<div id="resultShopList"></div>';
                    ShopsByCriterion();
                }

//            });
        </script>
        
	</head>
	<body onload="test();">

        <%--include header--%>
        <%@ include file="/view/public/common/header.jsp" %>

        <div id="content">
            <div id="leftBar">
                <h2>Search</h2>
                <br>
                <p>Name</p>
                <form>
                    <p><input id="searchName" type="text" placeholder="Enter name of shop"></p>
                    <p>Country</p>
                    <p><input id="searchCountry" type="text" placeholder="Enter country of shop"></p>
                    <p>City</p>
                    <p><input id="searchCity" type="text" placeholder="Enter city of shop"></p>
                    <p>Street</p>
                    <p><input id="searchStreet" type="text" placeholder="Enter street of shop"></p>
                    <p>Building</p>
                    <p><input id="searchBuilding" type="text" placeholder="Enter building of shop"></p>
                    <p>Rating</p>
                    <p class="smallText">
                        From<input id="searchMinRating" class="rating" type="text" placeholder="0">
                        To<input id="searchMaxRating" class="rating" type="text" placeholder="5">
                    </p>
                    <button id="submitSearch" type="button" ><p>Send</p></button>
                </form>
            </div>
            <div id="centralBar">
                <h2>Shops</h2>
                <%--<script>uploadShops();</script>--%>
                <div id="resultShopList" ></div>
            </div>
            <div id="rightBar">
            </div>
        </div>

        <%--include footer--%>
        <%@ include file="/view/public/common/footer.jsp" %>

	</body>
</html>