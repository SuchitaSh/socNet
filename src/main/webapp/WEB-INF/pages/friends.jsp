<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Friends</title>

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
  <link rel="stylesheet" type="text/css" href="${navbarCss}">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/friends.js' />"></script>
   <script type="text/javascript" src="<c:url value='/resources/js/subscribe.js' />"></script>
  

</head>
<body>

	<c:import url = "/resources/html/navbar.html"/>

<div class="container">
<div class="row">
        <div class="col-xs-12 col-sm-offset-3 col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading c-list">
                    <span class="title">Contacts</span>
                   </div>
                
                <ul class="list-group" id="contact-list">
                   </ul>
                                </div>
            </div>
        </div>
    </div>
    
<br>
<br>


</body>
</html>