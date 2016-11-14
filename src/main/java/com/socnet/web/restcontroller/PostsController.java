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
@RequestMapping(value = "/api/posts/{postId:[0-9]+}/comments",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class PostsController {
    private CommentService commentService;

    @Autowired
    public PostsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    @JsonView(Views.WithChildren.class)
    public ResponseEntity<Set<Comment>> getAllCommentsOfPost(@PathVariable long postId) {
        Set<Comment> commentsOfPost = commentService.getAllCommentsOfPost(postId);
        return ResponseEntity.ok(commentsOfPost);
    }


    @PostMapping
    public ResponseEntity<Comment> addComment(@PathVariable long postId, @RequestBody Comment comment) {
        System.out.println(comment.getText());
        Comment newComment = commentService.addCommentToPost(postId, comment.getText());
        return ResponseEntity.ok(newComment);
    }
}