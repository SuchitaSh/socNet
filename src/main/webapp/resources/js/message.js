$(document).ready(function($) {
    $("#send").click(function(event) {

        var url = "http://localhost:8080/socNet/send-message"

        var data = {}
        data["text"]   = $("#username").val();


        $.post(url, data);

    });
});