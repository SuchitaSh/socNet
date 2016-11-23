function toggle_visibility() {
    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
}

function login(){
	
	var username = $("#login").val();
	localStorage.setItem("username", username);	
}

$(function () {
	   
	 $("#login-button").click(function() { 
		 	login(); 
		    $("#login-form").submit();
	 });
	 	
});


