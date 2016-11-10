package com.socnet.web.controller;

import com.socnet.persistance.entities.User;
import com.socnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ruslan Lazin
 */
@Controller
public class TestLoginController {

    UserService userService;

    @Autowired
    public TestLoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ModelAndView doLogin(@RequestParam String login, @RequestParam String password) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("Login = " + login);
        System.out.println(password);
        User user = userService.login(login, password);
        /*if (user==null)
            modelAndView.setViewName("error");*/
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/home")
     public ModelAndView showHomePage() {
            ModelAndView modelAndView = new ModelAndView("home");
            return modelAndView;

    }

}
