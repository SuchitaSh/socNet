package com.socnet.service;

import com.socnet.exception.EntityNotFoundException;
import com.socnet.persistence.entities.Comment;
import com.socnet.persistence.entities.Post;
import com.socnet.persistence.entities.User;
import com.socnet.persistence.repository.PostsRepository;
import com.socnet.persistence.repository.UsersRepository;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {

    private UsersRepository usersRepository;
    private PostsRepository postsRepository;

    @Autowired
    public PostService(UsersRepository usersRepository, PostsRepository postsRepository) {
        this.usersRepository = usersRepository;
        this.postsRepository = postsRepository;
    }

    public Post addPost(Post post) {
        post = postsRepository.save(post);
        return post;
    }


    /**
     * @return Post entities with all comments
     */
    @Transactional
    public Set<Post> getAllPostsOfUser(Long userId) {
        User user = usersRepository.findById(userId);
        if (user == null) {
            throw new EntityNotFoundException();
        }

        Set<Post> userPosts = postsRepository.findByUser(user);

        userPosts.stream()
                .forEach(Hibernate::initialize);

//        userPosts.stream()
//                 .map(Post::getComments)
//                 .forEach(Hibernate::initialize);

        userPosts.stream()
                .map(Post::getComments)
                .flatMap(Set::stream)
                .forEach(Hibernate::initialize);

        return userPosts;
    }

    /**
     * @return Post, just entity
     */
    public Post getPost(Long postId) {
        return postsRepository.findById(postId);
    }
}
