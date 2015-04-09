<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
<head>
    <meta charset="utf-8">
    <title>Service</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
    <script src="${root}/view/res/js/client/userClient.js"></script>
    <link href="${root}/view/res/style.css" rel="stylesheet" type="text/css" media="screen" />
    <script>
        $(function() {
            $('body').load(function (e) {
                alert("See");
                document.getElementById('centralBar').innerHTML = '<h2>Shops</h2>' +
                '<div id="resultUserList"></div>';
                usersByCriterion('${webServiceRootUrl}', '${root}');
            })
        });
        $(function() {
            $('#submitSearch').click(function (e) {
                document.getElementById('centralBar').innerHTML = '<h2>Shops</h2>' +
                '<div id="resultUserList"></div>';
                usersByCriterion('${webServiceRootUrl}', '${root}');
            })
        });

        function test() {
            document.getElementById('centralBar').innerHTML = '<h2>Shops</h2>' +
            '<div id="resultUserList"></div>';
            usersByCriterion('${webServiceRootUrl}', '${root}');
        }
    </script>

</head>
<body onload="test();">

<%--include header--%>
<%@ include file="/view/public/common/header.jsp" %>

<div id="content">
    <div id="leftBar">
        <h2>People</h2>
        <br>
        <form>
            <div class="searchText">
                <p>FN</p>
                <p>LN</p>
                <p>Age</p>
                <p>Type</p>
                <p>Status</p>
                <p>Gender</p>
            </div>
            <div class="searchInput" style="  width: 165px;">
                <p><input id="searchFirstName" type="text" placeholder="first name"></p>
                <p><input id="searchLastName" type="text" placeholder="last name"></p>
                <p class="smallText">
                    From<input id="searchMinAge" class="rating" type="text" placeholder="0">
                    To<input id="searchMaxAge" class="rating" type="text" placeholder="100">
                </p>
                <p>
                    <p><input type="radio" style="width: 20px;" name="userType" value="simple" checked="checked"> User</p>
                    <p><input type="radio" style="width: 20px;"name="userType" value="admin"> Admin</p>
                    <p><input type="radio" style="width: 20px;"name="userType" value="manager">Manager</p>
                </p>
                <p>
                    <select id="systemStatus">
                        <option value="NONE">NONE</option>
                        <option value="ONLINE">ONLINE</option>
                        <option value="OFFLINE">OFFLINE</option>
                        <option value="AWAY">AWAY</option>
                    </select>
                </p>
                <p>
                    <select id="gender">
                        <option value="NONE">NONE</option>
                        <option value="MALE">MALE</option>
                        <option value="FEMALE">FEMALE</option>
                        <option value="UNSPECIFIED">UNSPECIFIED</option>
                    </select>
                </p>
            </div>
            <button id="submitSearch" type="button" ><p>Send</p></button>
        </form>
    </div>
    <div id="centralBar">
        <h2>Shops</h2>
        <div id="resultUserList" ></div>
    </div>
    <div id="rightBar">
    </div>
</div>

<%--include footer--%>
<%@ include file="/view/public/common/footer.jsp" %>

</body>
</html>