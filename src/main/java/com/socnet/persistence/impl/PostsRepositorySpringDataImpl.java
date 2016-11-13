package com.socnet.persistence.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.socnet.persistence.entities.Post;
import com.socnet.persistence.entities.User;
import com.socnet.persistence.repository.PostsRepository;

@Repository
public class PostsRepositorySpringDataImpl implements PostsRepository {

	@Autowired
	SpringDataPostsRepository repository;
	
	@Override
	public Post findById(Long id) {
		return repository.findById(id);
	}
	
	@Override
	public Set<Post> findByTitle(String title) {
		return repository.findByTitle(title);
	}
	
	@Override
	public Set<Post> findByUser(User user) {
		return repository.findByUser(user);
	}
	
	@Override
	public Post save(Post post) {
		return repository.save(post);
		
	}
	
}
