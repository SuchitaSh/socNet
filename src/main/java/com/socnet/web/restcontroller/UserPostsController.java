package com.socnet.web.restcontroller;

import com.socnet.exception.EntityNotFoundException;
import com.socnet.persistence.entities.Post;
import com.socnet.persistence.entities.User;
import com.socnet.persistence.repository.PostsRepository;
import com.socnet.persistence.repository.UsersRepository;

import com.socnet.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        // moved body to PostService
        Set<Post> userPosts = postService.getAllPostsOfUser(userId);
        return ResponseEntity.ok(userPosts);
    }
}
