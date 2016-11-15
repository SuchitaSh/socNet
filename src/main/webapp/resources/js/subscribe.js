/**
 * 
 */

function connect(){
		
	var stompClient = null;

	    var socket = new SockJS('/socNet/socnetws');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {
	        console.log('Connected: ' + frame);
	        console.log(localStorage.getItem("username") + "asdf");
	        stompClient.subscribe('/topic/notifications/' + localStorage.getItem("username"), function (notification) {
	        	console.log(notification.body);
	        });
	    });

	
}
window.addEventListener('load', connect);
