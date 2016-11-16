package com.socnet.web.restcontroller;

import com.socnet.dto.BasicPostDto;
import com.socnet.persistence.entities.Post;
import com.socnet.service.PostService;
import com.socnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Set<BasicPostDto>> getAllPosts(@PathVariable long userId) {
        Set<BasicPostDto> userPosts = postService.getAllPostsOfUser(userId);
        return ResponseEntity.ok(userPosts);
    }

    @PostMapping
    public ResponseEntity<BasicPostDto> addPost(@PathVariable long userId, @RequestBody Post post) {
        BasicPostDto newPost = userService.addPost(post.getText(), post.getTitle());
        return ResponseEntity.ok(newPost);
    }
}
