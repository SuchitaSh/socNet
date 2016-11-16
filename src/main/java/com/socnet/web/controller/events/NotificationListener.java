package com.socnet.web.controller.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.socnet.persistence.entities.Notification;
import com.socnet.persistence.entities.User;
import com.socnet.service.UserService;
import com.socnet.service.UsernameStorage;
import com.socnet.utils.NotificationType;

@Component
public class NotificationListener {

	@Autowired
	UserService userService;
	
	@Autowired
	UsernameStorage usernameStorage;
	
	@Async
	@EventListener
	public void handleURequest(FriendRequestEvent friendRequestEvent){
		User receiver = userService.findUserByUsername(friendRequestEvent.getReceiverUsername());
		System.out.println("1");
		User sender =  userService.findUserByUsername(friendRequestEvent.getSenderUsername());
		System.out.println(2);
		Notification notification = new Notification();
		notification.setAuthor(sender);
		System.out.println(3);
		notification.setReceiver(receiver);
		System.out.println(4);
		notification.setEventType(NotificationType.FRIEND_REQUEST);
		System.out.println("hell no");
		userService.save(receiver);
		
		System.out.println("hell no");
	}
	
}
