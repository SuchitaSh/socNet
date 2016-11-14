package com.socnet.web.controller;

import com.socnet.exception.EntityNotFoundException;
import com.socnet.persistence.entities.Post;
import com.socnet.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ruslan Lazin
 */
@Controller
@RequestMapping("/post")
public class PostPageController {
    private PostService postService;

    @Autowired
    public PostPageController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ModelAndView getPost(@RequestParam Long postId) {
        System.out.println("in postController");
        Post post = postService.getPost(postId);
        if (post == null) {
            throw new EntityNotFoundException();
        }
        ModelAndView modelAndView = new ModelAndView("post");
        modelAndView.addObject("post",post);
        modelAndView.addObject("user",post.getUser());
        return modelAndView;
    }
}
