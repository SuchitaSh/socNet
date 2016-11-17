package com.socnet.web.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socnet.persistence.entities.User;
import com.socnet.service.UserService;

@RestController
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "api/user/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable String username) {
        System.out.println(username);
        User user = userService.findUserByUsername(username);

        user.setFollowings(null);
        user.setPosts(null);
        user.setNotifications(null);

        return user;
    }

    @GetMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser() {
        User user = userService.getCurrentUser();

        user.setNotifications(null);
        user.setFollowings(null);
        user.setPosts(null);

        return user;
    }
}
