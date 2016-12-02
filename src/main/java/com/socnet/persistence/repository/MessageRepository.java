package com.socnet.persistence.repository;

import java.util.List;

import com.socnet.persistence.entities.Message;

/**
 * @author Ruslan Lazin
 */
public interface MessageRepository {
    void addMessage(String key, Message message);

    List<Message> getAllMessages(String key);

    List<Message> getLastMessages(String key, int quantity);
    
    int getUnreadMessagesCount(String username);
    
    int getUnreadMessagesCountPerSender(String sender, String receiver);
    
    void setUnreadMessagesCountToNull(String sender, String receiver);
}
