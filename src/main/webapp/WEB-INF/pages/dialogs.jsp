<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dialogs</title>

 <spring:url value="/resources/css/style.css" var="styleCss" />
 <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapMinCss" />
 <spring:url value="/resources/css/bootstrap-theme.min.css" var="bootstrapThemeMinCss" />
 <spring:url value="/resources/css/bootstrap.css" var="bootstrapCss" />
 <spring:url value="/resource/css/navbar.css" var = "navbarCss"/>
 <spring:url value="/resources/html/navbar.html" var = "navbarHtml"/>

  <link rel="stylesheet" type="text/css" href="${styleCss}">
  <link rel="stylesheet" type="text/css" href="${bootstrapMinCss}">
  <link rel="stylesheet" type="text/css" href="${bootstrapThemeMinCss}">
  <link rel="stylesheet" type="text/css" href="${bootstrapCss}">
  <link rel="stylesheet" type="text/css" href="${navbarCss}">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/dialogs.css'/>">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/mustache.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/dialogs.js' />"></script>
	


<script id="current-user-message" type="text/template">
      <li class="left clearfix admin_chat">
                     <span class="chat-img1 pull-right">
                     <img src="/resources/usersImages/${user.id}.png" class="img-circle">
                     </span>
                     <div class="chat-body1 clearfix">
                        <p>{{message}}</p>
						<div class="chat_time pull-left">09:40PM</div>
                     </div>
</script>

<script id="another-user-message" type="text/template">
      <li class="left clearfix admin_chat">
                     <span class="chat-img1 pull-left">
                     <img src="/resources/usersImages/{{imageId}}.png" class="img-circle">
                     </span>
                     <div class="chat-body1 clearfix">
                        <p>{{message}}</p>
						<div class="chat_time pull-left">09:40PM</div>
                     </div>
</script>

  
</head>
<body>

	<c:import url = "/resources/html/navbar.jsp"/>


<div class="main_section">
   <div class="container">
      <div class="chat_container">
         <div class="col-sm-3 chat_sidebar">
    	 <div class="row">
            <div id="custom-search-input">
               <div class="input-group col-md-12">
                  <input type="text" class="  search-query form-control" placeholder="Conversation" />
                  <button class="btn btn-danger" type="button">
                  <span class=" glyphicon glyphicon-search"></span>
                  </button>
               </div>
            </div>
            <div class="dropdown all_conversation">
               <button class="dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
               <i class="fa fa-weixin" aria-hidden="true"></i>
               All Conversations
               <span class="caret pull-right"></span>
               </button>
               <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                  <li><a href="#"> All Conversation </a>  
                  </li>
                  </ul>
                 </div>
            <div class="member_list">
               <ul class="list-unstyled">
              <c:forEach var = "friend" items = "${friends}"><li class="left clearfix">
              		<c:if test="friend.username eq userPicked">
              		<p id = "id" hidden>friend.id</p>
              		</c:if>
              	     <span class="chat-img pull-left">
                     <img src="/resources/usersImages/${friend.id}.png" class="img-circle">
                     </span> 
                      <div class="chat-body clearfix" onclick="window.location = '/dialogs/${friend.username}'"> 
                        <div class="header_sec">
                           <strong class="primary-font">${friend.firstName} ${friend.lastName}</strong> <strong class="pull-right">
                           09:45AM</strong>
                        </div>
                     </div>
                     </a>
                  </li>
                  </c:forEach>
               </ul>
            </div></div>
         </div>
		 <c:if test="${not empty userPicked}">
         <!--chat_sidebar-->
		 
         <div class="col-sm-9 message_section">
		 <div class="row">
		 <div class="new_message_head">
		  <span>Messages</span>
		</div>

		 </div><!--new_message_head-->
		 
		 <div id = "chat-area" class="chat_area">
		 <div class = "messages-area">
		 <ul id = "messages" style = "overflow: auto;">
		 </ul>
		 </div>
		 </div><!--chat_area-->
          <div id = "#message-container" class="message_write">
    	 <textarea id = "message" class="form-control" placeholder="type a message"></textarea>
		 <div class="clearfix"></div>
		 <div class="chat_bottom"><a href="#" class="pull-left upload_btn"><i class="fa fa-cloud-upload" aria-hidden="true"></i>
 Add Files</a>
 <button id = "send-button" class="pull-right btn btn-success">Send</button></div>
		 </div>
		 </div>
         </div> <!--message_section-->
      </div>
   </div>
</div>
	</c:if>

</body>
</html>