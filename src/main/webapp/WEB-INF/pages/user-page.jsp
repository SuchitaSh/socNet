 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Welcome page</title>

    <spring:url value="/resources/css/style.css" var="styleCss" />
    <spring:url value="/resources/css/bootstrap-theme.min.css" var="bootstrapThemeMinCss" />
    <spring:url value="/resources/css/bootstrap.css" var="bootstrapCss" />
    <spring:url value="/resources/css/navbar.css" var = "navbarCss"/>
    <spring:url value="/resources/html/navbar.html" var = "navbarHtml"/>
    <spring:url value="/resources/js/lib/util.js" var = "utilJs"/>
    <spring:url value="/resources/js/lib/jquery.js" var = "jqueryJs"/>

    <link rel="stylesheet" type="text/css" href="${styleCss}">
    <link rel="stylesheet" type="text/css" href="${bootstrapThemeMinCss}">
    <link rel="stylesheet" type="text/css" href="${bootstrapCss}">
    <link rel="stylesheet" type="text/css" href="${navbarCss}">

    <script src="${jqueryJs}"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/user.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/lib/modal.js' />"></script>
    <script type="text/javascript" src="${utilJs}"></script>
</head>
<body>


<c:import url = "/resources/html/navbar.jsp"/>

<!-- Values passed to javascript -->
<input type="hidden" id="user-id" value="${user.id}"/>
<input type="hidden" id="current-user-id" value="${currentUser.id}"/>
<input type="hidden" id="current-user-first-name" value="${currentUser.firstName}"/>
<input type="hidden" id="current-user-last-name" value="${currentUser.lastName}"/>
<input type="hidden" id="current-user-username" value="${currentUser.username}"/>

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
                    </div>
                </div>
                <!-- END SIDEBAR USER TITLE -->
                <!-- SIDEBAR BUTTONS -->
           
                <div class="profile-userbuttons">

                    <c:if test="${not isCurrent}">

                        <c:if test="${follower}">
                            <button id="follow-button" type="button" class="btn btn-success btn-sm"
                                    data-following="${following}"
                                    data-following-message="In your friends list" data-not-following-message="Add to friends"
                                    data-hover-message="Remove from friends">Add to friends</button>
                        </c:if>

                        <c:if test="${!follower}">
                            <button id = "follow-button" type="button" class="btn btn-success btn-sm"
                                    data-following="${following}"
                                    data-following-message="Request sent" data-not-following-message="Follow"
                                    data-hover-message="Cancel request">Follow</button>
                        </c:if>
                        <button id = "message-button" type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal">
                            <span class="glyphicon glyphicon-envelope fix-glyphicon-button"> </span>
                        </button>

                    </c:if>


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
                                    <input type="text" id="form-post-title" placeholder="Title" <c:if test="${!friend}">disabled</c:if> />
                                    <textarea id="form-post-text" placeholder="What are you doing right now?" <c:if test="${!friend}">disabled</c:if> ></textarea>
                                    <button <c:if test="${!friend}">disabled</c:if> type="submit" class="btn btn-success green"><i class="fa fa-share"></i> Share</button>
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

<div class="modal fade" id="modal-remove-post">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
                Do you really want to delete that post?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" id="confirm-remove" class="btn btn-danger" data-dismiss="modal">Confirm</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-remove-failed">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
                Failed to remove post
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>