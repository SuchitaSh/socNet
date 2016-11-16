package com.socnet.web.controller.events;

import org.springframework.context.ApplicationEvent;

public class FriendRequestEvent extends ApplicationEvent {

	public FriendRequestEvent(Object source, String senderUsername, String receiverUsername) {
		super(source);
		this.senderUsername = senderUsername;
		this.receiverUsername = receiverUsername;
	}
	
	private static final long serialVersionUID = 8713649683571991553L;

	private String receiverUsername;
	private String senderUsername;
	
	public String getSenderUsername(){
		return senderUsername;
	}
	
	public String getReceiverUsername(){
		return receiverUsername;
	}
	
}
