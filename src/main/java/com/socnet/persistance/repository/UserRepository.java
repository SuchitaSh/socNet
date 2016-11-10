package com.socnet.persistance.repository;

import com.socnet.persistance.entities.User;

/**
 * @author Ruslan Lazin
 */
public interface UserRepository {
    User findByUsername(String login);
}
