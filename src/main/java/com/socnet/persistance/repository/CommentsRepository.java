package com.socnet.persistance.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socnet.persistance.entities.Comment;
import com.socnet.persistance.entities.Post;
import com.socnet.persistance.entities.User;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {

	Comment findById(Long id);
	Set<Comment> findByUser(User user);
	Set<Comment> findByPost(Post post);
	
	
}