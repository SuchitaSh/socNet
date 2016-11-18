package com.socnet.web.controller;

import com.socnet.persistence.entities.User;
import com.socnet.service.AuthService;
import com.socnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    public static final String KEY_LOGIN_ERROR = "login-error";  //can it be private? RL
    public static final String KEY_REGISTER_ERROR = "register-error";

    private UserService userService;
    private AuthService authService;

    @Autowired
    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginPage() {
        if (userService.getCurrentUser() != null) {     //Do we need this if?  RL

            return "redirect:/home";                    // Is it criminal to go to login page
                                                        // when you already logged in? May be you want to change account
        }
        return "login";
    }


    @PostMapping("/login")
    public String doLogin(@RequestParam String login,
                          @RequestParam String password,
                          Model model) {
        User user = authService.login(login, password);

        if (user == null) {
            model.addAttribute(KEY_LOGIN_ERROR, true); // TODO: i18n
            return "redirect:/login";
        }

        return "redirect:/home";
    }

    @PostMapping("/register")
    public String doRegister(@RequestParam String username, @RequestParam String password,
                             @RequestParam String email, @RequestParam String name,
                             @RequestParam String surname,
                             Model model) {

        if (!userService.isUsernameAvailable(username)) {
            model.addAttribute(KEY_REGISTER_ERROR, true);
            return "redirect:/login";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(name);
        user.setLastName(surname);
        user.setEmail(email);

        userService.save(user);

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String doLogout() {
        authService.logout();

        return "redirect:/login";
    }
}
