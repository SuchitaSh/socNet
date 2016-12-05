package com.socnet.web.restcontroller;

import com.socnet.dto.BasicCommentDto;
import com.socnet.persistence.entities.Comment;
import com.socnet.service.CommentService;
import com.socnet.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostsController {
    private CommentService commentService;
    private PostService postService;

    @Autowired
    public PostsController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @GetMapping(path = "/api/posts/{postId:[0-9]+}/comments",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BasicCommentDto>> getAllCommentsOfPost(@PathVariable long postId) {
        List<BasicCommentDto> commentsOfPost = commentService.getAllCommentsOfPost(postId);
        return ResponseEntity.ok(commentsOfPost);
    }

    @PostMapping(path = "/api/posts/{postId:[0-9]+}/comments",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> addComment(@PathVariable long postId, @RequestBody Comment comment) {
        Comment newComment = commentService.addCommentToPost(postId, comment.getText());
        return ResponseEntity.ok(newComment);
    }

    @DeleteMapping(path = "/api/posts/{postId:[0-9]+}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removePost(@PathVariable long postId) {
        postService.tryDeletePost(postId);
        return ResponseEntity.noContent().build();
    }
}