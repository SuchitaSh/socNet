package com.socnet.persistence.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.socnet.persistence.entities.Comment;
import com.socnet.persistence.entities.Post;
import com.socnet.persistence.entities.User;
import com.socnet.persistence.repository.CommentsRepository;
import com.socnet.persistence.spring.data.repository.SpringDataCommentsRepository;

@Repository
public class CommentsRepositorySpringDataImpl implements CommentsRepository{

	@Autowired
	CommentsRepository repository;
	
	@Override
	public Comment findById(Long id) {
		return repository.findById(id);
	}
	@Override
	public Set<Comment> findByPost(Post post) {
		return repository.findByPost(post);
	}
	@Override
	public Set<Comment> findByUser(User user) {
		return repository.findByUser(user);
	}
	@Override
	public Comment save(Comment comment) {
		return repository.save(comment);
	}
	
	
}
