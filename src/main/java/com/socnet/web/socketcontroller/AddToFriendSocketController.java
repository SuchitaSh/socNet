package com.socnet.web.socketcontroller;

import com.socnet.utils.NotificationType;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * Created by admin on 02.12.2016.
 */
public class AddToFriendSocketController {

    @MessageMapping("/notification.private.{username}")
    @SendTo("/topic/notifications/{username}")
    public String addToFriend(@DestinationVariable String username) {
        return NotificationType.FRIEND_REQUEST;
    }
}
