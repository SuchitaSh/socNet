package com.socnet.web.controller;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.socnet.dto.UserWithFriendsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.socnet.persistence.entities.User;
import com.socnet.service.MessageService;
import com.socnet.service.UserService;
import com.socnet.service.UsernameStorage;
import com.socnet.utils.Message;

@Controller
public class DialogsController {

	private UserService userService;
    private SimpMessagingTemplate simpMessagingTemplate;
    private MessageService messageService;
    private UsernameStorage usernameStorage;
    
    @Autowired
    public DialogsController(UserService userService, SimpMessagingTemplate simpMessagingTemplate,
    						 MessageService messageService, UsernameStorage usernameStorage) {
        this.userService = userService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.messageService = messageService;
        this.usernameStorage = usernameStorage;
    }

    @GetMapping("/dialogs")
    public String getDialogs(Model model) {
        Set<User> friends = userService.getCurrentUserWithFriends().getFriends();
        model.addAttribute("friends", friends);

        return "dialogs";
    }

    @GetMapping("/dialogs/{username}")
    public String getUserDialogs(@PathVariable String username, Model model) {

        UserWithFriendsDto currentUser = userService.getCurrentUserWithFriends();
        Set<User> friends = currentUser.getFriends();
        List<Message> messages = messageService.getAllMessages(usernameStorage.getUsername(), username);
        model.addAttribute("friends", friends);
        model.addAttribute("userPicked", username);
        model.addAttribute("user", currentUser);
        model.addAttribute("messages", messages);
       
        return "dialogs";
    }

    @MessageMapping("/message.private")
    public void sendMessage(Message message) {
    	System.out.println("fuck");
		messageService.addMessage(message);
		simpMessagingTemplate.convertAndSend("/topic/messages/" + message.getDestination(), message.getMessage());
	}
}
