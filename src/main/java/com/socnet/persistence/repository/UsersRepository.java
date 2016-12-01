package com.socnet.persistence.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socnet.persistence.entities.User;

public interface UsersRepository{

	User findByUsername(String username);
	User findById(Long id);

    Set<User> getFollowersByUsername(String username);

    Set<User> findAll();
	Set<User> getFollowingsByUsername(String username);
	User save(User user);
	
	
}
