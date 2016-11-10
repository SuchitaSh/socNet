package com.socnet.service;

import com.socnet.persistance.entities.Comment;
import com.socnet.persistance.entities.Post;
import com.socnet.persistance.entities.User;
import com.socnet.persistance.repository.PostsRepository;
import com.socnet.persistance.repository.PostsRepository;
import com.socnet.persistance.repository.UsersRepository;
import com.socnet.persistance.repository.UsersRepository;

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
        comment.setAuthorFullName(author.getFirstName() + " " + author.getLastName());
        Post post = postsRepository.findById(postId);
        post.getComments().add(comment);
        post = postsRepository.save(post);
        return post;
    }
}
