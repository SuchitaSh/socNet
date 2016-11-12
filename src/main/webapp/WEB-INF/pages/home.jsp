<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Welcome page</title>

    <spring:url value="/resources/css/style.css" var="styleCss" />
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapMinCss" />
    <spring:url value="/resources/css/bootstrap-theme.min.css" var="bootstrapThemeMinCss" />
    <spring:url value="/resources/css/bootstrap.css" var="bootstrapCss" />

    <link rel="stylesheet" type="text/css" href="${styleCss}">
    <link rel="stylesheet" type="text/css" href="${bootstrapMinCss}">
    <link rel="stylesheet" type="text/css" href="${bootstrapThemeMinCss}">
    <link rel="stylesheet" type="text/css" href="${bootstrapCss}">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/post.js' />"></script>

</head>
<body>
<div class="container">
    <div class="row profile">

        <div class="col-md-3">
            <div class="profile-sidebar">
                <!-- SIDEBAR USERPIC -->
                <div class="profile-userpic">
                    <img src="/resources/usersImages/${user.id}.png" class="img-responsive" alt="">
                </div>
                <!-- END SIDEBAR USERPIC -->
                <!-- SIDEBAR USER TITLE -->
                <div class="profile-usertitle">
                    <div class="profile-usertitle-name">
                        <c:out value="${user.firstName} ${user.lastName}" />
                    </div>
                    <div class="profile-usertitle-job">
                        Developer
                    </div>
                </div>
                <!-- END SIDEBAR USER TITLE -->
                <!-- SIDEBAR BUTTONS -->
                <div class="profile-userbuttons">
                    <button type="button" class="btn btn-success btn-sm">Follow</button>
                    <button type="button" class="btn btn-danger btn-sm">Message</button>
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
                <div class="post-form">
                   <form method="post" action="addPost" id="postForm">
                       <input id="title" type="text" name="title" placeholder="Enter title of your post">
                       <br>
                       <textarea id="text"  name="text" rows="5" cols="100"></textarea>
                       <input id="create-button" type="submit" value="Post">
                   </form>
                </div>

                <div class="wall-posts">
                    <c:forEach items="${posts}" var="post">
                        <h2>${post.title}</h2>
                        <br>
                        <p>${post.text}</p>
                        <br>
                        <p>${post.postingDate}</p>
                    </c:forEach>
                </div>

                <hr/>

                <div class="row">
                    <div class="col-sm-12">
                        <h1>Posts</h1>
                    </div>
                </div>

                <div class="row comment">
                    <div class="col-sm-1 thumbnail-container">
                        <div class="thumbnail">
                            <img class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
                        </div>
                    </div>

                    <div class="col-sm-11">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <strong>myusername</strong> <span class="text-muted">posted 5 days ago</span>
                            </div>

                            <div class="panel-body">
                                Panel content
                            </div>

                            <div class="panel-footer clearfix">
                                <img class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
                                <div class="col-sm-10">
                                    Comment 1
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
            </div>



    </div>
</div>
<br>
<br>
</body>
</html>
