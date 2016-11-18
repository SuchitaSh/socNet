package com.socnet.web.controller;

import java.security.Principal;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.socnet.persistence.entities.Notification;
import com.socnet.persistence.entities.User;
import com.socnet.service.UserService;
import com.socnet.service.UsernameStorage;
import com.socnet.utils.NotificationType;

@Controller
public class AddFriendController {
    private UserService userService;

    @Autowired
    public AddFriendController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/api/addToFriends/{username}")
    public void addToFriends(@PathVariable String username) {
        userService.addNotificationToUser(username, NotificationType.FRIEND_REQUEST);
        userService.addCurrentUserFollowing(username);
    }

    @MessageMapping("/notification.private.{username}")
    @SendTo("/topic/notifications/{username}")
    public String addToFriend(@DestinationVariable String username) {
        return NotificationType.FRIEND_REQUEST;
    }
}
