package com.socnet.web.restcontroller;

import com.socnet.persistence.entities.User;
import com.socnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by admin on 17.11.2016.
 */
@RestController
public class FollowersRestController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/api/followers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<User>> getCurrentUserFriends(){

        Set<User> followers = userService.getCurrentUserFollowers();

        if(followers.isEmpty()){
            return new ResponseEntity<Set<User>>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(followers);
    }

    @GetMapping(path = "/api/followers/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<User>> getUserFriends(@PathVariable String username){
        Set<User> followers = userService.getUserFollowers(username);

        if(followers.isEmpty()){
            return new ResponseEntity<Set<User>>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(followers);
    }
}
