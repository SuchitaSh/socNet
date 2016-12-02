package com.socnet.web.restcontroller;

import com.socnet.service.UserService;
import com.socnet.utils.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AddFriendController {
    private UserService userService;

    @Autowired
    public AddFriendController(UserService userService) {
        this.userService = userService;
    }


    @ResponseBody
    @GetMapping("/api/addToFriends/{username}")
    public void addToFriends(@PathVariable String username) {
        userService.addNotificationToUser(username, NotificationType.FRIEND_REQUEST);
        userService.addCurrentUserFollowing(username);
    }


}
