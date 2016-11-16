package com.socnet.service;

import com.socnet.dto.BasicPostDto;
import com.socnet.exception.EntityNotFoundException;
import com.socnet.persistence.entities.Post;
import com.socnet.persistence.entities.User;
import com.socnet.persistence.repository.PostsRepository;
import com.socnet.persistence.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {

    private ModelMapper modelMapper;
    private UsersRepository usersRepository;
    private PostsRepository postsRepository;

    @Autowired
    public PostService(UsersRepository usersRepository, PostsRepository postsRepository,
                       ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.postsRepository = postsRepository;
        this.modelMapper = modelMapper;
    }

    public Post addPost(Post post) {
        post = postsRepository.save(post);
        return post;
    }


    /**
     * @return Post entities with all comments
     */
    @Transactional
    public Set<BasicPostDto> getAllPostsOfUser(Long userId) {
        User user = usersRepository.findById(userId);

        if (user == null) {
            throw new EntityNotFoundException();
        }

        Set<BasicPostDto> posts =  postsRepository.findByUser(user)
                .stream()
                .map(post -> modelMapper.map(post, BasicPostDto.class))
                .collect(Collectors.toSet());

        return posts;
    }

    /**
     * @return Post, just entity
     */
    public Post getPost(Long postId) {
        return postsRepository.findById(postId);
    }
}
