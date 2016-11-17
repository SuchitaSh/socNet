package com.socnet.service;

import com.socnet.dto.BasicPostDto;
import com.socnet.exception.AccessDeniedException;
import com.socnet.exception.EntityNotFoundException;
import com.socnet.persistence.entities.Post;
import com.socnet.persistence.entities.User;
import com.socnet.persistence.repository.PostsRepository;
import com.socnet.persistence.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private ModelMapper modelMapper;
    private UsersRepository usersRepository;
    private PostsRepository postsRepository;
    private UserService userService;

    @Autowired
    public PostService(UsersRepository usersRepository, PostsRepository postsRepository,
                       UserService userService, ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.postsRepository = postsRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Transactional
    public BasicPostDto addPost(BasicPostDto postDto) throws AccessDeniedException {
        User user = userService.getCurrentUser();

        // TODO: move permission checking to another place(e.g. Filter)
        if(user == null) {
            throw new AccessDeniedException();
        }

        Long currentUserId = user.getId();

        if(currentUserId == null) {
            throw new AccessDeniedException();
        }

        // TODO: uncomment when there will be a correct impl of isFriendOf
//        if(!currentUserId.equals(postDto.getId()) && userService.isFriendOf()) {
//            throw new AccessDeniedException();
//        }

        Post post = modelMapper.map(postDto, Post.class);
        post.setPostingDate(new Date());
        post = postsRepository.save(post);
        return modelMapper.map(post, BasicPostDto.class);
    }


    /**
     * @return Post entities with all comments
     */
    @Transactional
    public List<BasicPostDto> getAllPostsOfUser(Long userId) {
        User user = usersRepository.findById(userId);

        if (user == null) {
            throw new EntityNotFoundException();
        }

        List<BasicPostDto> posts =  postsRepository.findByUserOrderByIdAsc(user)
                .stream()
                .map(post -> modelMapper.map(post, BasicPostDto.class))
                .collect(Collectors.toList());

        return posts;
    }

    /**
     * @return Post, just entity
     */
    public Post getPost(Long postId) {
        return postsRepository.findById(postId);
    }
}
