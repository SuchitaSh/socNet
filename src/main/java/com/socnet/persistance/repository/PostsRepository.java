package com.socnet.persistance.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socnet.persistance.entities.Post;
import com.socnet.persistance.entities.User;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long>{

	Post findById(Long id);
	Set<Post> findByUser(User user);
	Set<Post> findByTitle(String title);
}
