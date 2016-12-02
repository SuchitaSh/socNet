package com.socnet.persistence.repository;

import com.socnet.dto.BasicPostDto;
import com.socnet.persistence.entities.Post;
import com.socnet.persistence.entities.User;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;

public interface PostsRepository {

	Post findById(Long id);
	Set<Post> findByUser(User user);
	Set<Post> findByTitle(String title);
	Post save(Post post);
	List<Post> findByUserOrderByIdAsc(User user);
	List<Post> findByIdSliced(Long userId, Long fromPost);
}
