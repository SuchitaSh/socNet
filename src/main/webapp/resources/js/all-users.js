/**
 * 
 */
function getAllUsers(){
	
	var url = "http://localhost:8080/socNet/api/users";
	console.log(url);
	$.get(url, function(data){
		for(user of data){
		console.log(user);
		var userUrl = "http://localhost:8080/socNet/home/" + user.username;
        var div = '<li class="list-group-item clearfix">' +
                        '<div class="col-xs-12 col-sm-3">' +
                            '<img src="http://api.randomuser.me/portraits/men/49.jpg" ' +
                        'alt="' + user.firstName + ' ' + user.lastName + 
                        '" class= "img-responsive img-circle" />' +
                        '</div>' +
        				'<div class="col-xs-12 col-sm-9">' +
                             '<div class="profile-usertitle-name">' +
                        '<a href = "' + userUrl + ' ">' + user.firstName + 
                        ' ' + user.lastName + ' </a>' +
                    	'</div>' +
                    	 '<a>Write message</a>'+
                        '</div>' +
                        '<div class="clearfix"></div>' + 
                    '</li>';
        $("#contact-list").append(div);
		}
	}).error(function(jqXHR, textStatus, errorThrown) {
		  if (textStatus == 'timeout')
			    console.log('The server is not responding');

			  if (textStatus == 'error')
			    console.log(errorThrown);

			  // Etc
			});
}

window.onload = getAllUsers;