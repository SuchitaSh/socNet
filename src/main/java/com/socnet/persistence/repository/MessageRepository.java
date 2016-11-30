package com.socnet.persistence.repository;

import java.util.List;

import com.socnet.persistence.entities.Message;

/**
 * @author Ruslan Lazin
 */
public interface MessageRepository {
    public void addMessage(String key, Message message);

    public List<Message> getAllMessages(String key);

    public List<Message> getLastMessages(String key, int quantity);
}
