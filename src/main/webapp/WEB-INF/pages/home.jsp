<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Welcome page</title>

    <spring:url value="/resources/css/style.css" var="styleCss" />
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapMinCss" />
    <spring:url value="/resources/css/bootstrap-theme.min.css" var="bootstrapThemeMinCss" />
    <spring:url value="/resources/css/bootstrap.css" var="bootstrapCss" />
    <spring:url value="/resources/css/navbar.css" var = "navbarCss"/>
    <spring:url value="/resources/html/navbar.html" var = "navbarHtml"/>

    <link rel="stylesheet" type="text/css" href="${styleCss}">
    <link rel="stylesheet" type="text/css" href="${bootstrapMinCss}">
    <link rel="stylesheet" type="text/css" href="${bootstrapThemeMinCss}">
    <link rel="stylesheet" type="text/css" href="${bootstrapCss}">
    <link rel="stylesheet" type="text/css" href="${navbarCss}">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
 
</head>
<body>

<c:import url = "/resources/html/navbar.jsp"/>

<div class="container">
    <input type="hidden" id="user-id" value="${user.id}"/>
    <input type="hidden" id="user-first-name" value="${user.firstName}"/>
    <input type="hidden" id="user-last-name" value="${user.lastName}"/>

    <div class="row profile">

        <div class="col-md-3">
            <div class="profile-sidebar">
                <!-- SIDEBAR USERPIC -->
                <div class="profile-userpic">
                    <img src="/socNet/resources/usersImages/${user.id}.png" class="img-responsive" alt="">
                </div>
                <!-- END SIDEBAR USERPIC -->
                <!-- SIDEBAR USER TITLE -->
                <div class="profile-usertitle">
                    <div class="profile-usertitle-name">
                       	<p id = "user-fullname"></p>
                    </div>
                </div>
                <!-- END SIDEBAR USER TITLE -->
                <!-- SIDEBAR BUTTONS -->
                <div class="profile-userbuttons">
          <%-- <button id = "add-to-friends-button" type="button" class="btn btn-success btn-sm">
                    	Add to friends
                    </button>--%>
                    <a href="http://localhost:8080/socNet/followers" id = "" type="button" class="btn btn-success btn-sm">
                    	Followers
                    </a>
                    <button id = "message-button" type="button" class="btn btn-danger btn-sm">Message</button>
                </div>
                <!-- END SIDEBAR BUTTONS -->
                <!-- SIDEBAR MENU -->
                <div class="profile-usermenu">
                    <ul class="nav">
                        <li class="active">
                            <a href="#">
                                <i class="glyphicon glyphicon-home"></i>
                                Overview </a>
                        </li>
                       
                    </ul>
                </div>
                <!-- END MENU -->
            </div>
        </div>

        <div class="col-md-9">
            <div class="profile-content">
                <hr/>

                <div class="row">
                    <div class="col-sm-12">
                        <h1>Posts</h1>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-12">
                        <div class="widget-area no-padding blank">

                            <div class="status-upload">
                                <form id="post-form">
                                    <input type="text" id="form-post-title" placeholder="Title"/>
                                    <textarea id="form-post-text" placeholder="What are you doing right now?" ></textarea>
                                    <button type="submit" class="btn btn-success green"><i class="fa fa-share"></i> Share</button>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>

                <br/>

                <div id="posts">

                </div>

            </div>

        </div>

    </div>
</div>

<br>
<br>

<c:import url="/resources/html/post.template.html"/>
<c:import url="/resources/html/comment.template.html"/>

<script type="text/javascript" src="<c:url value='/resources/js/post.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/user.js' />"></script>

</body>
</html>
