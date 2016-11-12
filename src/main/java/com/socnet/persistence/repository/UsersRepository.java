package com.socnet.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socnet.persistence.entities.User;

public interface UsersRepository{

	User findByUsername(String username);
	User findById(Long id);
	User save(User user);
	
	
}
