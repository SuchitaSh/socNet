package com.socnet.service;

import com.socnet.exception.EntityNotFoundException;
import com.socnet.persistence.entities.Comment;
import com.socnet.persistence.entities.Post;
import com.socnet.persistence.entities.User;
import com.socnet.persistence.repository.CommentsRepository;
import com.socnet.persistence.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * @author Ruslan Lazin
 */
@Component
public class CommentService {
    private CommentsRepository commentsRepository;
    private PostsRepository postsRepository;
    private UserService userService;

    @Autowired
    public CommentService(CommentsRepository commentsRepository, PostsRepository postsRepository, UserService userService) {
        this.commentsRepository = commentsRepository;
        this.postsRepository = postsRepository;
        this.userService = userService;
    }

    @Transactional
    public Set<Comment> getAllCommentsOfPost(Long postId) {
        Post post = postsRepository.findById(postId);
        if (post == null) {
            throw new EntityNotFoundException();
        }
        Set<Comment> commentsOfPost = commentsRepository.findByPost(post);
        return commentsOfPost;
    }

    @Transactional
    public Comment addCommentToPost(Long postId, String text) { //now return Comment
        Comment comment = new Comment();
        User author = userService.getCurrentUser();
        if (author == null) {
            throw new EntityNotFoundException();  //todo other exception?
        }
        comment.setUser(author);
        Post post = postsRepository.findById(postId);
        if (post == null) {
            throw new EntityNotFoundException();
        }
        comment.setText(text);
        post.addComment(comment);
        postsRepository.save(post);
        return comment;
    }
}
