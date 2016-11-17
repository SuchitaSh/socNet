package com.socnet.service;

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

    @Autowired
    public AuthService(UsernameStorage principal) {
        this.principal = principal;
    }

    public boolean isCurrentUserLoggedIn() {
        return principal.getUsername() == null ? false : true;
    }
}
