package com.socnet.web.restcontroller;

import com.socnet.exception.EntityNotFoundException;
import com.socnet.persistence.entities.Post;
import com.socnet.persistence.entities.User;
import com.socnet.persistence.repository.PostsRepository;
import com.socnet.persistence.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value="/api/users/{userId:[0-9]+}/posts",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserPostsController {
    // TODO: should this be in UserPostsController or PostService?
    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public ResponseEntity<Set<Post>> getAllPosts(@PathVariable long userId) {
        User user = usersRepository.findById(userId);
        if(user == null) {
            throw new EntityNotFoundException();
        }

        Set<Post> userPosts = postsRepository.findByUser(user);
        return ResponseEntity.ok(userPosts);
    }
}
