package com.socnet.persistence.impl;

import com.socnet.persistence.entities.Post;
import com.socnet.persistence.entities.User;
import com.socnet.persistence.repository.PostsRepository;
import com.socnet.persistence.spring.data.repository.SpringDataPostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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

	@Override
	public List<Post> findByUserOrderByIdAsc(User user) {
		return repository.findByUserOrderByIdAsc(user);
	}

}
