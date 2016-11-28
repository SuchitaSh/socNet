package com.socnet.web.controller;

import com.socnet.dto.UserWithFriendsDto;
import com.socnet.persistence.entities.User;
import com.socnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * Created by admin on 17.11.2016.
 */
@Controller
public class FollowersController {
    private UserService userService;

    @Autowired
    public FollowersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/followers", "/friends"})
    public String getFollowers(Model model, HttpServletRequest request) {
        String path = request.getServletPath();
        if (path.equals("/followers")) {
            System.out.println("followers");
            Set<User> followers = userService.getFollowersOfCurrentUser();
            if (followers.isEmpty()) {
                return "followers";
            }
            model.addAttribute("users", followers);
        } else if (path.equals("/friends")) {
            Set<User> friends = userService.getCurrentUserWithFriends().getFriends();
            if (friends.isEmpty()) {
                return "followers";
            }
            model.addAttribute("users", friends);
        }

        return "followers";
    }
}
