package com.socnet.service;

import com.socnet.exception.EntityNotFoundException;
import com.socnet.persistence.entities.Comment;
import com.socnet.persistence.entities.Post;
import com.socnet.persistence.entities.User;
import com.socnet.persistence.repository.PostsRepository;
import com.socnet.persistence.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PostService {

    private UsersRepository usersRepository;
    private PostsRepository postsRepository;

    @Autowired
    public PostService(UsersRepository usersRepository, PostsRepository postsRepository) {
        this.usersRepository = usersRepository;
        this.postsRepository = postsRepository;
    }

    public Comment addCommentToPost(Long postId, String text, Long authorId) { //now return Comment
        Comment comment = new Comment();
        User author = usersRepository.findById(authorId);
        comment.setUser(author);
        Post post = postsRepository.findById(postId);
        if (post == null) {
            throw new EntityNotFoundException();
        }
        post.addComment(comment);
        postsRepository.save(post);
        return comment;
    }

    public Set<Post> getAllPostsOfUser(Long userId) {
        User user = usersRepository.findById(userId);
        if (user == null) {
            throw new EntityNotFoundException();
        }
        Set<Post> userPosts = postsRepository.findByUser(user);
        return userPosts;
    }
}
