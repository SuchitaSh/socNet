package com.socnet.service;

import com.socnet.persistance.entities.Comment;
import com.socnet.persistance.entities.Post;
import com.socnet.persistance.entities.User;
import com.socnet.persistance.repository.PostRepository;
import com.socnet.persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ruslan Lazin
 */
@Service
public class PostService {

    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public PostService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public Post addComment(Long postId, String text, Long authorId) { //may be better return Comment?
        Comment comment = new Comment();
        User author = userRepository.findById(authorId);
        comment.setUser(author);
        comment.setAuthorFullName(author.getFirstName() + " " + author.getLastName());
        Post post = postRepository.findById(postId);
        post.getComments().add(comment);
        post = postRepository.update(post);
        return post;
    }
}
