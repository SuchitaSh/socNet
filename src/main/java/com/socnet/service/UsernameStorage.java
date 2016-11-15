package com.socnet.service;

import java.security.Principal;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * @author Ruslan Lazin
 */
@Component
@SessionScope
public class UsernameStorage implements Principal{
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @Override
    public String getName() {
    	return username;
    }
}
