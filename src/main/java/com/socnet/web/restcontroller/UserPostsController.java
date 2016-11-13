package com.socnet.web.restcontroller;

import com.fasterxml.jackson.annotation.JsonView;
import com.socnet.persistence.entities.Post;
import com.socnet.persistence.entities.User;
import com.socnet.service.PostService;
import com.socnet.service.UserService;
import com.socnet.web.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/users/{userId:[0-9]+}/posts",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserPostsController {
    private PostService postService;

    private UserService userService;

    @Autowired
    public UserPostsController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    @JsonView(Views.WithChildren.class)
    public ResponseEntity<Set<Post>> getAllPosts(@PathVariable long userId) {
        Set<Post> userPosts = postService.getAllPostsOfUser(userId);
        return ResponseEntity.ok(userPosts);
    }

    @PostMapping
    public ResponseEntity<Post> addPost(@PathVariable long userId, @RequestBody Post post) {
        // TODO: not allowed exception
        Post newPost = userService.addPost(post.getText(), post.getTitle());
        return ResponseEntity.ok(newPost);
    }
}
