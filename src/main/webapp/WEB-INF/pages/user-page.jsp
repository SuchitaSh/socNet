<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Welcome page</title>

    <spring:url value="/resources/css/style.css" var="styleCss" />
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapMinCss" />
    <spring:url value="/resources/css/bootstrap-theme.min.css" var="bootstrapThemeMinCss" />
    <spring:url value="/resources/css/bootstrap.css" var="bootstrapCss" />
    <spring:url value="resources/css/navbar.css" var = "navbarCss"/>
    <spring:url value="/resources/html/navbar.html" var = "navbarHtml"/>

    <link rel="stylesheet" type="text/css" href="${styleCss}">
    <link rel="stylesheet" type="text/css" href="${bootstrapMinCss}">
    <link rel="stylesheet" type="text/css" href="${bootstrapThemeMinCss}">
    <link rel="stylesheet" type="text/css" href="${bootstrapCss}">
    <link rel="stylesheet" type="text/css" href="${navbarCss}">



    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/bootstrap.min.js' />"></script>



</head>
<body>

<c:import url = "/resources/html/navbar.html"/>
<input type="hidden" id="user-id" value="${user.id}"/>
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
                    <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal">Message</button>
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
                <div class="post-form">
                    <form method="post" action="addPost" id="postForm">
                        <input id="title" type="text" name="title" placeholder="Enter title of your post" <c:if test="${!friend }">disabled</c:if>  >
                        <br>
                        <textarea <c:if test="${!friend }">disabled</c:if> id="text"  name="text" rows="5" cols="100"></textarea>
                        <input <c:if test="${!friend }">disabled</c:if> id="create-button" type="submit" value="Post">
                    </form>
                </div>

                <hr/>

                <div class="row">
                    <div class="col-sm-12">
                        <h1>Posts</h1>
                    </div>
                </div>

                <div id="posts">

                </div>

            </div>

        </div>

    </div>
</div>

<br>
<br>

<script type="template" data-template-name="post">
    <div class="row post">

        <div class="col-sm-1 thumbnail-container">
            <div class="thumbnail">
                <img class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
            </div>
        </div>

        <div class="col-sm-11">
            <div class="panel panel-default">
                <div class="panel-heading clearfix">
                    <strong class="placeholder-title pull-left">John Doe</strong>
                    <div class="pull-right placeholder-author"></div>
                    <span class="text-muted placeholder-time pull-left"></span>
                </div>

                <div class="panel-body placeholder-post">
                    Post Placeholder
                </div>

            </div>
        </div>

    </div>

</script>

<script type="template" data-template-name="comment">
    <div class="panel-footer clearfix comment" data-template="comment">
        <img class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
        <div class="col-sm-10 placeholder-comment">
            Comment Placeholder
        </div>
    </div>
</script>


<script type="text/javascript" src="<c:url value='/resources/js/post.js' />"></script>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
               <textarea id="message" name="message" rows="5" cols="80"></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" id="send" class="btn btn-primary">Send</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
