<%@ page import="com.foodservice.entities.data.UserType" %>
<%@ page import="com.foodservice.entities.data.State" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />

<c:set var="typeSimpleUser" value="<%=UserType.SIMPLE%>"/>
<c:set var="typeManagerUser" value="<%=UserType.MANAGER%>"/>
<c:set var="typeShopAdminUser" value="<%=UserType.SHOP_ADMIN%>"/>

<c:set var="stateNew" value="<%=State.NEW%>"/>
<c:set var="stateAccepted" value="<%=State.ACCEPTED%>"/>
<c:set var="stateRefused" value="<%=State.REFUSED%>"/>

<head>
    <meta charset="utf-8">
    <title>User profile</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
    <script src="${root}/view/res/js/client/friendshipClient.js"></script>
</head>
<body >

<%--include header--%>
<%@ include file="/view/public/common/header.jsp" %>

<div id="content">
    <div id="leftBar">
        <c:if test="${isAuthenticated}">
            <c:choose>
                <c:when test="${userDetails.userType == typeSimpleUser}">
                    <c:if test="${friendship != null}">
                        <c:choose>
                            <c:when test="${friendship.state == stateNew}">
                                <c:if test="${friendship.applicantId == userDetails.id}">
                                    <form>
                                        <button onclick="deleteRequest('${webServiceRootUrl}', '${userDetails.id}', '${relativeUserId}')" class="list">Delete request</button>
                                            <%--<button formaction="" class="list">Send message</button>--%>
                                    </form>
                                </c:if>
                                <c:if test="${friendship.acceptorId == userDetails.id}">
                                    <form>
                                        <button onclick="decideOnRequest('${webServiceRootUrl}', '${relativeUserId}', '${userDetails.id}', 'ACCEPTED')" class="list">Accept request</button>
                                        <button onclick="decideOnRequest('${webServiceRootUrl}', '${relativeUserId}', '${userDetails.id}', 'REFUSED')" class="list">Refused request</button>
                                    </form>
                                </c:if>
                            </c:when>
                            <c:when test="${friendship.state == stateAccepted}">
                                <c:if test="${friendship.applicantId == userDetails.id}">
                                    <form>
                                        <button onclick="decideOnRequest('${webServiceRootUrl}', '${userDetails.id}', '${relativeUserId}', 'REFUSED')" class="list">Remove from friends</button>
                                    </form>
                                </c:if>
                                <c:if test="${friendship.acceptorId == userDetails.id}">
                                    <form>
                                        <button onclick="decideOnRequest('${webServiceRootUrl}', '${relativeUserId}', '${userDetails.id}', 'REFUSED')" class="list">Remove from friends</button>
                                    </form>
                                </c:if>
                            </c:when>
                            <c:when test="${friendship.state == stateRefused}">
                                <c:if test="${friendship.applicantId == userDetails.id}">
                                    <form>
                                        <button onclick="deleteRequest('${webServiceRootUrl}', '${userDetails.id}', '${relativeUserId}')" class="list">Unfollow</button>
                                    </form>
                                </c:if>
                                <c:if test="${friendship.acceptorId == userDetails.id}">
                                    <form>
                                        <button onclick="decideOnRequest('${webServiceRootUrl}', '${relativeUserId}', '${userDetails.id}', 'ACCEPTED')" class="list">Set as friend</button>
                                    </form>
                                </c:if>
                            </c:when>

                        </c:choose>
                    </c:if>
                    <c:if test="${friendship == null}">
                        <form>
                            <button onclick="sendRequest('${webServiceRootUrl}', '${userDetails.id}', '${relativeUserId}')" class="list">Add to friends</button>
                                <%--<button formaction="" class="list">Send message</button>--%>
                        </form>
                    </c:if>
                </c:when>
                <c:when test="${userDetails.userType == typeManagerUser}">

                </c:when>
                <c:when test="${userDetails.userType == typeShopAdminUser}">

                </c:when>
                <c:otherwise>

                </c:otherwise>
            </c:choose>
        </c:if>
    </div>
    <div id="centralBar">
        <h2><p>Profile</p></h2>
        <br>
        <div id="profileImage">
            <img height="150px" width="150px" src="${root}/content/photo/${simpleUser.photoId}"/>
        </div>
        <div id="profileText">
            <div id="profileTextKey">
                <p>First name : </p>
                <br>
                <p>Last name : </p>
                <br>
                <p>DOB : </p>
                <br>
                <p>E-mail : </p>
                <br>
                <p>Gender : </p>
                <br>
            </div>
            <div id="profileTextValue">
                <p id="profileFName">${simpleUser.firstName}</p>
                <br>
                <p id="profileLName">${simpleUser.lastName}</p>
                <br>
                <p id="profileDOB">${simpleUser.dob}</p>
                <br>
                <p id="profileEmail">${simpleUser.email}</p>
                <br>
                <p id="profileGender">${simpleUser.gender}</p>
                <br>
            </div>
            <%--<div id="profileMenu">--%>
            <%--<form>--%>
            <%--<button formaction="${root}/view/private/friends.jsp" class="imageButton" width="35px">--%>
            <%--<img src="${root}/view/res/images/ic_action_user.png"/>--%>
            <%--</button>--%>
            <%--<button formaction="${root}/view/private/messages.jsp" class="imageButton" width="35px">--%>
            <%--<img src="${root}/view/res/images/ic_action_mail.png"/>--%>
            <%--</button>--%>
            <%--</form>--%>
            <%--</div>--%>
        </div>
        Personal info:
        <div>
            <p id="profileInfo">${simpleUser.personalData}</p>
        </div>
    </div>
    <div id="rightBar">
        <%--<h2>Friends</h2>--%>
        <%--<br>--%>
        <%--<input class="searchLine" style="width: 89%">--%>
        <%--<button class="searchButton"><img src="${root}/view/res/images/ic_action_search.png" width="20px"></button>--%>
        <%--<div style="text-align: center;">--%>
            <%--<button style="width: 32%;">All friends</button>--%>
            <%--<button style="width: 32%;">Online</button>--%>
            <%--<button style="width: 32%;">Requests</button>--%>
        <%--</div>--%>
    </div>
</div>

<%--include footer--%>
<%@ include file="/view/public/common/footer.jsp" %>

</body>
</html>