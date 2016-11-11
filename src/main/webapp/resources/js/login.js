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
				
		var url = "http://localhost:8080/socNet/register"
			
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
        
        /*
         * if in spring aplication csrf enable
         * send csrf parameter in header otherwise 405 error
         */
//        $.ajax({
//            type 	 : "POST",
//            url      : url,
//            data 	 : JSON.stringify(data),
//            dataType : 'json',
//            beforeSend: function(xhr) {
//                xhr.setRequestHeader("Accept", "application/json");
//                xhr.setRequestHeader("Content-Type", "application/json");
//                xhr.setRequestHeader(header, token);
//            },
//            success  : function(response) {
//                var message = "registration sucess";
//                //				$("#msg").html(data.message);
//                console.log(resonse.data);
//                alert(resonse.message);
//                data = null;
//                document.getElementById("registerForm").reset()
//            },
//            error 	 : function(e) {
//                console.log("ERROR: ",e);
//                alert("registration failed");
////						$("#msg").html(e.message);
//                data = null;
//                document.getElementById("registerForm").reset()
//            }
//        });

    });
});