<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<%--include footer--%>
<%@ include file="/view/public/common/header.jsp" %>
    <div id="content">
        <div id="leftBar" style="visibility: hidden">
            <p>Leftbar</p>
        </div>
        <div id="centralBar" >
            <p style="text-align: center;">Please, read rules before you sign up!</p>
                <p>You are...</p><br>
                <a href="${root}/view/public/register_user.jsp">User</a><br>
                <a href="${root}/view/public/register_admin.jsp">Shop Administrator</a><br>
                <a href="${root}/view/public/register_manager.jsp">Manager</a><br>
        </div>
        <div id="rightBar" style="visibility: hidden">

        </div>
    </div>
<%--include footer--%>
<%@ include file="/view/public/common/footer.jsp" %>

</body>
</html>
