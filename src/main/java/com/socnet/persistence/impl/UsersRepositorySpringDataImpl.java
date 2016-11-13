package com.socnet.persistence.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.socnet.persistence.entities.User;
import com.socnet.persistence.repository.UsersRepository;
import com.socnet.persistence.spring.data.repository.SpringDataUsersRepository;

@Repository
public class UsersRepositorySpringDataImpl implements UsersRepository {

	@Autowired
    SpringDataUsersRepository repository;
	
	@Override
	public User findById(Long id) {
		return repository.findById(id);
	}
	
	@Override
	public User findByUsername(String username) {
		return repository.findByUsername(username);
	}
	
	@Override
	public User save(User user) {
		return repository.save(user);
	}
	
	@Override
	public Set<User> getFollowingsByUsername(String username) {
		return repository.getFollowingsByUsername(username);
	}

	@Override
	public Set<User> findAll() {
		return new HashSet(repository.findAll());
	}
	
}
