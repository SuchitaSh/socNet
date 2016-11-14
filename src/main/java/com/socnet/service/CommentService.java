package com.socnet.service;

import com.socnet.exception.EntityNotFoundException;
import com.socnet.persistence.entities.Comment;
import com.socnet.persistence.entities.Post;
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

    @Autowired
    public CommentService(CommentsRepository commentsRepository, PostsRepository postsRepository) {
        this.commentsRepository = commentsRepository;
        this.postsRepository = postsRepository;
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
}
