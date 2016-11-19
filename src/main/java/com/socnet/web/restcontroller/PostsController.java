package com.socnet.web.restcontroller;

import com.socnet.dto.BasicCommentDto;
import com.socnet.persistence.entities.Comment;
import com.socnet.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<BasicCommentDto>> getAllCommentsOfPost(@PathVariable long postId) {
        List<BasicCommentDto> commentsOfPost = commentService.getAllCommentsOfPost(postId);
        return ResponseEntity.ok(commentsOfPost);
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@PathVariable long postId, @RequestBody Comment comment) {
        Comment newComment = commentService.addCommentToPost(postId, comment.getText());
        return ResponseEntity.ok(newComment);
    }
}