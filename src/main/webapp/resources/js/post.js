$(document).ready(function() {


    $('#postForm').submit(function(event) {

        var text = $('#text').val();
        var title = $('#title').val();

        var json = { "text" : text,
            "title" : title};

        $.post(url, json);
        document.getElementById("postForm").reset();

        /* $.ajax({
         url: "addPost",
         data: JSON.stringify(json),
         type: "POST",
         beforeSend: function(xhr) {
         xhr.setRequestHeader("Accept", "application/json");
         xhr.setRequestHeader("Content-Type", "application/json");
         },
         success: function(message) {
         var respContent = "";
         respContent += message.text;
         $("#demo").html(respContent);
         }
         });*/

        event.preventDefault();
    });

});