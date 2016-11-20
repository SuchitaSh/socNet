package com.socnet.service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;

@Component
public class OnlineUsersStorage {

	private ConcurrentSkipListSet<String> onlineUsers = new ConcurrentSkipListSet<>();	
	
	public void setUserOnline(String username){
		onlineUsers.add(username);
	}
	
	public void setUserOffline(String username){
		onlineUsers.remove(username);
	}
	
	public boolean isOnline(String username){
		return onlineUsers.contains(username);
	}
}
