<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<%--include header--%>
<%@ include file="/view/public/common/header.jsp" %>

<div id="content">
    <div id="leftBar">
    </div>
    <div id="centralBar">
        <h2>${message}</h2>
    </div>
    <div id="rightBar">
    </div>
</div>

<%--include footer--%>
<%@ include file="/view/public/common/footer.jsp" %>

</body>
</html>
