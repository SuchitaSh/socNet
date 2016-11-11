package com.socnet.web.restcontroller;

import com.socnet.persistance.entities.Post;
import com.socnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by admin on 11.11.2016.
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    public UserService userService;

    @PostMapping(value = "/addPost", produces= MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
    public String addPost(@RequestBody Post post) {
        System.out.println("Title: "+post.getTitle());
        System.out.println("Text: " + post.getText());
        userService.addPost(post.getText(), post.getTitle());
        return null ;
    }
}
