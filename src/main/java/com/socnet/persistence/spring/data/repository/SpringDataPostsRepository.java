package com.socnet.persistence.spring.data.repository;

import com.socnet.persistence.entities.Post;
import com.socnet.persistence.entities.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SpringDataPostsRepository extends JpaRepository<Post, Long>{

	Post findById(Long id);
	
	Set<Post> findByUser(User user);
	
	Set<Post> findByTitle(String title);
	
	List<Post> findByUserOrderByIdAsc(User user);
	
	List<Post> findTop10ByUserIdAndIdLessThanOrderByIdDesc(Long userId, Long fromPost);
}
