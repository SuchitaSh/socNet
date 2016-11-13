package com.socnet.web.restcontroller;

import com.socnet.persistence.entities.Post;
import com.socnet.persistence.entities.User;
import com.socnet.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/users/{userId:[0-9]+}/posts",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserPostsController {
    private PostService postService;

    @Autowired
    public UserPostsController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<Set<Post>> getAllPosts(@PathVariable long userId) {
        // Stack overflow
//        Set<Post> userPosts = postService.getAllPostsOfUser(userId);
//        TODO: set null for unnecessary fields

        // For testing purposes only

        User user = new User();
        user.setFirstName("Jane");
        user.setLastName("Doe");

        Set<Post> userPosts = new HashSet<>();

        Post post = new Post();
        post.setText("Text t");
        userPosts.add(post);

        post = new Post();
        post.setText("Hello, World");
        userPosts.add(post);

        return ResponseEntity.ok(userPosts);
    }
}
