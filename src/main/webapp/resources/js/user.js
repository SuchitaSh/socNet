/**
 * 
 */

function getUserInfo(){
	
	var pathname = window.location.pathname;
	var index = pathname.lastIndexOf("/")
	var user = pathname.substring(index, pathname.length)
        if(user == "/home")
            user = "";
	
	var url = "/api/user" + user;
	
	
	$.get(url, function(data){
	
		$("#user-fullname").html(data.firstName + " " + data.lastName);
		
	});
	
}

function addToFriends(){
	
	var pathname = window.location.pathname;
	var index = pathname.lastIndexOf("/")
	var user = pathname.substring(index, pathname.length)
        if(user == "/home")
            user = "";
	
	var getUrl = "/api/addToFriends" + user;
	$.get(getUrl);

	var stompClient = null;
	
	var socket = new SockJS('/socnetws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
    	var destination = "/app/notification.private." + user.substring(1, user.length);
    	stompClient.send(destination, {});
   
    });
   
	 
	
}

window.addEventListener('load', getUserInfo);

$(function () {
	   
	 $("#add-to-friends-button").click(function() { addToFriends(); });
	 	
});