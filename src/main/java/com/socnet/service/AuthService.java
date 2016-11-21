package com.socnet.service;

import com.socnet.persistence.entities.User;
import com.socnet.persistence.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Ruslan Lazin
 */
@Service
@Component
public class AuthService {

    private UsernameStorage principal;
    private UsersRepository usersRepository;
    private OnlineUsersStorage onlineUsersStorage;


    @Autowired
    public AuthService(UsernameStorage principal, UsersRepository usersRepository,
                       OnlineUsersStorage onlineUsersStorage) {
        this.principal = principal;
        this.usersRepository = usersRepository;
        this.onlineUsersStorage = onlineUsersStorage;
    }


    public boolean isUsernameAvailable(String username) {
        return usersRepository.findByUsername(username) == null;
    }


    /*    method returns User object, and saves username in sessionScope "principal"
    returns null if user does not found or password does not match.*/
    public User login(String login, String password) {
        User user = usersRepository.findByUsername(login);
        if (user != null && user.getPassword().equals(password)) {
            principal.setUsername(user.getUsername());
            onlineUsersStorage.setUserOnline(user.getUsername());
        } else {
            user = null;
        }
        return user;
    }

    public void logout() {
        onlineUsersStorage.setUserOffline(principal.getUsername());
        principal.setUsername(null);
    }

    public boolean isCurrentUserLoggedIn() {
        return principal.getUsername() != null;
    }
}
