<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
	<head>
		<meta charset="utf-8">
		<title>Friends</title>
	</head>
	<body>

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
                <h2>Friends</h2>
                <input class="searchLine" style="width: 300px">
                <button class="searchButton"><img src="${root}/view/res/images/ic_action_search.png" width="20px"></button>
                <div>
                    <button style="width: 32%;">All friends</button>
                    <button style="width: 32%;">Online</button>
                    <button style="width: 32%;">Requests</button>
                </div>
            </div>
            <div id="rightBar">
                <p>Rightbar</p>
            </div>
        </div>

        <%--include footer--%>
        <%@ include file="/view/public/common/footer.jsp" %>

	</body>
</html>