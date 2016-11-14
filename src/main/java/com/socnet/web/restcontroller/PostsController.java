package com.socnet.web.restcontroller;

import com.fasterxml.jackson.annotation.JsonView;
import com.socnet.persistence.entities.Comment;
import com.socnet.persistence.entities.Post;
import com.socnet.service.CommentService;
import com.socnet.web.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
public class PostsController {
    private CommentService commentService;

    @Autowired
    public PostsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/api/posts/{postId:[0-9]+}/comments", method = RequestMethod.GET)
    @JsonView(Views.WithChildren.class)
    public ResponseEntity<Set<Comment>> getAllCommentsOfPost(@PathVariable long postId) {
        Set<Comment> commentsOfPost = commentService.getAllCommentsOfPost(postId);
        return ResponseEntity.ok(commentsOfPost);
    }
}
