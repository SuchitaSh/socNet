<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Sign in Login</title>
    <link href="<c:url value='/resources/css/login.css' />" rel="stylesheet"></link>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/login.js' />"></script>
</head>

<body>

<div class="login-page">
    <div class="form">
        <font color="red">
				<span style="align: center">
					<c:if test="${not empty param['error']}">
                        <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                    </c:if>
				</span>
        </font>
        <form class="register-form" id="registerForm" action="#" method="post">
            <input type="text" placeholder="name" id="username" autocomplete="off"/>
            <input type="password" placeholder="password" id="password"/>
            <input type="email" placeholder="email address" id="email" autocomplete="off"/>
            <input type="text" placeholder="You Name" id="name"/>
            <input type="text" placeholder="You Surname" id="surname">
            <input type="date" placeholder="You Birthday" id="birthday">
            <button>create</button>
            <p class="message">Already registered? <a href="#" onclick="toggle_visibility(); return false;" class="message">Sign In</a></p>
        </form>
        <form class="login-form" action="/login" method="post">
            <input type="text" name="login" placeholder="username" autocomplete="off"/>
            <input type="password" name="password" placeholder="password"/>
            <input class="submit" type="submit" value="Login">
            <p class="message">Not registered? <a href="#" onclick="toggle_visibility(); return false;" class="message">Create an account</a></p>
        </form>
    </div>
</div>

</body>
</html>