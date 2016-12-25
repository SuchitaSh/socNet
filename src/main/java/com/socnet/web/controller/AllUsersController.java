package com.socnet.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.socnet.dto.UserWithFollowingStatusDto;
import com.socnet.service.UserService;

@Controller
public class AllUsersController {

	UserService userService;
	
	@Autowired
	public AllUsersController(UserService userService){
		this.userService = userService;
	}
	
    @GetMapping("/all-users")
    public String allUsers(Model model) {
    	List<UserWithFollowingStatusDto> allUsers = userService.getAllUsersWithCurrentUserFollowingStatus();    
    	model.addAttribute("users", allUsers);
        return "all-users";
    }
}
