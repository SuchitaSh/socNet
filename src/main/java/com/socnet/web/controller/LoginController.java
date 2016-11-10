package com.socnet.web.controller;

import com.socnet.persistance.entities.User;
import com.socnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    public static final String KEY_LOGIN_ERROR = "error";

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "/login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String login,
                                @RequestParam String password,
                                Model model) {
        User user = userService.login(login, password);

        if(user == null) {
            model.addAttribute(KEY_LOGIN_ERROR, true); // TODO: i18n
            return "redirect:/login";
        }

        return "redirect:/home";
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
