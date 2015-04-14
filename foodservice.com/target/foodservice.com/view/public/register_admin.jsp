<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="root" value="${pageContext.request.contextPath}" />
	<head>
		<meta charset="utf-8">
		<title>Register manager</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
        <script src="${root}/view/res/js/load/REST_client.js"></script>
    </head>
	<body>
        
        <%--include header--%>
        <%@ include file="/view/public/common/header.jsp" %>
        
        <div id="content">   
            <div id="leftbar">
            </div>
            <div id="centralbar">
                <h2>Sign up as shop administrator</h2>
                <br>
                <form action="${root}/register/shopAdmin"  method="post" enctype="multipart/form-data">

                    <label for="firstName">First name</label>
                    <p><input id="firstName" name="firstName" type="text" placeholder="Enter your name" required></p>

                    <label for="lastName" >Last name</label>
                    <p><input id="lastName" name="lastName" type="text" placeholder="Enter your last name" required></p>

                    <label for="email">E-mail</label>
                    <p><input id="email" name="email" type="email" placeholder="Enter your e-mail" required></p>

                    <label for="password">Password</label>
                    <p><input id="password" name="password" onchange="checkPasswords()" type="password" placeholder="Enter password" required></p>
                    <p><input id="passwordconfirm" onchange="checkPasswords()" type="password" placeholder="Confirm password" required></p>

                    <label for="personalData">About you</label>
                    <p><input id="personalData" name="personalData" type="text"  placeholder="Tell some words about yourself"></p><br>

                    <label for="gender">Gender</label>
                    <p>
                        <select id="gender" name="gender">
                            <option>MALE</option>
                            <option>FEMALE</option>
                            <option>UNSPECIFIED</option>
                        </select>
                    </p>

                    <label for="dob">Date of birth</label>
                    <p><input type="date" name="dobDate" id="dob"></p>

                    <label for="userAvatar">Create profile photo</label>
                    <p><input id="userAvatar" name="userAvatar" type="file" accept=".jpeg, .jpg"></p><br>

                    <input id="submitShopAdminForm" type="submit" value="Send">
                </form>
            </div>
            <div id="rightbar">
            </div>
        </div>

        <%--include footer--%>
        <%@ include file="/view/public/common/footer.jsp" %>

	</body>
</html>