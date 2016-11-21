package com.socnet.persistence.repository;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public interface MessageRepository {
    public void addMessage(String key, String text);

    public List<String> getAllMessages(String key);

    public List<String> getLastMessages(String key, int quantity);
}
