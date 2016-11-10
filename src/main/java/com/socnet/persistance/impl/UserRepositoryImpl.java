package com.socnet.persistance.impl;

import com.socnet.persistance.entities.User;
import com.socnet.persistance.repository.UserRepository;
import org.springframework.stereotype.Component;


@Component
public class UserRepositoryImpl implements UserRepository {
    @Override
    public User findByUsername(String login) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }
}
