package com.socnet.web.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.socnet.dto.BasicUserDto;
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
    
    // TODO: this method works in the context of current user
    // TODO: replace basicUserDto with something smaller
    @PostMapping(path = "/api/user/{username}/followings", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> follow(@PathVariable String username, @RequestBody BasicUserDto follower) {
        userService.addCurrentUserFollowing(follower.getUsername());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/api/user/{username}/followings/{following}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> unfollow(@PathVariable String username, @PathVariable String following) {
    	// TODO: proper return value
    	userService.removeCurrentUserFollowing(following);
        return ResponseEntity.ok().build();
    }
}
