package com.socnet.persistance.impl;

import com.socnet.persistance.entities.Post;
import com.socnet.persistance.repository.PostRepository;
import org.springframework.stereotype.Component;

/**
 * @author Ruslan Lazin
 */
@Component
public class PostRepositoryImpl implements PostRepository {
    @Override
    public Post findById(Long postId) {
        return null;
    }

    @Override
    public Post update(Post post) {
        return null;
    }
}
