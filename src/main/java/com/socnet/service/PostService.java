package com.socnet.service;

import com.socnet.persistence.entities.Comment;
import com.socnet.persistence.entities.Post;
import com.socnet.persistence.entities.User;
import com.socnet.persistence.repository.PostsRepository;
import com.socnet.persistence.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ruslan Lazin
 */
@Service
public class PostService {

    private UsersRepository usersRepository;
    private PostsRepository postsRepository;

    @Autowired
    public PostService(UsersRepository usersRepository, PostsRepository postsRepository) {
        this.usersRepository = usersRepository;
        this.postsRepository = postsRepository;
    }

    public Post addComment(Long postId, String text, Long authorId) { //may be better return Comment?
        Comment comment = new Comment();
        User author = usersRepository.findById(authorId);
        comment.setUser(author);
        Post post = postsRepository.findById(postId);
        post.getComments().add(comment);
        post = postsRepository.save(post);
        return post;
    }
}
