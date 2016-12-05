package com.socnet.web.restcontroller;

import com.socnet.dto.BasicPostDto;
import com.socnet.dto.BasicUserDto;
import com.socnet.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<BasicPostDto> addPost(@PathVariable long userId, @RequestBody BasicPostDto post) {
        post.setUser(new BasicUserDto(userId));
        BasicPostDto newPost = postService.addPost(post);
        return ResponseEntity.ok(newPost);
    }
    
    @GetMapping
    public ResponseEntity<List<BasicPostDto>> getAllPostsSlice(@PathVariable long userId,
                                                               @RequestParam(required = false) Long from) {
        if(from == null) {
            from = Long.MAX_VALUE;
        }

    	List<BasicPostDto> posts = postService.getUserPostsSlice(userId, from);
    	return ResponseEntity.ok(posts);
    }
}
