<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url  value='/resources/css/lib/toastr.css' />" rel="stylesheet">

<script src="<c:url value='/resources/js/lib/sockjs.js' />"></script>
<script src="<c:url value='/resources/js/lib/toastr.js' />"></script>
<script src="<c:url value='/resources/js/lib/stomp.js' />"></script>
<script src="<c:url value='/resources/js/subscribe.js' />"></script>
<script src="<c:url value='/resources/js/navbar.js' />"></script>

<div class="container">
<nav class="navbar navbar-default">
<div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">SocNet</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="/home">Home</a></li>
      <li><a href="/friends">Friends</a></li>
      <li><a href="/followers">Followers</a></li>
      <li ><a href="/all-users">All Users</a></li>
      <li><a id = "dialogs" href="/dialogs">Messages</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
     	<li><a href="/settings">Settings</a></li>
 		<li><a href="/logout">Logout</a></li>
			
			</ul>
  </div>
</nav>
  </div>
  


