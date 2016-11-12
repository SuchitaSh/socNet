package com.socnet.service;

import com.socnet.persistence.entities.Post;
import com.socnet.persistence.entities.User;
import com.socnet.persistence.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;


/**
 * @author Ruslan Lazin
 */

@Service
public class UserService {

    private UsernameStorage principal;
    private UsersRepository usersRepository;

    @Autowired
    public UserService(UsernameStorage principal, UsersRepository usersRepository) {
        this.principal = principal;
        this.usersRepository = usersRepository;
    }

    /*    method returns User object, and saves username in sessionScope "principal"
    returns null if user does not found or password does not match.*/

    public User login(String login, String password) {
        User user = usersRepository.findByUsername(login);
        if (user != null && user.getPassword().equals(password)) {
            principal.setUsername(user.getUsername());
        } else {
            user = null;
        }
        return user;
    }

    public void logout() {
        principal.setUsername(null);
    }

    public User getCurrentUser() {
        return usersRepository.findByUsername(principal.getUsername());
    }
    @Transactional
    public User addPost(String text, String title) { //or better return Post???
        Post post = new Post();
        post.setText(text);
        post.setPostingDate(new Date());
        post.setTitle(title);
        User user = usersRepository.findByUsername(principal.getUsername());
        post.setUser(user);
        user.addPost(post);
        user = usersRepository.save(user);
        return user;
    }
   
   public boolean isUsernameAvailable(String username){
	   
	   return usersRepository.findByUsername(username) == null;
   }
   
   public void save(User user){
	   usersRepository.save(user);
   }
}
