package com.socnet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by admin on 17.11.2016.
 */
@Controller
public class FollowersController {

    @GetMapping("/followers")
    public String getFriends(){
        return "followers";
    }

    @GetMapping("/followers/{username}")
    public String getFriends(@PathVariable String username){
        return "followers";
    }
}
