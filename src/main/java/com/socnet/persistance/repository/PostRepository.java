package com.socnet.persistance.repository;

import com.socnet.persistance.entities.Post;

/**
 * @author Ruslan Lazin
 */
public interface PostRepository {

    public Post findById(Long postId);

    public Post update(Post post);
}
