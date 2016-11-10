package com.socnet.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socnet.persistance.entities.Comment;

public interface CommentsRepository extends JpaRepository<Comment, Long> {

}
