package com.socnet.service;

import com.socnet.persistence.entities.Post;
import com.socnet.persistence.entities.User;
import com.socnet.persistence.repository.UsersRepository;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


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
        post.setPostingDate(new Date(new java.util.Date().getTime()));
        post.setTitle(title);
        User user = usersRepository.findByUsername(principal.getUsername());
        post.setUser(user);
        user.getPosts().add(post);
        user = usersRepository.save(user);
        return user;
    }
   
   public boolean isUsernameAvailable(String username){
	   
	   return usersRepository.findByUsername(username) == null;
	   
   }
   
   public void save(User user){
	   usersRepository.save(user);
   }
   
   
   //get users with with just username, firstName, lastName field
   //it is possibly stuped solution for self reference json formatting problem
   @Transactional
   public Set<User> getCurrentUserFriends(){   
	   User user = usersRepository.findByUsername(principal.getUsername());
	   Set<User> friends = new HashSet<>();
	   User newUser;
	   for(User u : user.getFriends()){
		   newUser = new User();
		   newUser.setId(u.getId());
		   newUser.setUsername(u.getUsername());
		   newUser.setFirstName(u.getFirstName());
		   newUser.setLastName(u.getLastName());
		   friends.add(newUser);
	   }
	   
	   return friends;
   }
   
   public Set<User> getAllUsersInfo(){
	
	   Set<User> allUsers = new HashSet<>();
	   User newUser;
	   for(User u : usersRepository.findAll()){
		   newUser = new User();
		   newUser.setId(u.getId());
		   newUser.setUsername(u.getUsername());
		   newUser.setFirstName(u.getFirstName());
		   newUser.setLastName(u.getLastName());
		   allUsers.add(newUser);
	   }
	   
	   return allUsers;
   }
   
	   
   }
   
