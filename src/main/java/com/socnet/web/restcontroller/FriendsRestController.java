package com.socnet.web.restcontroller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socnet.persistence.entities.User;
import com.socnet.service.UserService;
import com.socnet.service.UsernameStorage;

@RestController
public class FriendsRestController {

	@Autowired
	UserService userService;
	
	@GetMapping(path = "/api/friends", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<User>> getCurrentUserFriends(){
	
		Set<User> friends = userService.getCurrentUserFriends();
		
		if(friends.isEmpty()){
			return new ResponseEntity<Set<User>>(HttpStatus.NO_CONTENT);
		}
		
		return ResponseEntity.ok(friends);
	}
	
	@GetMapping(path = "/api/friends/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<User>> getUserFriends(@PathVariable String username){
	
		System.out.println(username);
		Set<User> friends = userService.getUserFriends(username);
		
		if(friends.isEmpty()){
			return new ResponseEntity<Set<User>>(HttpStatus.NO_CONTENT);
		}
		
		return ResponseEntity.ok(friends);
	}	
}
