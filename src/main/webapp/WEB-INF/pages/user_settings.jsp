<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Welcome page</title>

    <spring:url value="/resources/css/style.css" var="styleCss" />
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapMinCss" />
    <spring:url value="/resources/css/bootstrap-theme.min.css" var="bootstrapThemeMinCss" />
    <spring:url value="/resources/css/bootstrap.css" var="bootstrapCss" />
	<spring:url value="resource/css/navbar.css" var = "navbarCss"/>
    <spring:url value="/resources/html/navbar.html" var = "navbarHtml"/>
	
	
    <link rel="stylesheet" type="text/css" href="${styleCss}">
    <link rel="stylesheet" type="text/css" href="${bootstrapMinCss}">
    <link rel="stylesheet" type="text/css" href="${bootstrapThemeMinCss}">
    <link rel="stylesheet" type="text/css" href="${bootstrapCss}">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/post.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/subscribe.js' />"></script>
	
</head>
<body>

<c:import url = "/resources/html/navbar.jsp"/>


<div class="container">
    <div class="row profile">

        <div class="col-md-3">
            <div class="profile-sidebar">
                <!-- SIDEBAR USERPIC -->
                <div class="profile-userpic">

                </div>
                <!-- END SIDEBAR USERPIC -->
                <!-- SIDEBAR USER TITLE -->

                <!-- END SIDEBAR USER TITLE -->

                <!-- SIDEBAR MENU -->
                <div class="profile-usermenu">
                    <ul class="nav">
                        <li class="active">
                            <a href="#">
                                <i class="glyphicon glyphicon-home"></i>
                                Overview </a>
                        </li>
                        <li>
                            <a href="logout">
                                <!--<i class="glyphicon glyphicon-flag"></i>-->
                                Logout </a>
                        </li>
                        <li>
                            <a href="settings">
                                <!--<i class="glyphicon glyphicon-flag"></i>-->
                                Settings </a>
                        </li>
                    </ul>
                </div>
                <!-- END MENU -->
            </div>
        </div>

        <div class="col-md-9">
            <div class="profile-content">

<div style="margin: auto">

                <form action="change-information" method="post" enctype="multipart/form-data">
                    <input type="text" name="name" value="${user.firstName}">
                    <br>
                    <br>
                    <input type="text" name="surname" value="${user.lastName}">
                    <br>
                    <br>
                    <input type="file" name="photo">
                    <br>
                    <br>
                    <input type="submit" value="OK">
                </form>
                <hr/>
</div>
            </div>
        </div>



    </div>
</div>
<br>
<br>
</body>
</html>


