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
import java.util.ArrayList;
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
        if(!hasPostingPermission(postDto)) {
            throw new AccessDeniedException();
        }

        System.out.println("Author: " + postDto.getAuthor().getId());
        Post post = modelMapper.map(postDto, Post.class);
        post.setPostingDate(new Date());
        System.out.println("Author map: " + post.getAuthor().getId());
        post = postsRepository.save(post);
        System.out.println("Author after: " + post.getAuthor().getId());
        return modelMapper.map(post, BasicPostDto.class);
    }

    private boolean hasPostingPermission(BasicPostDto postDto) {
        User currentUser = userService.getCurrentUser();

        Long currentUseId = currentUser.getId();
        Long authorUserId = postDto.getAuthor().getId();
        Long targetUserId = postDto.getUser().getId();

        User authorUser = new User();
        authorUser.setId(postDto.getAuthor().getId());

        User targetUser = new User();
        targetUser.setId(targetUserId);


        boolean selfPost = targetUserId.equals(authorUserId) && targetUserId.equals(currentUseId);
        boolean friendPost = userService.isCurrentUserFriendOf(targetUser);

        return selfPost || friendPost;
    }

    private boolean hasPostDeletePermission(long postId, long userId) {
        Post post = postsRepository.findById(postId);

        if(post.getAuthor().getId().equals(userId)) {
            return true;
        }

        if(post.getUser().getId().equals(userId)) {
            return true;
        }

        return false;
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

    /**
     *  Get a slice of user's posts. The slice contains no more than a specified in PostRepository
     *  number of elements. Iterating over slices possible by varying the fromPost parameter which
     *  specifies the last post id retrieved by previous call to getUserPostsSlice
     *
     *  Note: posts ordered by id in a descending way
     * @param userId is the id of user posts of whom should be retrieved
     * @param fromPost all post will have id < fromPost
     * @return list of posts
     */
	public List<BasicPostDto> getUserPostsSlice(long userId, long fromPost) {
		List<BasicPostDto> posts = new ArrayList<>();
		
		List<BasicPostDto> basicPosts =  postsRepository.findByIdSliced(userId, fromPost)
	                		.stream()
	                		.map(post -> modelMapper.map(post, BasicPostDto.class))
	                		.collect(Collectors.toList());
    	
    	return basicPosts;
	}

    public void deletePost(long postId) {
        postsRepository.delete(postId);
    }

    public void tryDeletePost(long postId) {
        if(!hasPostDeletePermission(postId, userService.getCurrentUser().getId())) {
            throw new AccessDeniedException();
        }

        deletePost(postId);
    }
}
