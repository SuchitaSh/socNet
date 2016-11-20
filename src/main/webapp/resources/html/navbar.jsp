<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
            <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet">    
    <script src="//cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
 <script type="text/javascript" src="<c:url value='/resources/js/stomp.js' />"></script>
	 <script type="text/javascript" src="<c:url value='/resources/js/subscribe.js' />"></script>

	
 <div class="container">
<nav class="navbar navbar-default">
<div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">SocNet</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="/home">Home</a></li>
      <li><a href="/friends">Friends</a></li>
      <li><a href="/all-users">All Users</a></li>
      <li><a href="/dialogs">Messages</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
     	<li><a href="/settings">Settings</a></li>
 		<li><a href="/logout">Logout</a></li>
			
			</ul>
  </div>
</nav>
  </div>
  


