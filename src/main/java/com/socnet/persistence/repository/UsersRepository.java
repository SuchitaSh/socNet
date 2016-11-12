package com.socnet.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socnet.persistence.entities.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
	User findById(Long id);
	
	
}
