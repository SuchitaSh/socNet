/**
 * 
 */


function subscribeOnMessages(){

	var scrollHeight = $("#chat-area").prop("scrollHeight");
	$('#chat-area').animate({scrollTop: scrollHeight}, 1);
	
	console.log("hello");
	var stompClient;
	var socket = new SockJS('/socnetws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
    	stompClient.subscribe("/topic/messages/" + localStorage.getItem("username"), function (message) {
   
    		var id = $("#id").innerHTML;
    		var message = message.body;
    		
    		var data = {
    				"imageId" : id,
    				"message" : message
    		};


    		var template = $("#another-user-message").html();
    		
    		var html = Mustache.render(template, data);
    		
    		$("#messages").append(html);
    		var scrollHeight = $("#chat-area").prop("scrollHeight");
    		$('#chat-area').animate({scrollTop: scrollHeight}, 1);
	
    		var destination = "/app/message.addMessage";
    			
    	});
}); 
}

function sendMessage(){
	
	if(! $("#message").val()){
		return;
	}
	
	console.log("1");
	var pathname = window.location.pathname;
	var index = pathname.lastIndexOf("/")
	var user = pathname.substring(index + 1, pathname.length);
	
	var stompClient;
	var socket = new SockJS('/socnetws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
    	
		var message = $("#message").val();
		
		var data = {
				"message" : message
		};


		var template = $("#current-user-message").html();
		
		var html = Mustache.render(template, data);
		
		$("#messages").append(html);
		
		var scrollHeight = $("#chat-area").prop("scrollHeight");
		$('#chat-area').animate({scrollTop: scrollHeight}, 1);
		$('#message').val("");
    	var destination = "/app/message.private";
    	var sender = localStorage.getItem("username");
    	stompClient.send(destination, {}, JSON.stringify({'message': message, 'receiver' : user, 'sender' : sender}));
   
    });
	
}

window.onload = subscribeOnMessages;

$(function () {
	 $("#send-button").click(function() { sendMessage(); });
	 $("#message").keyup(function(event){
		    if(event.keyCode == 13){
		        $("#send-button").click();
		    }
		});
	 	
});