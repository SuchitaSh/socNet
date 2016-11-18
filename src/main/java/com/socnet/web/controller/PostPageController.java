package com.socnet.web.controller;

import com.socnet.exception.EntityNotFoundException;
import com.socnet.persistence.entities.Post;
import com.socnet.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ruslan Lazin
 */
@Controller
@RequestMapping(value = "/posts/{postId:[0-9]+}")
public class PostPageController {
    private PostService postService;
    private UserService userService;

    @Autowired
    public PostPageController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getPost(@PathVariable Long postId) {
        Post post = postService.getPost(postId);
        if (post == null) {
            throw new EntityNotFoundException();
        }
        ModelAndView modelAndView = new ModelAndView("post");
        modelAndView.addObject("post", post);
        modelAndView.addObject("author", post.getAuthor());
        modelAndView.addObject("user", userService.getCurrentUser());
        return modelAndView;
    }
}
