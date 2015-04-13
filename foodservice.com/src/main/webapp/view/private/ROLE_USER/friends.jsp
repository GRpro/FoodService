<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page session="true"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
	<head>
		<meta charset="utf-8">
		<title>Friends</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
        <script src="${root}/view/res/js/client/simpleUserClient.js"></script>
        <link href="${root}/view/res/style.css" rel="stylesheet" type="text/css" media="screen" />
        <script>
            $(function() {
                $('body').load(function (e) {
                    document.getElementById('centralBar').innerHTML = '<h2>Related users</h2>' +
                    '<div id="resultUserList"></div>';
                    getRelatedUsers('<sec:authentication property="principal.id" />', '${webServiceRootUrl}', '${root}');
                })
            });
            $(function() {
                $('#submitSearch').click(function (e) {
                    document.getElementById('centralBar').innerHTML = '<h2>Related users</h2>' +
                    '<div id="resultUserList"></div>';
                    getRelatedUsers('<sec:authentication property="principal.id" />', '${webServiceRootUrl}', '${root}');
                })
            });

            function test() {
                document.getElementById('centralBar').innerHTML = '<h2>Related users</h2>' +
                '<div id="resultUserList"></div>';
                getRelatedUsers('<sec:authentication property="principal.id" />', '${webServiceRootUrl}', '${root}');
            }
        </script>
	</head>
	<body id="body">

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
                <div id="resultUserList" ></div>
                <%--<h2>Friends</h2>--%>
                <%--<input class="searchLine" style="width: 300px">--%>
                <%--<button class="searchButton"><img src="${root}/view/res/images/ic_action_search.png" width="20px"></button>--%>
                <%--<div>--%>
                    <%--<button style="width: 32%;">All friends</button>--%>
                    <%--<button style="width: 32%;">Online</button>--%>
                    <%--<button style="width: 32%;">Requests</button>--%>
                <%--</div>--%>
            </div>
            <div class="rightBar" >
                <p>Find users</p>

                <form>
                    <p>
                        <p><input type="radio" style="width: 20px;" name="relationKind" value="friendsOf" checked="checked">Friends</p>
                        <p><input type="radio" style="width: 20px;" name="relationKind" value="followersOf">Followers</p>
                        <p><input type="radio" style="width: 20px;" name="relationKind" value="requestedTo">Requests</p>
                        <p></p>
                        <p><input type="radio" style="width: 20px;" name="relationKind" value="followedBy">My subscribes</p>
                        <p><input type="radio" style="width: 20px;" name="relationKind" value="requestedBy">My requests</p>
                    </p>

                    <div class="searchText">
                        <p>FN</p>
                        <p>LN</p>
                        <p>Age</p>
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
                        <button id="submitSearch" type="button" ><p>Send</p></button>
                    </div>
                </form>
            </div>
        </div>

        <%--include footer--%>
        <%@ include file="/view/public/common/footer.jsp" %>

	</body>
</html>