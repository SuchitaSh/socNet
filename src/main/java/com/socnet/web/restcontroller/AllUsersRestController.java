package com.socnet.web.restcontroller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socnet.persistence.entities.User;
import com.socnet.service.UserService;

@RestController
public class AllUsersRestController {

	@Autowired
	UserService userService;
	
	@GetMapping(path = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<User>> getCurrentUserFriends(){
	
		Set<User> allUsers = userService.getAllUsersInfo();

		
		if(allUsers.isEmpty()){
			return new ResponseEntity<Set<User>>(HttpStatus.NO_CONTENT);
		}
		
		return ResponseEntity.ok(allUsers);
	}

	
}
