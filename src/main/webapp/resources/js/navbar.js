/**
 * 
 */

var socket = new SockJS('/socnetws');
var stompClient = Stomp.over(socket);
	   
function connect(){
		friendsUrl = "/followers";
		console.log("in connect")
		stompClient.connect({}, function (frame) {
	        stompClient.subscribe('/topic/notifications/' + localStorage.getItem("username"),
	        													function(notification){
	        	postNotification(motification);
	        });
	        stompClient.subscribe("/topic/messages/" + localStorage.getItem("username"), 
	        													function(message){
	        	postMessage(message);
	        	console.log("hello");
	        													
	        });
});
}

function postNotification(notification){
	
	toastr.options = {
			  "closeButton": false,
			  "debug": false,
			  "newestOnTop": false,
			  "progressBar": false,
			  "positionClass": "toast-bottom-left",
			  "preventDuplicates": false,
			  "onclick": function(){window.location = friendsUrl;},
			  "showDuration": "300",
			  "hideDuration": "1000",
			  "timeOut": "5000",
			  "extendedTimeOut": "1000",
			  "showEasing": "swing",
			  "hideEasing": "linear",
			  "showMethod": "fadeIn",
			  "hideMethod": "fadeOut"
			}
	
	
	 toastr["info"]("You got a new friend request");

	
}

function postMessage(message){
	var toPost = true;
	var pathname = window.location.pathname;
	var index = pathname.lastIndexOf("/")
	
	if(pathname.substring(0, index) == "/dialogs"){
		var user = pathname.substring(index + 1, pathname.length);
		message = JSON.parse(message.body);
		if(user == message.sender){
			toPost = false;
			}
		}
	
	if(toPost){
		var newMessages = $("#dialogs").text();
		var messagesNum = newMessages.substring(newMessages.indexOf("(") + 1, 
												newMessages.indexOf(")"));
		if(! messagesNum){
			messagesNum = 1;
		}
		else{
			messagesNum = parseInt(messagesNum) + 1;
		}
		
		$("#dialogs").text('Messages(' + messagesNum + ')');
		
		var senderDialog = "#num_of_messages_" + message.sender;
		var messagesPerSender = $(senderDialog).text();
		
		if(! messagesPerSender){
			messagesPerSender = 1;
		}
		else{
			messagesPerSender = parseInt(messagesPerSender) + 1;
		}
		
		$(senderDialog).css("visibility", "visible")
		$(senderDialog).text(messagesPerSender);
		
	}
	
}

function getUnreadMessagesCount(){
	
	$.get( "/api/dialogs/unreadMessagesCount", function(data){
		console.log("unread messages : " + data);
		if( data !== 0){
			console.log("here");
			$("#dialogs").append( '(' + data + ')');
		}
	});
	
}

window.addEventListener('load', connect);
window.addEventListener('load', getUnreadMessagesCount);