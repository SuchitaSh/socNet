<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Post page</title>

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

</head>
<body>

<c:import url = "/resources/html/navbar.html"/>

<div class="container">
    <input type="hidden" id="user-id" value="${user.id}"/>
    <div class="row profile">

        <div class="col-md-3">
            <div class="profile-sidebar">
                <div class="profile-usertitle">
                    <div class="profile-usertitle-name">
                        Author
                    </div>
                </div>
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
                </div>
                <!-- END SIDEBAR USER TITLE -->
                <!-- SIDEBAR BUTTONS -->
                <div class="profile-userbuttons">
                    <button type="button" class="btn btn-success btn-sm">Messages</button>
                    <button type="button" class="btn btn-danger btn-sm">Followers</button>
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

                <div >

                    <h2>${post.title}</h2>
                    <br>
                    <p>${post.text}</p>
                    <br>
                    <p>Published: <i>${post.postingDate}</i></p>

                </div>

                <hr/>

                <div class="row">
                    <div class="col-sm-12">
                        <h2>Comments</h2>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-12">
                        <div class="widget-area no-padding blank">

                            <div class="status-upload">
                                <form id="post-form">
                                    <textarea id="form-post-text" placeholder="Add comment here" ></textarea>
                                    <button type="submit" class="btn btn-success green"><i class="fa fa-share"></i> Comment</button>
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

</body>
</html>
