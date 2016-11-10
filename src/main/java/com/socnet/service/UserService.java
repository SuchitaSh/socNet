package com.socnet.service;

import com.socnet.persistance.entities.User;
import com.socnet.persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Ruslan Lazin
 */

@Service
public class UserService {

    UsernameStorage principal;
    UserRepository userRepository;

    @Autowired
    public UserService(UsernameStorage principal, UserRepository userRepository) {
        this.principal = principal;
        this.userRepository = userRepository;
    }

//method returns User object, and saves username in sessionScope "principal"
//returns null if user does not found or password does not match.
    public User login(String login, String password) {
        User user = userRepository.findByUsername(login);
        if (user!=null && user.getUserName().equals(password)) {
            principal.setUsername(user.getUserName());

        }
        return user;
    }

    public User getCurrentUser(){
        return userRepository.findByUsername(principal.getUsername());
    }

    public void logout(){
        principal.setUsername(null);
    }
}
