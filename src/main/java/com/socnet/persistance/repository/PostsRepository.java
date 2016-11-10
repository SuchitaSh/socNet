package com.socnet.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socnet.persistance.entities.Post;

public interface PostsRepository extends JpaRepository<Post, Long>{

}
