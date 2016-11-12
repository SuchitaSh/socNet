package com.socnet.persistence.spring.data.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socnet.persistence.entities.Post;
import com.socnet.persistence.entities.User;

@Repository
public interface SpringDataPostsRepository extends JpaRepository<Post, Long>{

	Post findById(Long id);
	Set<Post> findByUser(User user);
	Set<Post> findByTitle(String title);
}
