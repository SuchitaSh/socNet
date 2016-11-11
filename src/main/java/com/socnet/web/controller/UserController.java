package com.socnet.web.controller;

import com.socnet.persistance.entities.User;
import com.socnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showRootPage() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        User user = userService.getCurrentUser();


        if(user == null) {
            return "redirect:/login";
        }

        userService.addPost("Post", "Title");

        model.addAttribute(user);
        model.addAttribute("posts");
        return "home";
    }
    }
