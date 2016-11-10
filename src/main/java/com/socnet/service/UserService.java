package com.socnet.service;

import com.socnet.persistance.entities.Post;
import com.socnet.persistance.entities.User;
import com.socnet.persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;


/**
 * @author Ruslan Lazin
 */

@Service
@Component
public class UserService {

    UsernameStorage principal;
    UserRepository userRepository;

    @Autowired
    public UserService(UsernameStorage principal, UserRepository userRepository) {
        this.principal = principal;
        this.userRepository = userRepository;
    }

    /*    method returns User object, and saves username in sessionScope "principal"
    returns null if user does not found or password does not match.*/

    public User login(String login, String password) {
        User user = userRepository.findByUsername(login);
        if (user != null && user.getPassword().equals(password)) {
            principal.setUsername(user.getUserName());
        } else {
            user = null;
        }
        return user;
    }

    public void logout() {
        principal.setUsername(null);
    }

    public User getCurrentUser() {
        return userRepository.findByUsername(principal.getUsername());
    }

    public User addPost(String text, String title) {
        Post post = new Post();
        post.setText(text);
        post.setDate(new Date(new java.util.Date().getTime()));
        post.setTitle(title);
        User user = userRepository.findByUsername(principal.getUsername());
        post.setUser(user);
        user.getPosts().add(post);
        user = userRepository.update(user);
        return user;
    }

}
