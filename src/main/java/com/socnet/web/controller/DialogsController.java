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
import com.socnet.service.UserService;
import com.socnet.service.UsernameStorage;
import com.socnet.utils.Message;

@Controller
public class DialogsController {

    private List<String> messages = new ArrayList<String>(50);
    private UserService userService;
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public DialogsController(UserService userService, SimpMessagingTemplate simpMessagingTemplate) {
        this.userService = userService;
        this.simpMessagingTemplate = simpMessagingTemplate;
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
        model.addAttribute("friends", friends);
        model.addAttribute("userPicked", username);
        model.addAttribute("user", currentUser);

        return "dialogs";
    }


    @MessageMapping("/message.addMessage")
    public void addMessage(Message message) {
        System.out.println("fuck1");
        messages.add(message.getMessage());
    }

    @MessageMapping("/message.private")
    public void sendMessage(Message message) {
        System.out.println("fuck2");
        messages.add(message.getMessage());

        simpMessagingTemplate.convertAndSend("/topic/messages/" + message.getDestination(), message.getMessage());
    }
}
