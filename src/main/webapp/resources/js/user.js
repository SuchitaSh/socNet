/**
 * 
 */

function getUserInfo(){
	
	var pathname = window.location.pathname;
	var index = pathname.lastIndexOf("/")
	var user = pathname.substring(index, pathname.length)
        if(user == "/home")
            user = "";
	
	var url = "http://localhost:8080/socNet/api/user" + user;
	
	console.log(url);
	
	$.get(url, function(data){
	
		console.log(data.firstName);
		$("#user-fullname").html(data.firstName + " " + data.lastName);
		
	});
	
}

function addToFriends(){
	
	var pathname = window.location.pathname;
	var index = pathname.lastIndexOf("/")
	var user = pathname.substring(index, pathname.length)
        if(user == "/home")
            user = "";
	
	var getUrl = "http://localhost:8080/socNet/api/addToFriends" + user;
	console.log(getUrl);
	$.get(getUrl);

	var stompClient = null;
	
	var socket = new SockJS('/socNet/socnetws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
    	var destination = "/app/notification.private." + user.substring(1, user.length);
    	console.log("destination:" + destination);
    	console.log(localStorage.getItem("username"));
    	stompClient.send(destination, {});
   
    });
   
	 
	
}

window.addEventListener('load', getUserInfo);

$(function () {
	   
	 $("#add-to-friends-button").click(function() { addToFriends(); });
	 	
});