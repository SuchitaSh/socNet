/**
 * Created by admin on 10.11.2016.
 */

function toggle_visibility() {
    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
}

/*
 * registration function with jquery ajax
 */
$(document).ready(function($) {
	$("#create-button").click(function(event) {
				
		var url = "/register"
			
		var data = {}
        data["username"]   = $("#username").val(),
            data["password"]   = $("#password").val(),
            data["email"] 	   = $("#email").val(),
            data["name"]		=$("#name").val(),
            data["surname"]		=$("#surname").val(),
            data["birthday"]	=$("#birthday").val();

        var token = $('#csrfToken').val();
        var header = $('#csrfHeader').val();

        $.post(url, data);    
    });
	
	$('#login-form').submit(function() {
		localStorage.setItem("username", $("#login").val())
		return true; 
	});
});