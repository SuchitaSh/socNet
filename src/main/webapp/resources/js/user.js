window.addEventListener('load', getUserInfo);

$(function () {
    $("#follow-button").click(onFollow);
    updateFollowButton($("#follow-button"));
});

function getUserInfo(){
	var pathname = window.location.pathname;
	var index = pathname.lastIndexOf("/")
	var user = pathname.substring(index, pathname.length)
        if(user == "/home")
            user = "";
	
	var url = "/api/user" + user;
	
	
	$.get(url, function(data){
		$("#user-fullname").html(data.firstName + " " + data.lastName);
	});
	
}

function updateFollowButton($followButton) {
    var following = $followButton.data('following');

    if(following) {
        $followButton.html($followButton.data('following-message'));
        $followButton.addClass('faded-reversible');

        enableHoverMessage($followButton);
    } else {
        $followButton.html($followButton.data('not-following-message'));
        $followButton.removeClass('faded-reversible');

        $followButton.unbind('mouseenter').unbind('mouseleave');
    }

}

function onFollow() {
    var $this = $(this);
    var following = $this.data('following');


    if(following) {
        $this.data('following', false);
        unfollow(getUsername());
    } else {
        $this.data('following', true);
        follow(getUsername());
    }

    updateFollowButton($this);
}

function enableHoverMessage($el) {
    var initText;

    function hover(e) {
        if(e.handled !== true) {
            initText = $el.html();
            $el.html($el.data('hover-message'));
        }
    }

    function hoverEnd() {
        $el.html(initText);
    }

    $el.on('mouseenter', hover);
    $el.on('mouseleave', hoverEnd);
}

function follow(user) {
	var getUrl = "/api/addToFriends" + user;

	// TODO: remove dummy when UserRestController get fixed
	ajax('/api/user/'+ 'dummy'  + '/followings', 'POST', { username: user.substring(1) })

    notify(user);
}

function unfollow(user) {
	ajax('/api/user/'+ 'dummy'  + '/followings/' + user.substring(1), 'DELETE')
}

function notify(user) {
	var stompClient = null;

	var socket = new SockJS('/socnetws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
    	var destination = "/app/notification.private." + user.substring(1, user.length);
    	stompClient.send(destination, {});

    });
}

function getUsername() {
	var pathname = window.location.pathname;
	var index = pathname.lastIndexOf("/")
	var user = pathname.substring(index, pathname.length)
        if(user == "/home")
            user = "";

    return user;
}

function ajax(url, method, data) {
    return $.ajax(url, {
        method: method,
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json',
    });
}