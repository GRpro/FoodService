<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
	<head>
		<meta charset="utf-8">
		<title>Messages</title>
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
                <h2>Messages</h2>
            </div>
            <div id="rightBar">
                <p>Rightbar</p>
            </div>
        </div>

        <%--include footer--%>
        <%@ include file="/view/public/common/footer.jsp" %>

	</body>
</html>