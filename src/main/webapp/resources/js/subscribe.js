/**
 * 
 */

function connect(){
		
	var stompClient = null;

		friendsUrl = "http://localhost:8080/socNet/friends";
	
	    var socket = new SockJS('/socNet/socnetws');
	    console.log("fuck");
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {
	        console.log('Connected: ' + frame);
	        console.log(localStorage.getItem("username") + "asdf");
	        stompClient.subscribe('/topic/notifications/' + localStorage.getItem("username"), function (notification) {
	        
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

	        });
	    });

	
}
window.addEventListener('load', connect);
