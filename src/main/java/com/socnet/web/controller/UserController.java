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

    @GetMapping("/home")
    public String showHomePage(Model model) {
//         TODO: only if already logged in
//        if(userService.getCurrentUser() == null) {
//            return "redirect:/login";
//        }
        User user = new User();
        user.setUsername("john_doe");
        user.setFirstName("John");
        user.setLastName("Doe");

        model.addAttribute(user);
        return "home";
    }
}
