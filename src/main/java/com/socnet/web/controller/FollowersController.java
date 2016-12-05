package com.socnet.web.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.socnet.dto.BasicUserDto;
import com.socnet.persistence.entities.User;
import com.socnet.service.UserService;

@Controller
public class FollowersController {
    private UserService userService;

    @Autowired
    public FollowersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/followers")
    public String getFollowers(Model model, HttpServletRequest request) {
        List<BasicUserDto> followers = userService.getFollowersOfCurrentUser();
        model.addAttribute("users", followers);
   
        return "followers";
    }
    
    @GetMapping("/friends")
    public String getFriends(Model model, HttpServletRequest request) {
        
        Set<User> friends = userService.getCurrentUserWithFriends().getFriends();
        model.addAttribute("users", friends);
        
        return "followers";
    } 
}
