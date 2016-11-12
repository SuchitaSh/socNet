package com.socnet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FriendsController {
	
	@GetMapping("/friends")
	public String getFriends(){
		return "friends";
	}
	
	@GetMapping("/friends/{username}")
	public String getFriends(@PathVariable String username){
		return "friends";
	}
	
}
