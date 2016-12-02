package com.socnet.web.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socnet.service.MessageService;
import com.socnet.service.UsernameStorage;

@RestController
@RequestMapping(value = "/api/dialogs")
public class DialogsRestController {
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	UsernameStorage usernameStorage;
	
	@GetMapping("/unreadMessagesCount")
	public int getUnreadMessagesCount(){
		return messageService.getUnreadMessagesCount(usernameStorage.getUsername());
	}
	
	@GetMapping("/readAll")
	public void readAllMessages(@RequestParam String senderUsername){
		messageService.setUnreadMessagesCountToNull(senderUsername,
									usernameStorage.getUsername());
	}
	
	
	
}
