package com.socnet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/home")
    public String showHomePage(Model model) {
        // TODO: only if already logged in
        return "home";
    }
}
